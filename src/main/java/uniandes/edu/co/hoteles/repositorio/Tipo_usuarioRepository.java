package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Tipo_usuario;

import java.util.Collection;

public interface Tipo_usuarioRepository extends JpaRepository<Tipo_usuario, Integer>{

    //Read
    @Query(value = "SELECT * FROM tipos_usuario", nativeQuery = true)
    Collection<Tipo_usuario> darTiposUsuario();

    //Read
    @Query(value = "SELECT * FROM tipos_usuario WHERE id_tipo = :id_tipo", nativeQuery = true)
    Tipo_usuario darTipoUsuario(@Param("id_tipo") long id_tipo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipos_usuario WHERE id_tipo = :id_tipo", nativeQuery = true)
    void eliminarTipoUsuario(@Param("id_tipo") long id_tipo);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE tipos_usuario SET nombre = :nombre WHERE id_tipo = :id_tipo", nativeQuery = true)
    void actualizarTipoUsuario(@Param("id_tipo") long id_tipo, @Param("nombre") String nombre);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipos_usuario (id_tipo, nombre) VALUES (id_tipo_usuario.nextval, :nombre)", nativeQuery = true)
    void insertarTipoUsuario(@Param("nombre") String nombre);
    
}