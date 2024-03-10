package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Reserva;

import java.util.Collection;
import java.sql.Date;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    //Read
    @Query(value = "SELECT * FROM reservas FETCH FIRST 50 ROWS ONLY", nativeQuery = true)
    Collection<Reserva> darReservas();
    
    //Read
    @Query(value = "SELECT * FROM reservas WHERE codigo_reserva = :codigo_reserva", nativeQuery = true)
    Reserva darReserva(@Param("codigo_reserva") Integer codigo_reserva);

    //Read - Check room availabity
    @Query(value = "SELECT * FROM reservas WHERE habitacion = :habitacion AND fecha_entrada <= :fecha_salida AND fecha_salida >= :fecha_entrada", nativeQuery = true)
    Collection<Reserva> darReservasHabitacionFechas(@Param("habitacion") Integer num_habitacion, @Param("fecha_entrada") Date fecha_entrada, @Param("fecha_salida") Date fecha_salida);

    //Read - Check if user has reservation between dates
    @Query(value = "SELECT * FROM reservas R WHERE R.cliente_reserva = :cliente AND :fecha_consumo >= R.fecha_entrada AND :fecha_consumo <= R.fecha_salida", nativeQuery = true)
    Collection<Reserva> darReservasUsuario(@Param("cliente") String cliente, @Param("fecha_consumo") Date fecha_consumo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservas WHERE codigo_reserva = :codigo_reserva",  nativeQuery = true)
    void eliminarReserva(@Param("codigo_reserva") Integer codigo_reserva);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservas SET fecha_entrada = :fecha_entrada, fecha_salida = :fecha_salida, num_huespedes = :num_huespedes, estado = :estado, cliente_reserva = :cliente_reserva, habitacion = :habitacion, plan_consumo = :plan_consumo WHERE codigo_reserva = :codigo_reserva", nativeQuery = true)
    void actualizarReserva(@Param("codigo_reserva") Integer codigo_reserva, @Param("fecha_entrada") Date fecha_entrada, @Param("fecha_salida") Date fecha_salida, @Param("num_huespedes") Integer num_huespedes, @Param("estado") String estado, @Param("cliente_reserva") String cliente_reserva, @Param("habitacion") Integer habitacion, @Param("plan_consumo") Integer plan_consumo);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservas (codigo_reserva, fecha_entrada, fecha_salida, num_huespedes, estado, cliente_reserva, habitacion, plan_consumo) VALUES (codigo_reserva.nextval, :fecha_entrada, :fecha_salida, :num_huespedes, :estado, :cliente_reserva, :habitacion, :plan_consumo)", nativeQuery = true)
    void insertarReserva(@Param("fecha_entrada") Date fecha_entrada, @Param("fecha_salida") Date fecha_salida, @Param("num_huespedes") Integer num_huespedes, @Param("estado") String estado, @Param("cliente_reserva") String cliente_reserva, @Param("habitacion") Integer habitacion, @Param("plan_consumo") Integer plan_consumo);

}
