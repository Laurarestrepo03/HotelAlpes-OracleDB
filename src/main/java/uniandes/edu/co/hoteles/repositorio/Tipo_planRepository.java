package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Tipo_plan;

import java.util.Collection;

public interface Tipo_planRepository extends JpaRepository<Tipo_plan, Integer> {

    //Read
    @Query(value = "SELECT * FROM tipos_plan", nativeQuery = true)
    Collection<Tipo_plan> darTiposPlan();

    //Read
    @Query(value = "SELECT * FROM tipos_plan WHERE id_tipo = :id_tipo", nativeQuery = true)
    Tipo_plan darTipoPlan(@Param("id_tipo") long id_tipo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipos_plan WHERE id_tipo = :id_tipo", nativeQuery = true)
    void eliminarTipoPlan(@Param("id_tipo") long id_tipo);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE tipos_plan SET nombre = :nombre WHERE id_tipo = :id_tipo", nativeQuery = true)
    void actualizarTipoPlan(@Param("id_tipo") long id_tipo, @Param("nombre") String nombre);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipos_plan (id_tipo, nombre) VALUES (id_tipo_plan.nextval, :nombre)", nativeQuery = true)
    void insertarTipoPlan(@Param("nombre") String nombre);
    
}
