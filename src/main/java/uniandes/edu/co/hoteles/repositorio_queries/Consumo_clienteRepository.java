package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hoteles.modelo_queries.Consumo_cliente;
import uniandes.edu.co.hoteles.modelo_queries.Consumo_clientePK;

import java.util.Collection;

public interface Consumo_clienteRepository extends JpaRepository<Consumo_cliente, Consumo_clientePK> {

    //RFC9 - Get consumption by traits
    @Query(value="SELECT U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre tipo, COUNT(U.num_documento) veces_consumo, C.fecha\r\n" + //
        "FROM usuarios U\r\n" + //
        "INNER JOIN consumen C ON C.cliente = U.num_documento\r\n" + //
        "INNER JOIN tipos_usuario TU ON TU.id_tipo = U.tipo\r\n" + //
        "WHERE (C.servicio = :servicio)\r\n" + //
        "AND (:num_documento IS NULL OR U.num_documento = :num_documento)\r\n" + //
        "AND (:tipo_documento IS NULL OR U.tipo_documento = :tipo_documento)\r\n" + //
        "AND (:nombre IS NULL OR U.nombre = :nombre)\r\n" + //
        "AND (:correo IS NULL OR U.correo = :correo)\r\n" + //
        "AND ((:fecha_inicio IS NULL AND :fecha_fin IS NULL) OR (C.fecha BETWEEN :fecha_inicio AND :fecha_fin))\r\n" + //
        "AND (U.tipo = 1)\r\n" + //
        "GROUP BY U.num_documento, U.tipo_documento, U.nombre, U.correo, TU.nombre, C.fecha\r\n" + //
        "HAVING (:veces_consumo IS NULL AND COUNT(U.num_documento) >= 1) OR COUNT(U.num_documento) >= :veces_consumo", nativeQuery = true)
    Collection<Consumo_cliente> darConsumoclientes(@Param("servicio") Integer servicio, @Param("num_documento") String num_documento, @Param("nombre") String nombre, @Param("tipo_documento") String tipo_documento,
    @Param("correo") String correo, @Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin, @Param("veces_consumo") Integer veces_consumo);
    
}
