package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Producto;

import java.util.Collection;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    //Read
    @Query(value = "SELECT * from productos", nativeQuery = true)
    Collection<Producto> darProductos();
    
    //Read
    @Query(value = "SELECT * FROM productos WHERE id_producto = :id_producto", nativeQuery = true)
    Producto darProducto(@Param("id_producto") Integer id_producto);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE id_producto = :id_producto", nativeQuery = true)
    void eliminarProducto(@Param("id_producto") Integer id_producto);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, precio = :precio WHERE id_producto = :id_producto", nativeQuery = true)
    void actualizarProducto(@Param("id_producto") Integer id_producto, @Param("nombre") String nombre, @Param("precio") Double precio);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (id_producto, nombre, precio) VALUES (id_producto.nextval, :nombre, :precio)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, @Param("precio") Double precio);
}