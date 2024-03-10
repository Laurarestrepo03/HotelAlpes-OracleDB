package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Dotacion;

import java.util.Collection;

public interface DotacionRepository extends JpaRepository<Dotacion, Integer>  {

    //Read
    @Query(value = "SELECT * FROM dotaciones", nativeQuery = true)
    Collection<Dotacion> darDotaciones();

    //Read
    @Query(value = "SELECT * FROM dotaciones WHERE id_dotacion = :id_dotacion", nativeQuery = true)
    Dotacion darDotacion(@Param("id_dotacion") long id_dotacion);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM dotaciones WHERE id_dotacion = :id_dotacion", nativeQuery = true)
    void eliminarDotacion(@Param("id_dotacion") long id_dotacion);
    
    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE dotaciones SET nombre = :nombre WHERE id_dotacion = :id_dotacion", nativeQuery = true)
    void actualizarDotacion(@Param("id_dotacion") long id_dotacion, @Param("nombre") String nombre);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO dotaciones (id_dotacion, nombre) VALUES (id_dotacion.nextval, :nombre)", nativeQuery = true)
    void insertarDotacion(@Param("nombre") String nombre);
}
