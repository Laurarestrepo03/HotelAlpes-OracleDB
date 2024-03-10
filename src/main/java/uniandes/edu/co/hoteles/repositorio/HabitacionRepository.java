package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Habitacion;

import java.util.Collection;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    //Read
    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    //Read
    @Query(value = "SELECT * FROM habitaciones WHERE num_habitacion = :num_habitacion", nativeQuery = true)
    Habitacion darHabitacion(@Param("num_habitacion") Integer num_habitacion);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE num_habitacion = :num_habitacion", nativeQuery = true)
    void eliminarHabitacion(@Param("num_habitacion") Integer num_habitacion);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET num_habitacion = :num_habitacion_actualizado, costo_noche = :costo_noche, tipo = :tipo WHERE num_habitacion = :num_habitacion", nativeQuery = true)
    void actualizarHabitacion(@Param("num_habitacion") Integer num_habitacion, @Param("num_habitacion_actualizado") Integer num_habitacion_actualizado, @Param("costo_noche") Double costo_noche, @Param("tipo") Integer tipo);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (num_habitacion, costo_noche, tipo) VALUES (:num_habitacion, :costo_noche, :tipo)", nativeQuery = true)
    void insertarHabitacion(@Param("num_habitacion") Integer num_habitacion, @Param("costo_noche") Double costo_noche, @Param("tipo") Integer tipo);
    
}
