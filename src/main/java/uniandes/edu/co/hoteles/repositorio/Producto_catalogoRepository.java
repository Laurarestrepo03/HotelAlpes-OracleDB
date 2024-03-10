package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Producto_catalogo;
import uniandes.edu.co.hoteles.modelo.Producto_catalogoPK;

import java.util.Collection;

public interface Producto_catalogoRepository extends JpaRepository<Producto_catalogo, Producto_catalogoPK> {

    //Read
    @Query(value = "SELECT * FROM catalogo_productos", nativeQuery = true)
    Collection<Producto_catalogo> darCatalogoProductos();

    //Read
    @Query(value = "SELECT * FROM catalogo_productos WHERE servicio_comercial = :servicio_comercial AND producto = :producto", nativeQuery = true)
    Producto_catalogo darProductoCatalogo(@Param("servicio_comercial") Integer servicio_comercial, @Param("producto") Integer producto);
    
    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM catalogo_productos WHERE servicio_comercial = :servicio_comercial AND producto = :producto", nativeQuery = true)
    void eliminarProductoCatalogo(@Param("servicio_comercial") Integer servicio_comercial, @Param("producto") Integer producto);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE catalogo_productos SET servicio_comercial = :servicio_comercial_actualizado, producto = :producto_actualizado WHERE servicio_comercial = :servicio_comercial AND producto = :producto", nativeQuery = true)
    void actualizarproductoCatalogo(@Param("servicio_comercial") Integer servicio_comercial, @Param("producto") Integer producto, @Param("servicio_comercial_actualizado") Integer servicio_comercial_actualizado, @Param("producto_actualizado") Integer producto_actualizado);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO catalogo_productos (servicio_comercial, producto) VALUES (:servicio_comercial, :producto)", nativeQuery = true)
    void insertarProductoCatalogo(@Param("servicio_comercial") Integer servicio_comercial, @Param("producto") Integer producto);

}
