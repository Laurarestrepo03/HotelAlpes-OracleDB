package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.hoteles.modelo_queries.Fecha;

import java.util.Collection;
import java.sql.Date;

public interface FechaRepository extends JpaRepository<Fecha, Date> {

    //RFC6.1 - Get highest occupation dates
    @Query(value="WITH Fechas (fecha_reserva) AS (\r\n" + //
        "SELECT MIN(fecha_entrada)\r\n" + //
        "FROM reservas\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fecha_reserva + 1\r\n" + //
        "FROM Fechas\r\n" + //
        "WHERE fecha_reserva + 1 <= (SELECT MAX(fecha_salida) FROM reservas)\r\n" + //
        ")\r\n" + //
        "SELECT fecha_reserva AS fecha, COUNT(*) AS cantidad\r\n" + //
        "FROM Fechas\r\n" + //
        "INNER JOIN reservas ON fecha_reserva BETWEEN fecha_entrada AND fecha_salida\r\n" + //
        "GROUP BY (fecha_reserva, habitacion)\r\n" + //
        "ORDER BY cantidad DESC, fecha_reserva DESC\r\n" + //
        "FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
    Collection<Fecha> darFechasMayorOcupacion();

    //RFC6.2 - Get highest consumption dates
    @Query(value="SELECT C.fecha, COUNT(C.fecha) AS cantidad\r\n" + //
        "FROM consumen C\r\n" + //
        "GROUP BY (C.fecha)\r\n" + //
        "ORDER BY cantidad DESC, C.fecha DESC\r\n" + //
        "FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
    Collection<Fecha> darFechasMayorIngreso();

    //RFC6.3 - Get lowest occupation dates
    @Query(value="WITH Fechas (fecha_reserva) AS (\r\n" + //
        "SELECT MIN(fecha_entrada)\r\n" + //
        "FROM reservas\r\n" + //
        "UNION ALL\r\n" + //
        "SELECT fecha_reserva + 1\r\n" + //
        "FROM Fechas\r\n" + //
        "WHERE fecha_reserva + 1 <= (SELECT MAX(fecha_salida) FROM reservas)\r\n" + //
        ")\r\n" + //
        "SELECT fecha_reserva AS fecha, COUNT(*) AS cantidad\r\n" + //
        "FROM Fechas\r\n" + //
        "INNER JOIN reservas ON fecha_reserva BETWEEN fecha_entrada AND fecha_salida\r\n" + //
        "GROUP BY (fecha_reserva, habitacion)\r\n" + //
        "ORDER BY cantidad ASC, fecha_reserva DESC\r\n" + //
        "FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
    Collection<Fecha> darFechasMenorOcupacion();
    
}
