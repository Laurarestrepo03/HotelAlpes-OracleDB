package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hoteles.modelo_queries.Servicio_popular;

import java.util.Collection;
import java.sql.Date;

public interface Servicio_popularRepository extends JpaRepository<Servicio_popular, String> {

    //RFC2 - Get popular services
    @Query(value="SELECT TS.nombre, COALESCE(COUNT(C.servicio), 0) cantidad\r\n" + //
        "FROM tipos_servicio TS\r\n" + //
        "LEFT JOIN consumen C ON C.servicio = TS.id_tipo AND C.fecha BETWEEN :fecha_inicio AND :fecha_fin\r\n" + //
        "INNER JOIN servicios S ON S.tipo = TS.id_tipo\r\n" + //
        "GROUP BY TS.nombre\r\n" + //
        "ORDER BY cantidad DESC, TS.nombre", nativeQuery = true)
    Collection<Servicio_popular> darServicios_populares(@Param("fecha_inicio") Date fecha_inicio, @Param("fecha_fin") Date fecha_fin);
    
}
