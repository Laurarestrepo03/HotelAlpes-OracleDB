--RFC1
SELECT H.num_habitacion, SUM(NVL(S.precio, 0)) AS dinero
FROM habitaciones H
LEFT JOIN reservas R ON R.habitacion = H.num_habitacion
LEFT JOIN consumen C ON C.cliente = R.cliente_reserva
LEFT JOIN servicios S ON S.tipo = C.servicio
WHERE (C.fecha BETWEEN (SELECT TO_DATE('01/01/' || EXTRACT(YEAR FROM SYSDATE)) FROM DUAL) AND (SELECT SYSDATE FROM DUAL))OR R.habitacion IS NULL
GROUP BY H.num_habitacion;

--RFC2
SELECT TS.nombre, COALESCE(COUNT(C.servicio), 0) cantidad
FROM tipos_servicio TS
LEFT JOIN consumen C ON C.servicio = TS.id_tipo AND C.fecha BETWEEN :fecha_inicio AND :fecha_fin
INNER JOIN servicios S ON S.tipo = TS.id_tipo 
GROUP BY TS.nombre
ORDER BY cantidad DESC, TS.nombre;

--RFC3
WITH Fechas AS (
    SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) + LEVEL - 1 AS fecha_reserva
    FROM DUAL
    CONNECT BY (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) + LEVEL - 1 <= (SELECT SYSDATE FROM DUAL)
)
SELECT H.num_habitacion, COALESCE(ROUND((COUNT(DISTINCT fecha_reserva)/366)*100, 2), 0) porcentaje
FROM Fechas
INNER JOIN reservas R ON fecha_reserva BETWEEN R.fecha_entrada AND R.fecha_salida
RIGHT JOIN habitaciones H ON H.num_habitacion = R.habitacion
GROUP BY (H.num_habitacion);

--RFC4
SELECT DISTINCT TS.nombre, S.capacidad, S.tipo_cobro, S.precio
FROM tipos_servicio TS
LEFT JOIN consumen C ON C.servicio = TS.id_tipo
INNER JOIN servicios S ON S.tipo = TS.id_tipo
WHERE ((:precio_menor IS NULL AND :precio_mayor IS NULL) OR (S.precio BETWEEN :precio_menor AND :precio_mayor))
AND ((:fecha_inicio IS NULL AND :fecha_fin IS NULL) OR (C.fecha BETWEEN :fecha_inicio AND :fecha_fin))
AND ((:registrado_por IS NULL) OR (:registrado_por = registrado_por))
AND ((:tipo_menor IS NULL AND :tipo_mayor IS NULL) OR (TS.id_tipo BETWEEN :tipo_menor AND :tipo_mayor));

--RFC5
SELECT C.cliente, TS.nombre as servicio, C.fecha, C.cuenta_pagada, C.hora, C.registrado_por
FROM consumen C
INNER JOIN tipos_servicio TS ON TS.id_tipo = C.servicio
WHERE C.cliente = :cliente AND (C.fecha BETWEEN :fecha_inicio AND :fecha_fin);

--RFC6.1
WITH Fechas (fecha_reserva) AS (
    SELECT MIN(fecha_entrada)
    FROM reservas
    UNION ALL
    SELECT fecha_reserva + 1
    FROM Fechas
    WHERE fecha_reserva + 1 <= (SELECT MAX(fecha_salida) FROM reservas)
)
SELECT fecha_reserva AS fecha, COUNT(*) AS cantidad
FROM Fechas
INNER JOIN reservas ON fecha_reserva BETWEEN fecha_entrada AND fecha_salida
GROUP BY (fecha_reserva, habitacion)
ORDER BY cantidad DESC, fecha_reserva DESC
FETCH FIRST 20 ROWS ONLY;

--RFC6.2
SELECT C.fecha, COUNT(C.fecha) AS cantidad
FROM consumen C
GROUP BY (C.fecha)
ORDER BY cantidad DESC, C.fecha DESC
FETCH FIRST 20 ROWS ONLY;

--RFC6.3 
WITH Fechas (fecha_reserva) AS (
    SELECT MIN(fecha_entrada)
    FROM reservas
    UNION ALL
    SELECT fecha_reserva + 1
    FROM Fechas
    WHERE fecha_reserva + 1 <= (SELECT MAX(fecha_salida) FROM reservas)
)
SELECT fecha_reserva, COUNT(*) AS cantidad
FROM Fechas
INNER JOIN reservas ON fecha_reserva BETWEEN fecha_entrada AND fecha_salida
GROUP BY (fecha_reserva, habitacion)
ORDER BY cantidad ASC, fecha_reserva DESC
FETCH FIRST 20 ROWS ONLY;

--RFC7
WITH Fechas AS (
    SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) + LEVEL - 1 AS fecha_reserva
    FROM DUAL
    CONNECT BY (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) + LEVEL - 1 <= (SELECT SYSDATE FROM DUAL)
)
SELECT U.num_documento AS cliente, ROUND(COUNT(DISTINCT CONCAT(U.num_documento, fecha_reserva))/7, 2) AS semanas, SC.suma_consumo 
FROM Fechas
INNER JOIN reservas R ON fecha_reserva BETWEEN R.fecha_entrada AND R.fecha_salida
INNER JOIN usuarios U ON R.cliente_reserva = U.num_documento
INNER JOIN consumen C ON C.cliente = U.num_documento
INNER JOIN servicios S ON S.tipo = C.servicio
INNER JOIN Suma_Consumo SC ON U.num_documento = SC.cliente
WHERE U.tipo = 1
GROUP BY (U.num_documento, SC.suma_consumo)
HAVING COUNT(DISTINCT CONCAT(U.num_documento, fecha_reserva)) >= 14 OR SC.suma_consumo > 15000000;

--RFC8
WITH Semanas (inicio_semana, fin_semana) AS (
    SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,
           (SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana
    FROM DUAL
    UNION ALL
    SELECT fin_semana, fin_semana + 7
    FROM Semanas
    WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)
)
SELECT nombre, SUM(menos_tres) semanas FROM (
    SELECT S.inicio_semana, S.fin_semana, TS.nombre, CASE WHEN COUNT(C.servicio) < 3 THEN 1 ELSE 0 END AS menos_tres
    FROM Semanas S
    LEFT JOIN tipos_servicio TS ON 1=1
    LEFT JOIN consumen C ON C.fecha BETWEEN S.inicio_semana AND S.fin_semana
        AND TS.id_tipo = C.servicio
    GROUP BY S.inicio_semana, S.fin_semana, TS.nombre)
GROUP BY (nombre)
HAVING SUM(menos_tres) = 52;

--RFC9
SELECT U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre tipo, COUNT(U.num_documento) veces_consumo, C.fecha
FROM usuarios U
INNER JOIN consumen C ON C.cliente = U.num_documento
INNER JOIN tipos_usuario TU ON TU.id_tipo = U.tipo 
WHERE (C.servicio = :servicio)
  AND (:num_documento IS NULL OR U.num_documento = :num_documento)
  AND (:tipo_documento IS NULL OR U.tipo_documento = :tipo_documento)
  AND (:nombre IS NULL OR U.nombre = :nombre)
  AND (:correo IS NULL OR U.correo = :correo)
  AND ((:fecha_inicio IS NULL AND :fecha_fin IS NULL) OR (C.fecha BETWEEN :fecha_inicio AND :fecha_fin))
  AND (U.tipo = 1)
GROUP BY U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre, C.fecha
HAVING (:veces_consumo IS NULL AND COUNT(U.num_documento) >= 1) OR COUNT(U.num_documento) >= :veces_consumo;

--RFC10
SELECT U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre tipo
FROM usuarios U
INNER JOIN tipos_usuario TU ON TU.id_tipo = U.tipo 
LEFT JOIN (
    SELECT *
    FROM consumen C
    WHERE C.servicio = :servicio
    AND ((:fecha_inicio IS NULL AND :fecha_fin IS NULL) OR (C.fecha BETWEEN :fecha_inicio AND :fecha_fin))
) ClientesConsumo ON U.num_documento = ClientesConsumo.cliente
WHERE ClientesConsumo.cliente IS NULL
    AND (:num_documento IS NULL OR U.num_documento = :num_documento)
    AND (:tipo_documento IS NULL OR U.tipo_documento = :tipo_documento)
    AND (:nombre IS NULL OR U.nombre = :nombre)
    AND (:correo IS NULL OR U.correo = :correo)   
    AND (U.tipo = 1);

--RFC11.1
WITH Semanas (inicio_semana, fin_semana) AS (
    SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,
           (SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana
    FROM DUAL
    UNION ALL
    SELECT fin_semana, fin_semana + 7
    FROM Semanas
    WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)
)
SELECT inicio_semana, fin_semana, nombre
FROM (
    SELECT TS.nombre, S.inicio_semana, S.fin_semana, TS.id_tipo,
        ROW_NUMBER() OVER (PARTITION BY S.inicio_semana, S.fin_semana ORDER BY COUNT(TS.id_tipo) DESC) AS ranking
    FROM consumen C
    INNER JOIN tipos_servicio TS ON TS.id_tipo = C.servicio
    INNER JOIN Semanas S ON C.fecha BETWEEN S.inicio_semana AND S.fin_semana
    GROUP BY TS.nombre, S.inicio_semana, S.fin_semana, TS.id_tipo
) RankingSemanas
WHERE ranking = 1;

--RFC11.2
WITH Semanas (inicio_semana, fin_semana) AS (
    SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,
           (SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana
    FROM DUAL
    UNION ALL
    SELECT fin_semana, fin_semana + 7
    FROM Semanas
    WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)
)
SELECT inicio_semana, fin_semana, nombre
FROM (
    SELECT TS.nombre, S.inicio_semana, S.fin_semana, TS.id_tipo,
        ROW_NUMBER() OVER (PARTITION BY S.inicio_semana, S.fin_semana ORDER BY COUNT(TS.id_tipo) ASC) AS ranking
    FROM consumen C
    INNER JOIN tipos_servicio TS ON TS.id_tipo = C.servicio
    INNER JOIN Semanas S ON C.fecha BETWEEN S.inicio_semana AND S.fin_semana
    GROUP BY TS.nombre, S.inicio_semana, S.fin_semana, TS.id_tipo
) RankingSemanas
WHERE ranking = 1;

--RFC11.3
WITH Semanas (inicio_semana, fin_semana) AS (
    SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,
           (SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana
    FROM DUAL
    UNION ALL
    SELECT fin_semana, fin_semana + 7
    FROM Semanas
   WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)
),
Fechas (fecha_reserva) AS (
    SELECT MIN(fecha_entrada)
    FROM reservas
    UNION ALL
    SELECT fecha_reserva + 1
    FROM Fechas
    WHERE fecha_reserva + 1 <= (SELECT MAX(fecha_salida) FROM reservas)
)
SELECT inicio_semana, fin_semana, num_habitacion
FROM (
    SELECT H.num_habitacion, S.inicio_semana, S.fin_semana,
        ROW_NUMBER() OVER (PARTITION BY S.inicio_semana, S.fin_semana ORDER BY COUNT(H.num_habitacion) DESC) AS ranking
    FROM habitaciones H
    INNER JOIN reservas R ON R.habitacion = H.num_habitacion
    INNER JOIN Fechas F ON F.fecha_reserva BETWEEN R.fecha_entrada AND R.fecha_salida
    INNER JOIN Semanas S ON F.fecha_reserva  BETWEEN S.inicio_semana AND S.fin_semana
    GROUP BY  H.num_habitacion, S.inicio_semana, S.fin_semana
) RankingSemanas
WHERE ranking = 1;

--RFC11.4
WITH Semanas (inicio_semana, fin_semana) AS (
    SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,
           (SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana
    FROM DUAL
    UNION ALL
    SELECT fin_semana, fin_semana + 7
    FROM Semanas
    WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)
),
Fechas (fecha_reserva) AS (
    SELECT MIN(fecha_entrada)
    FROM reservas
    UNION ALL
    SELECT fecha_reserva + 1
    FROM Fechas
    WHERE fecha_reserva + 1 <= (SELECT MAX(fecha_salida) FROM reservas)
)
SELECT inicio_semana, fin_semana, num_habitacion
FROM (
    SELECT H.num_habitacion, S.inicio_semana, S.fin_semana,
        RANK() OVER (PARTITION BY S.inicio_semana, S.fin_semana ORDER BY COUNT(H.num_habitacion) ASC) AS ranking
    FROM habitaciones H
    INNER JOIN reservas R ON R.habitacion = H.num_habitacion
    INNER JOIN Fechas F ON F.fecha_reserva BETWEEN R.fecha_entrada AND R.fecha_salida
    INNER JOIN Semanas S ON F.fecha_reserva  BETWEEN S.inicio_semana AND S.fin_semana
    GROUP BY  H.num_habitacion, S.inicio_semana, S.fin_semana
) RankingSemanas
WHERE ranking = 1;

--RFC12
WITH Trimestres AS (
    SELECT
        TO_CHAR(ADD_MONTHS(SYSDATE, -12)) AS t1_inicio,
        TO_CHAR(ADD_MONTHS(SYSDATE, -9)) AS t2_inicio,
        TO_CHAR(ADD_MONTHS(SYSDATE, -6)) AS t3_inicio,
        TO_CHAR(ADD_MONTHS(SYSDATE, -3)) AS t4_inicio
    FROM DUAL
)
SELECT U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre AS tipo, COUNT(DISTINCT CASE
        WHEN R.fecha_salida BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1
        WHEN R.fecha_salida BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2
        WHEN R.fecha_salida BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3
        WHEN R.fecha_salida BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4
        END) AS cuenta_reservas,
        COUNT(DISTINCT CASE
        WHEN C.fecha BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1
        WHEN C.fecha BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2
        WHEN C.fecha BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3
        WHEN C.fecha BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4
        END) AS cuenta_consumo,
        COUNT(DISTINCT CASE
        WHEN ReS.dia_reserva BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1
        WHEN ReS.dia_reserva BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2
        WHEN ReS.dia_reserva BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3
        WHEN ReS.dia_reserva BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4
        END) AS cuenta_r_servicios
FROM usuarios U
INNER JOIN tipos_usuario TU ON TU.id_tipo = U.tipo
INNER JOIN reservas R ON R.cliente_reserva = U.num_documento
INNER JOIN consumen C ON C.cliente = U.num_documento
INNER JOIN tipos_servicio TS ON TS.id_tipo = C.servicio
INNER JOIN reservas_servicio ReS ON ReS.cliente = U.num_documento
INNER JOIN Trimestres T ON (((R.fecha_entrada BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3)) AND (R.fecha_salida BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3)))
                      OR ((R.fecha_entrada BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3)) AND (R.fecha_salida BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3)))
                      OR ((R.fecha_entrada BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3)) AND (R.fecha_salida BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3)))
                      OR ((R.fecha_entrada BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3)) AND (R.fecha_salida BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3))))
                      AND (C.fecha BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3)
                      OR C.fecha BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3)
                      OR C.fecha BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3)
                      OR C.fecha BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3))
                      AND (ReS.dia_reserva BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3)
                      OR ReS.dia_reserva BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3)
                      OR ReS.dia_reserva BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3)
                      OR ReS.dia_reserva BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3))                                    
INNER JOIN servicios S ON C.servicio = S.tipo
WHERE S.precio > 300000 AND (TO_NUMBER(SUBSTR(ReS.hora_fin, 1, 2)) - TO_NUMBER(SUBSTR(ReS.hora_inicio, 1, 2)) > 4) AND U.tipo = 1
GROUP BY U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre
HAVING
    COUNT(DISTINCT CASE
        WHEN R.fecha_salida BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1
        WHEN R.fecha_salida BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2
        WHEN R.fecha_salida BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3
        WHEN R.fecha_salida BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4
    END) = 4
    AND
    COUNT(DISTINCT CASE
        WHEN C.fecha BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1
        WHEN C.fecha BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2
        WHEN C.fecha BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3
        WHEN C.fecha BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4
    END) = 4
    AND
    COUNT(DISTINCT CASE
        WHEN ReS.dia_reserva BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1
        WHEN ReS.dia_reserva BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2
        WHEN ReS.dia_reserva BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3
        WHEN ReS.dia_reserva BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4
    END) = 4
    ORDER BY U.num_documento;
