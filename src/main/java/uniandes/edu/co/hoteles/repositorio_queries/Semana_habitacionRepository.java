package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.hoteles.modelo_queries.Semana_habitacion;

import java.util.Collection;
import java.sql.Date;

public interface Semana_habitacionRepository extends JpaRepository<Semana_habitacion, Date> {

    //RFC11.3 - Get most requested room by week
    @Query(value="WITH Semanas (inicio_semana, fin_semana) AS (\r\n" + //
        "SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,\r\n" + //
        "(SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana\r\n" + //
        "FROM DUAL\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fin_semana, fin_semana + 7\r\n" + //
        "FROM Semanas\r\n" + //
        "WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)\r\n" + //
        "),\r\n" + //
        "Fechas (fecha_reserva) AS (\r\n" + //
        "SELECT MIN(fecha_entrada)\r\n" + //
        "FROM reservas\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fecha_reserva + 1\r\n" + //
        "FROM Fechas\r\n" + //
        "WHERE fecha_reserva + 1 <= (SELECT MAX(fecha_salida) FROM reservas)\r\n" + //
        ")\r\n" + //
        "SELECT inicio_semana, fin_semana, num_habitacion\r\n" + //
        "FROM (\r\n" + //
        "SELECT H.num_habitacion, S.inicio_semana, S.fin_semana,\r\n" + //
        "ROW_NUMBER() OVER (PARTITION BY S.inicio_semana, S.fin_semana ORDER BY COUNT(H.num_habitacion) DESC) AS ranking\r\n" + //
        "FROM habitaciones H\r\n" + //
        "INNER JOIN reservas R ON R.habitacion = H.num_habitacion\r\n" + //
        "INNER JOIN Fechas F ON F.fecha_reserva BETWEEN R.fecha_entrada AND R.fecha_salida\r\n" + //
        "INNER JOIN Semanas S ON F.fecha_reserva  BETWEEN S.inicio_semana AND S.fin_semana\r\n" + //
        "GROUP BY  H.num_habitacion, S.inicio_semana, S.fin_semana\r\n" + //
        ") RankingSemanas\r\n" + //
        "WHERE ranking = 1", nativeQuery = true)
    Collection<Semana_habitacion> darHabitacionesMasSolicitadas();

    //RFC11.4 - Get least requested room by week
    @Query(value="WITH Semanas (inicio_semana, fin_semana) AS (\r\n" + //
        "SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,\r\n" + //
        "(SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana\r\n" + //
        "FROM DUAL\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fin_semana, fin_semana + 7\r\n" + //
        "FROM Semanas\r\n" + //
        "WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)\r\n" + //
        "),\r\n" + //
        "Fechas (fecha_reserva) AS (\r\n" + //
        "SELECT MIN(fecha_entrada)\r\n" + //
        "FROM reservas\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fecha_reserva + 1\r\n" + //
        "FROM Fechas\r\n" + //
        "WHERE fecha_reserva + 1 <= (SELECT MAX(fecha_salida) FROM reservas)\r\n" + //
        ")\r\n" + //
        "SELECT inicio_semana, fin_semana, num_habitacion\r\n" + //
        "FROM (\r\n" + //
        "SELECT H.num_habitacion, S.inicio_semana, S.fin_semana,\r\n" + //
        "ROW_NUMBER() OVER (PARTITION BY S.inicio_semana, S.fin_semana ORDER BY COUNT(H.num_habitacion) ASC) AS ranking\r\n" + //
        "FROM habitaciones H\r\n" + //
        "INNER JOIN reservas R ON R.habitacion = H.num_habitacion\r\n" + //
        "INNER JOIN Fechas F ON F.fecha_reserva BETWEEN R.fecha_entrada AND R.fecha_salida\r\n" + //
        "INNER JOIN Semanas S ON F.fecha_reserva  BETWEEN S.inicio_semana AND S.fin_semana\r\n" + //
        "GROUP BY  H.num_habitacion, S.inicio_semana, S.fin_semana\r\n" + //
        ") RankingSemanas\r\n" + //
        "WHERE ranking = 1", nativeQuery = true)
    Collection<Semana_habitacion> darHabitacionesMenosSolicitadas();
}
