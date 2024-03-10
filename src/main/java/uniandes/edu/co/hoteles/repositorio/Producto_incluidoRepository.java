package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Producto_incluido;
import uniandes.edu.co.hoteles.modelo.Producto_incluidoPK;

import java.util.Collection;

public interface Producto_incluidoRepository extends JpaRepository<Producto_incluido, Producto_incluidoPK> {

    //Read
    @Query(value = "SELECT * FROM productos_incluidos", nativeQuery = true)
    Collection<Producto_incluido> darProductosIncluidos();

    //Read
    @Query(value = "SELECT * FROM productos_incluidos WHERE plan_consumo = :plan_consumo AND producto = :producto", nativeQuery = true)
    Producto_incluido darProductoIncluido(@Param("plan_consumo") Integer plan_consumo, @Param("producto") Integer producto);
    
    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos_incluidos WHERE plan_consumo = :plan_consumo AND producto = :producto", nativeQuery = true)
    void eliminarProductoInlcuido(@Param("plan_consumo") Integer plan_consumo, @Param("producto") Integer producto);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos_incluidos SET plan_consumo = :plan_consumo_actualizado, producto = :producto_actualizado WHERE plan_consumo = :plan_consumo AND producto = :producto", nativeQuery = true)
    void actualizarProductoIncluido(@Param("plan_consumo") Integer plan_consumo, @Param("producto") Integer producto, @Param("plan_consumo_actualizado") Integer servicio_comercial_actualizado, @Param("producto_actualizado") Integer producto_actualizado);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos_incluidos (plan_consumo, producto) VALUES (:plan_consumo, :producto)", nativeQuery = true)
    void insertarProductoIncluido(@Param("plan_consumo") Integer plan_consumo, @Param("producto") Integer producto);
    
}
