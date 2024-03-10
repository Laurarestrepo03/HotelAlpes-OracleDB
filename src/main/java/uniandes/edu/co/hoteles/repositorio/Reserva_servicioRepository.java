package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Reserva_servicio;

import java.util.Collection;
import java.sql.Date;

public interface Reserva_servicioRepository extends JpaRepository<Reserva_servicio, Integer> {

    //Read
    @Query(value = "SELECT * FROM reservas_servicio FETCH FIRST 50 ROWS ONLY", nativeQuery = true)
    Collection<Reserva_servicio> darReservasServicio();

    //Read
    @Query(value = "SELECT * FROM reservas_servicio WHERE id_reserva_servicio = :id_reserva_servicio", nativeQuery = true)
    Reserva_servicio darReservaServicio(@Param("id_reserva_servicio") Integer id_reserva_servicio);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservas_servicio WHERE id_reserva_servicio = :id_reserva_servicio", nativeQuery = true)
    void eliminarReservaServicio(@Param("id_reserva_servicio") Integer id_reserva_servicio);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE reservas_servicio SET dia_reserva = :dia_reserva, hora_inicio = :hora_inicio, hora_fin = :hora_fin, servicio_comercial = :servicio_comercial, servicio_renta = :servicio_renta, cliente = :cliente WHERE id_reserva_servicio = :id_reserva_servicio", nativeQuery = true)
    void actualizarReservaServicio(@Param("id_reserva_servicio") Integer id_reserva_servicio, @Param("dia_reserva") Date dia_reserva, @Param("hora_inicio") String hora_inicio, @Param("hora_fin") String hora_fin, @Param("servicio_comercial") Integer servicio_comercial, @Param("servicio_renta") Integer servicio_renta, @Param("cliente") String cliente);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservas_servicio (id_reserva_servicio, dia_reserva, hora_inicio, hora_fin, servicio_comercial, servicio_renta, cliente) VALUES (id_reserva_servicio.nextval, :dia_reserva, :hora_inicio, :hora_fin, :servicio_comercial, :servicio_renta, :cliente)", nativeQuery = true)
    void insertarReservaServicio(@Param("dia_reserva") Date dia_reserva, @Param("hora_inicio") String hora_inicio, @Param("hora_fin") String hora_fin, @Param("servicio_comercial") Integer servicio_comercial, @Param("servicio_renta") Integer servicio_renta, @Param("cliente") String cliente);
    
}
