package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Tipo_habitacion;

import java.util.Collection;

public interface Tipo_habitacionRepository extends JpaRepository<Tipo_habitacion, Integer> {

    //Read
    @Query(value = "SELECT * FROM tipos_habitacion", nativeQuery = true)
    Collection<Tipo_habitacion> darTiposHabitacion();

    //Read
    @Query(value = "SELECT * FROM tipos_habitacion WHERE id_tipo = :id_tipo", nativeQuery = true)
    Tipo_habitacion darTipoHabitacion(@Param("id_tipo") long id_tipo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipos_habitacion WHERE id_tipo = :id_tipo", nativeQuery = true)
    void eliminarTipoHabitacion(@Param("id_tipo") long id_tipo);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE tipos_habitacion SET nombre = :nombre, capacidad = :capacidad WHERE id_tipo = :id_tipo", nativeQuery = true)
    void actualizarTipoHabitacion(@Param("id_tipo") long id_tipo, @Param("nombre") String nombre, @Param("capacidad") Integer capacidad);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipos_habitacion (id_tipo, nombre, capacidad) VALUES (id_tipo_habitacion.nextval, :nombre, :capacidad)", nativeQuery = true)
    void insertarTipoHabitacion(@Param("nombre") String nombre, @Param("capacidad") Integer capacidad);
    
}
