package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.hoteles.modelo_queries.Semana_servicio;

import java.util.Collection;
import java.sql.Date;

public interface Semana_servicioRepository extends JpaRepository<Semana_servicio, Date> {

    //RFC11.1 - Get most consumed service by week
    @Query(value="WITH Semanas (inicio_semana, fin_semana) AS (\r\n" + //
        "SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,\r\n" + //
        "(SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana\r\n" + //
        "FROM DUAL\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fin_semana, fin_semana + 7\r\n" + //
        "FROM Semanas\r\n" + //
        "WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)\r\n" + //
        ")\r\n" + //
        "SELECT inicio_semana, fin_semana, nombre\r\n" + //
        "FROM (\r\n" + //
        "SELECT TS.nombre, S.inicio_semana, S.fin_semana, TS.id_tipo,\r\n" + //
        "ROW_NUMBER() OVER (PARTITION BY S.inicio_semana, S.fin_semana ORDER BY COUNT(TS.id_tipo) DESC) AS ranking\r\n" + //
        "FROM consumen C\r\n" + //
        "INNER JOIN tipos_servicio TS ON TS.id_tipo = C.servicio\r\n" + //
        "INNER JOIN Semanas S ON C.fecha BETWEEN S.inicio_semana AND S.fin_semana\r\n" + //
        "GROUP BY TS.nombre, S.inicio_semana, S.fin_semana, TS.id_tipo\r\n" + //
        ") RankingSemanas\r\n" + //
        "WHERE ranking = 1", nativeQuery = true)
    Collection<Semana_servicio> darServiciosMasConsumidos();

    //RFC11.2 - Get least consumed service by week
    @Query(value="WITH Semanas (inicio_semana, fin_semana) AS (\r\n" + //
        "SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,\r\n" + //
        "(SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana\r\n" + //
        "FROM DUAL\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fin_semana, fin_semana + 7\r\n" + //
        "FROM Semanas\r\n" + //
        "WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)\r\n" + //
        ")\r\n" + //
        "SELECT inicio_semana, fin_semana, nombre\r\n" + //
        "FROM (\r\n" + //
        "SELECT TS.nombre, S.inicio_semana, S.fin_semana, TS.id_tipo,\r\n" + //
        "ROW_NUMBER() OVER (PARTITION BY S.inicio_semana, S.fin_semana ORDER BY COUNT(TS.id_tipo) ASC) AS ranking\r\n" + //
        "FROM consumen C\r\n" + //
        "INNER JOIN tipos_servicio TS ON TS.id_tipo = C.servicio\r\n" + //
        "INNER JOIN Semanas S ON C.fecha BETWEEN S.inicio_semana AND S.fin_semana\r\n" + //
        "GROUP BY TS.nombre, S.inicio_semana, S.fin_semana, TS.id_tipo\r\n" + //
        ") RankingSemanas\r\n" + //
        "WHERE ranking = 1", nativeQuery = true)
    Collection<Semana_servicio> darServiciosMenosConsumidos();
}
