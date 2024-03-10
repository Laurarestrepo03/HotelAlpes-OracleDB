package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Usuario;

import java.util.Collection;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    //Read
    @Query(value = "SELECT * FROM usuarios FETCH FIRST 50 ROWS ONLY", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    //Read
    @Query(value = "SELECT * FROM usuarios WHERE num_documento = :num_documento", nativeQuery = true)
    Usuario darUsuario(@Param("num_documento") String num_documento);

    //Read - Get clients
    @Query(value = "SELECT * FROM usuarios WHERE tipo = 1", nativeQuery = true)
    Collection<Usuario> darClientes();

    //Read - Get employees
    @Query(value = "SELECT * FROM usuarios WHERE tipo = 3", nativeQuery = true)
    Collection<Usuario> darEmpleados();

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE num_documento = :num_documento", nativeQuery = true)
    void eliminarUsuario(@Param("num_documento") String num_documento);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET num_documento = :num_documento_actualizado, tipo_documento = :tipo_documento, nombre = :nombre, correo = :correo, tipo = :tipo WHERE num_documento = :num_documento", nativeQuery = true)
    void actualizarUsuario(@Param("num_documento") String num_documento, @Param("num_documento_actualizado") String num_documento_actualizado, @Param("tipo_documento") String tipo_documento, @Param("nombre") String nombre, @Param("correo") String correo, @Param("tipo") Integer tipo);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (num_documento, tipo_documento, nombre, correo, tipo) VALUES (:num_documento, :tipo_documento, :nombre, :correo, :tipo)", nativeQuery = true)
    void insertarUsuario(@Param("num_documento") String num_documento, @Param("tipo_documento") String tipo_documento, @Param("nombre") String nombre, @Param("correo") String correo, @Param("tipo") Integer tipo);
    
}
