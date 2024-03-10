package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hoteles.modelo_queries.Ningun_consumo;

import java.util.Collection;

public interface Ningun_consumoRepository extends JpaRepository<Ningun_consumo, String> {

    //RFC10 - Get non-consumption by traits
    @Query(value="SELECT U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre tipo\r\n" + //
        "FROM usuarios U\r\n" + //
        "INNER JOIN tipos_usuario TU ON TU.id_tipo = U.tipo \r\n" + //
        "LEFT JOIN (\r\n" + //
        "SELECT *\r\n" + //
        "FROM consumen C\r\n" + //
        "WHERE C.servicio = :servicio\r\n" + //
        "AND ((:fecha_inicio IS NULL AND :fecha_fin IS NULL) OR (C.fecha BETWEEN :fecha_inicio AND :fecha_fin))\r\n" + //
        ") ClientesConsumo ON U.num_documento = ClientesConsumo.cliente\r\n" + //
        "WHERE ClientesConsumo.cliente IS NULL\r\n" + //
        "AND (:num_documento IS NULL OR U.num_documento = :num_documento)\r\n" + //
        "AND (:tipo_documento IS NULL OR U.tipo_documento = :tipo_documento)\r\n" + //
        "AND (:nombre IS NULL OR U.nombre = :nombre)\r\n" + //
        "AND (:correo IS NULL OR U.correo = :correo)\r\n" + //
        "AND (U.tipo = 1)", nativeQuery = true)
    Collection<Ningun_consumo> darNingunConsumo(@Param("servicio") Integer servicio, @Param("num_documento") String num_documento, @Param("nombre") String nombre, @Param("tipo_documento") String tipo_documento,
    @Param("correo") String correo, @Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin);
    
}
