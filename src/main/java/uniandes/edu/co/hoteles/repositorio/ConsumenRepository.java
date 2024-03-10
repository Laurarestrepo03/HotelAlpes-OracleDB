package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Consumen;
import uniandes.edu.co.hoteles.modelo.ConsumenPK;

import java.util.Collection;
import java.sql.Date;

public interface ConsumenRepository extends JpaRepository<Consumen, ConsumenPK> {

    //Read
    @Query(value = "SELECT * FROM consumen FETCH FIRST 50 ROWS ONLY", nativeQuery = true)
    Collection<Consumen> darConsumen();

    //Read
    @Query(value = "SELECT * FROM consumen WHERE cliente = :cliente AND servicio = :servicio AND fecha = :fecha AND hora = :hora", nativeQuery = true)
    Consumen darConsume(@Param("cliente") String cliente, @Param("servicio") long servicio, @Param("fecha") Date fecha, @Param("hora") String hora);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM consumen WHERE cliente = :cliente AND servicio = :servicio AND fecha = :fecha AND hora = :hora", nativeQuery = true)
    void eliminarConsume(@Param("cliente") String cliente, @Param("servicio") long servicio, @Param("fecha") Date fecha, @Param("hora") String hora);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE consumen SET cliente = :cliente_actualizado, servicio = :servicio_actualizado, fecha = :fecha_actualizado, hora = :hora_actualizado, cuenta_pagada = :cuenta_pagada, registrado_por = :registrado_por WHERE cliente = :cliente AND servicio = :servicio AND fecha = :fecha AND hora = :hora", nativeQuery = true)
    void actualizarConsume(@Param("cliente") String cliente, @Param("servicio") long servicio, @Param("fecha") Date fecha, @Param("hora") String hora, @Param("cliente_actualizado") String cliente_actualizado, @Param("servicio_actualizado") long servicio_actualizado, @Param("fecha_actualizado") Date fecha_actualizado, @Param("hora_actualizado") String hora_actualizado, @Param("cuenta_pagada") String cuenta_pagada, @Param("registrado_por") String registrado_por);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO consumen (cliente, servicio, fecha, hora, cuenta_pagada, registrado_por) VALUES (:cliente, :servicio, :fecha, :hora, :cuenta_pagada, :registrado_por)", nativeQuery = true)
    void insertarConsume(@Param("cliente") String cliente, @Param("servicio") long servicio, @Param("fecha") Date fecha, @Param("hora") String hora, @Param("cuenta_pagada") String cuenta_pagada, @Param("registrado_por") String registrado_por);
    
}
