package uniandes.edu.co.hoteles.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Servicio_comercial;
import uniandes.edu.co.hoteles.modelo.Servicio_comercialPK;

public interface Servicio_comercialRepository extends JpaRepository<Servicio_comercial, Servicio_comercialPK> {

    //Read
    @Query(value = "SELECT * FROM servicios_comerciales", nativeQuery = true)
    Collection<Servicio_comercial> darServiciosComerciales();

    //Read
    @Query(value = "SELECT * FROM servicios_comerciales WHERE tipo = :tipo", nativeQuery = true)
    Servicio_comercial darServicioComercial(@Param("tipo") Integer tipo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios_comerciales WHERE tipo = :tipo", nativeQuery = true)
    void eiliminarServicioComercial(@Param("tipo") Integer tipo);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios_comerciales SET tipo = :tipo_actualizado, estilo = :estilo, requiere_reserva = :requiere_reserva WHERE tipo = :tipo", nativeQuery = true)
    void actualizarServicioComercial(@Param("tipo")Integer tipo, @Param("tipo_actualizado") Integer tipo_actualizado, @Param("estilo") String estilo, @Param("requiere_reserva") String requiere_reserva);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios_comerciales (tipo, estilo, requiere_reserva) VALUES (:tipo, :estilo, :requiere_reserva)", nativeQuery = true)
    void insertarServicioComercial(@Param("tipo")Integer tipo, @Param("estilo") String estilo, @Param("requiere_reserva") String requiere_reserva);
    
}
