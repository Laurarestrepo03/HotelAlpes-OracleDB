package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Dotacion_incluida;
import uniandes.edu.co.hoteles.modelo.Dotacion_incluidaPK;

import java.util.Collection;

public interface Dotacion_incluidaRepository extends JpaRepository<Dotacion_incluida, Dotacion_incluidaPK> {

    //Read
    @Query(value = "SELECT * FROM dotaciones_incluidas", nativeQuery = true)
    Collection<Dotacion_incluida> darDotacionesIncluidas();

    //Read
    @Query(value = "SELECT * FROM dotaciones_incluidas WHERE tipo_habitacion = :tipo_habitacion AND dotacion = :dotacion", nativeQuery = true)
    Dotacion_incluida darDotacionIncluida(@Param("tipo_habitacion") Integer tipo_habitacion, @Param("dotacion") Integer dotacion);

    //Read - Get endowment by room type
    @Query(value = "SELECT DI.tipo_habitacion, DI.dotacion, DI.cantidad\r\n" + //
        "FROM dotaciones_incluidas DI\r\n" + //
        "INNER JOIN tipos_habitacion TH ON DI.tipo_habitacion = TH.id_tipo\r\n" + //
        "WHERE TH.id_tipo = :tipo_habitacion", nativeQuery = true)
        Collection<Dotacion_incluida> darDotacionIncluidaTipoHabitacion(@Param("tipo_habitacion") Integer tipo_habitacion);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM dotaciones_incluidas WHERE tipo_habitacion = :tipo_habitacion AND dotacion = :dotacion", nativeQuery = true)
    void eliminarDotacionIncluida(@Param("tipo_habitacion") Integer tipo_habitacion, @Param("dotacion") Integer dotacion);

    //Delete (by room type)
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM dotaciones_incluidas WHERE tipo_habitacion = :tipo_habitacion", nativeQuery = true)
    void eliminarDotacionIncluidaPorTipo(@Param("tipo_habitacion") Integer tipo_habitacion);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE dotaciones_incluidas SET dotacion = :dotacion_actualizado, cantidad = :cantidad WHERE tipo_habitacion = :tipo_habitacion AND dotacion = :dotacion", nativeQuery = true)
    void actualizarDotacionIncluida(@Param("tipo_habitacion") Integer tipo_habitacion, @Param("dotacion") Integer dotacion, @Param("dotacion_actualizado") Integer dotacion_actualizado, @Param("cantidad") Integer cantidad);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO dotaciones_incluidas (tipo_habitacion, dotacion, cantidad) VALUES (:tipo_habitacion, :dotacion, :cantidad)", nativeQuery = true)
    void insertaDotacionIncluida(@Param("tipo_habitacion") Integer tipo_habitacion, @Param("dotacion") Integer dotacion, @Param("cantidad") Integer cantidad);
    
}
