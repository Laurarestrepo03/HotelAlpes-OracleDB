package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.hoteles.modelo_queries.Cliente_excelente;

import java.util.Collection;

public interface Cliente_excelenteRepository extends JpaRepository<Cliente_excelente, String>{

    //RFC12 - Get excellent clients
    @Query(value="WITH Trimestres AS (\r\n" + //
    "SELECT\r\n" + //
    "TO_CHAR(ADD_MONTHS(SYSDATE, -12)) AS t1_inicio,\r\n" + //
    "TO_CHAR(ADD_MONTHS(SYSDATE, -9)) AS t2_inicio,\r\n" + //
    "TO_CHAR(ADD_MONTHS(SYSDATE, -6)) AS t3_inicio,\r\n" + //
    "TO_CHAR(ADD_MONTHS(SYSDATE, -3)) AS t4_inicio\r\n" + //
    "FROM DUAL\r\n" + //
    ")\r\n" + //
    "SELECT U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre AS tipo, COUNT(DISTINCT CASE\r\n" + //
    "WHEN R.fecha_salida BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1\r\n" + //
    "WHEN R.fecha_salida BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2\r\n" + //
    "WHEN R.fecha_salida BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3\r\n" + //
    "WHEN R.fecha_salida BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4\r\n" + //
    "END) AS cuenta_reservas,\r\n" + //
    "COUNT(DISTINCT CASE\r\n" + //
    "WHEN C.fecha BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1\r\n" + //
    "WHEN C.fecha BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2\r\n" + //
    "WHEN C.fecha BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3\r\n" + //
    "WHEN C.fecha BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4\r\n" + //
    "END) AS cuenta_consumo,\r\n" + //
    "COUNT(DISTINCT CASE\r\n" + //
    "WHEN ReS.dia_reserva BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1\r\n" + //
    "WHEN ReS.dia_reserva BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2\r\n" + //
    "WHEN ReS.dia_reserva BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3\r\n" + //
    "WHEN ReS.dia_reserva BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4\r\n" + //
    "END) AS cuenta_r_servicios\r\n" + //
    "FROM usuarios U\r\n" + //
    "INNER JOIN tipos_usuario TU ON TU.id_tipo = U.tipo\r\n" + //
    "INNER JOIN reservas R ON R.cliente_reserva = U.num_documento\r\n" + //
    "INNER JOIN consumen C ON C.cliente = U.num_documento\r\n" + //
    "INNER JOIN tipos_servicio TS ON TS.id_tipo = C.servicio\r\n" + //
    "INNER JOIN reservas_servicio ReS ON ReS.cliente = U.num_documento\r\n" + //
    "INNER JOIN Trimestres T ON (((R.fecha_entrada BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3)) AND (R.fecha_salida BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3)))\r\n" + //
    "OR ((R.fecha_entrada BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3)) AND (R.fecha_salida BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3)))\r\n" + //
    "OR ((R.fecha_entrada BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3)) AND (R.fecha_salida BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3)))\r\n" + //
    "OR ((R.fecha_entrada BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3)) AND (R.fecha_salida BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3))))\r\n" + //
    "AND (C.fecha BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3)\r\n" + //
    "OR C.fecha BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3)\r\n" + //
    "OR C.fecha BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3)\r\n" + //
    "OR C.fecha BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3))\r\n" + //
    "AND (ReS.dia_reserva BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3)\r\n" + //
    "OR ReS.dia_reserva BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3)\r\n" + //
    "OR ReS.dia_reserva BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3)\r\n" + //
    "OR ReS.dia_reserva BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3))\r\n" + //
    "INNER JOIN servicios S ON C.servicio = S.tipo\r\n" + //
    "WHERE S.precio > 300000 AND (TO_NUMBER(SUBSTR(ReS.hora_fin, 1, 2)) - TO_NUMBER(SUBSTR(ReS.hora_inicio, 1, 2)) > 4) AND U.tipo = 1\r\n" + //
    "GROUP BY U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre\r\n" + //
    "HAVING\r\n" + //
    "COUNT(DISTINCT CASE\r\n" + //
    "WHEN R.fecha_salida BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1\r\n" + //
    "WHEN R.fecha_salida BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2\r\n" + //
    "WHEN R.fecha_salida BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3\r\n" + //
    "WHEN R.fecha_salida BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4\r\n" + //
    "END) = 4\r\n" + //
    "AND\r\n" + //
    "COUNT(DISTINCT CASE\r\n" + //
    "WHEN C.fecha BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1\r\n" + //
    "WHEN C.fecha BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2\r\n" + //
    "WHEN C.fecha BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3\r\n" + //
    "WHEN C.fecha BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4\r\n" + //
    "END) = 4\r\n" + //
    "AND\r\n" + //
    "COUNT(DISTINCT CASE\r\n" + //
    "WHEN ReS.dia_reserva BETWEEN t1_inicio AND ADD_MONTHS(t1_inicio, 3) THEN 1\r\n" + //
    "WHEN ReS.dia_reserva BETWEEN t2_inicio AND ADD_MONTHS(t2_inicio, 3) THEN 2\r\n" + //
    "WHEN ReS.dia_reserva BETWEEN t3_inicio AND ADD_MONTHS(t3_inicio, 3) THEN 3\r\n" + //
    "WHEN ReS.dia_reserva BETWEEN t4_inicio AND ADD_MONTHS(t4_inicio, 3) THEN 4\r\n" + //
    "END) = 4\r\n" + // 
    "ORDER BY U.num_documento", nativeQuery = true)
    Collection<Cliente_excelente> darClientesExcelentes();
}
