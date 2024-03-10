package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hoteles.modelo_queries.Consumo_usuario;
import uniandes.edu.co.hoteles.modelo_queries.Consumo_usuarioPK;

import java.util.Collection;
import java.sql.Date;

public interface Consumo_usuarioRepository extends JpaRepository<Consumo_usuario, Consumo_usuarioPK> {

    //RFC5 - Get user consumption
    @Query(value="SELECT C.cliente usuario, TS.nombre as servicio, C.fecha, C.cuenta_pagada, C.hora, C.registrado_por\r\n" + //
        "FROM consumen C\r\n" + //
        "INNER JOIN tipos_servicio TS ON TS.id_tipo = C.servicio\r\n" + //
        "WHERE C.cliente = :usuario AND (C.fecha BETWEEN :fecha_inicio AND :fecha_fin)", nativeQuery = true)
    Collection<Consumo_usuario> darConsumosUsuario(@Param("usuario") String usuario, @Param("fecha_inicio") Date fecha_inicio, @Param("fecha_fin") Date fecha_fin);
    
} 