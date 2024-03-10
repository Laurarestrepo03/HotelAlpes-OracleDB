package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.hoteles.modelo_queries.Poca_demanda;

import java.util.Collection;

public interface Poca_demandaRepository extends JpaRepository<Poca_demanda, String> {

    //RFC8 - Get low demand services
     @Query(value="WITH Semanas (inicio_semana, fin_semana) AS (\r\n" + //
        "SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) AS inicio_semana,\r\n" + //
        "(SELECT SYSDATE - INTERVAL '1' YEAR + 7 FROM DUAL) AS fin_semana\r\n" + //
        "FROM DUAL\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fin_semana, fin_semana + 7\r\n" + //
        "FROM Semanas\r\n" + //
        "WHERE fin_semana <= (SELECT SYSDATE - 7 FROM DUAL)\r\n" + //
        ")\r\n" + //
        "SELECT nombre, SUM(menos_tres) semanas FROM (\r\n" + //
        "SELECT S.inicio_semana, S.fin_semana, TS.nombre, CASE WHEN COUNT(C.servicio) < 3 THEN 1 ELSE 0 END AS menos_tres\r\n" + //
        "FROM Semanas S\r\n" + //
        "LEFT JOIN tipos_servicio TS ON 1=1\r\n" + //
        "LEFT JOIN consumen C ON C.fecha BETWEEN S.inicio_semana AND S.fin_semana\r\n" + //
        "AND TS.id_tipo = C.servicio\r\n" + //
        "GROUP BY S.inicio_semana, S.fin_semana, TS.nombre)\r\n" + // 
        "GROUP BY (nombre)\r\n" + //
        "HAVING SUM(menos_tres) = 52\r\n", nativeQuery = true)
    Collection<Poca_demanda> darServiciosPocaDemanda();
    
}
