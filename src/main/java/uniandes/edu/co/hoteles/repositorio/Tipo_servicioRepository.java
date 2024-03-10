package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Tipo_servicio;

import java.util.Collection;

public interface Tipo_servicioRepository extends JpaRepository<Tipo_servicio, Integer> {

    //Read
    @Query(value = "SELECT * FROM tipos_servicio", nativeQuery = true)
    Collection<Tipo_servicio> darTiposServicio();

    //Read
    @Query(value = "SELECT * FROM tipos_servicio WHERE id_tipo = :id_tipo", nativeQuery = true)
    Tipo_servicio darTipoServicio(@Param("id_tipo") long id_tipo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipos_servicio WHERE id_tipo = :id_tipo", nativeQuery = true)
    void eliminarTipoServicio(@Param("id_tipo") long id_tipo);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE tipos_servicio SET nombre = :nombre WHERE id_tipo = :id_tipo", nativeQuery = true)
    void actualizarTipoServicio(@Param("id_tipo") long id_tipo, @Param("nombre") String nombre);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipos_servicio (id_tipo, nombre) VALUES (id_tipo_servicio.nextval, :nombre)", nativeQuery = true)
    void insertarTipoServicio(@Param("nombre") String nombre);
    
}
