package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Acompaniante;
import uniandes.edu.co.hoteles.modelo.AcompaniantePK;

import java.util.Collection;

public interface AcompanianteRepository extends JpaRepository<Acompaniante, AcompaniantePK> {

    //Read
    @Query(value = "SELECT * FROM acompaniantes", nativeQuery = true)
    Collection<Acompaniante> darAcompaniantes();

    //Read
    @Query(value = "SELECT * FROM acompaniantes WHERE reserva = :reserva AND num_documento = :num_documento", nativeQuery = true)
    Acompaniante darAcompaniante(@Param("reserva") Integer reserva, @Param("num_documento") String num_documento);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM acompaniantes WHERE reserva = :reserva AND num_documento = :num_documento", nativeQuery = true)
    void eliminarAcompaniante(@Param("reserva") Integer reserva, @Param("num_documento") String num_documento);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE acompaniantes SET reserva = :reserva_actualizado, num_documento = :num_documento_actualizado, tipo_documento = :tipo_documento WHERE reserva = :reserva AND num_documento = :num_documento", nativeQuery = true)
    void actualizarAcompaniante(@Param("reserva") Integer reserva, @Param("num_documento") String num_documento, @Param("tipo_documento") String tipo_documento, @Param("reserva_actualizado") Integer reserva_actualizado, @Param("num_documento_actualizado") String num_documento_actualizado);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO acompaniantes (reserva, num_documento, tipo_documento) VALUES (:reserva, :num_documento, :tipo_documento)", nativeQuery = true)
    void insertarAcompaniante(@Param("reserva") Integer reserva, @Param("num_documento") String num_documento, @Param("tipo_documento") String tipo_documento);
    
}
