package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hoteles.modelo_queries.Servicio_caracteristicas;

import java.util.Collection;

public interface Servicio_caracteristicasRepository extends JpaRepository <Servicio_caracteristicas, String> {
    
    //RFC4 - Get service by traits
    @Query(value="SELECT DISTINCT TS.nombre, S.capacidad, S.tipo_cobro, S.precio\r\n" + //
        "FROM tipos_servicio TS\r\n" + //
        "LEFT JOIN consumen C ON C.servicio = TS.id_tipo\r\n" + //
        "INNER JOIN servicios S ON S.tipo = TS.id_tipo\r\n" + //
        "WHERE ((:precio_menor IS NULL AND :precio_mayor IS NULL) OR (S.precio BETWEEN :precio_menor AND :precio_mayor))\r\n" + //
        "AND ((:fecha_inicio IS NULL AND :fecha_fin IS NULL) OR (C.fecha BETWEEN :fecha_inicio AND :fecha_fin))\r\n" + //
        "AND ((:registrado_por IS NULL) OR (:registrado_por = registrado_por))\r\n" + //
        "AND ((:tipo_menor IS NULL AND :tipo_mayor IS NULL) OR (TS.id_tipo BETWEEN :tipo_menor AND :tipo_mayor))", nativeQuery = true)
    Collection<Servicio_caracteristicas> darServicios_caracteristicas(@Param("precio_menor") Double precio_menor, @Param("precio_mayor") Double precio_mayor,
    @Param("fecha_inicio") String fecha_incio, @Param("fecha_fin") String fecha_fin, @Param("registrado_por") String registrado_por, @Param("tipo_menor") Integer tipo_menor, @Param("tipo_mayor") Integer tipo_mayor);
}
