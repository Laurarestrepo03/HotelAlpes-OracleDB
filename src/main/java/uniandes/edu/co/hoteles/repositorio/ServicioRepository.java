package uniandes.edu.co.hoteles.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Servicio;
import uniandes.edu.co.hoteles.modelo.ServicioPK;

public interface ServicioRepository extends JpaRepository<Servicio, ServicioPK> {

    //Read
    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();

    //Read
    @Query(value = "SELECT * FROM servicios WHERE tipo = :tipo", nativeQuery = true)
    Servicio darServicio(@Param("tipo") Integer tipo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios WHERE tipo = :tipo", nativeQuery = true)
    void eliminarServicio(@Param("tipo") Integer tipo);
    
    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios SET tipo = :tipo_actualizado, capacidad = :capacidad, tipo_cobro = :tipo_cobro, precio = :precio WHERE tipo = :tipo", nativeQuery = true)
    void actualizarServicio(@Param("tipo") Integer tipo, @Param("tipo_actualizado") Integer tipo_actualizado, @Param("capacidad") Integer capacidad, @Param("tipo_cobro") String tipo_cobro, @Param("precio") Double precio);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios (tipo, capacidad, tipo_cobro, precio) VALUES (:tipo, :capacidad, :tipo_cobro, :precio)", nativeQuery = true)
    void insertarServicio(@Param("tipo") Integer tipo, @Param("capacidad") Integer capacidad, @Param("tipo_cobro") String tipo_cobro, @Param("precio") Double precio);
    
}