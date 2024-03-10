package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Servicio_renta;
import uniandes.edu.co.hoteles.modelo.Servicio_rentaPK;

import java.util.Collection;

public interface Servicio_rentaRepository extends JpaRepository<Servicio_renta, Servicio_rentaPK> {

    //Read
    @Query(value = "SELECT * FROM servicios_renta", nativeQuery = true)
    Collection<Servicio_renta> darServiciosRenta();

    //Read
    @Query(value = "SELECT * FROM servicios_renta WHERE tipo = :tipo", nativeQuery = true)
    Servicio_renta darServicioRenta(@Param("tipo") Integer tipo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM servicios_renta WHERE tipo = :tipo", nativeQuery = true)
    void eiliminarServicioRenta(@Param("tipo") Integer tipo);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE servicios_renta SET tipo = :tipo_actualizado, costo_adicional = :costo_adicional, costo_por_hora = :costo_por_hora WHERE tipo = :tipo", nativeQuery = true)
    void actualizarServicioRenta(@Param("tipo")Integer tipo, @Param("tipo_actualizado") Integer tipo_actualizado, @Param("costo_adicional") Double costo_adicional, @Param("costo_por_hora") Double costo_por_hora);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO servicios_renta (tipo, costo_adicional, costo_por_hora) VALUES (:tipo, :costo_adicional, :costo_por_hora)", nativeQuery = true)
    void insertarServicioRenta(@Param("tipo")Integer tipo, @Param("costo_adicional") Double costo_adicional, @Param("costo_por_hora") Double costo_por_hora);
    
}
