package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.hoteles.modelo_queries.Buen_cliente;

import java.util.Collection;

public interface Buen_clienteRepository extends JpaRepository<Buen_cliente, String>{

    //RFC7 - Get good clients
    @Query(value="WITH Fechas AS (\r\n" + //
        "SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) + LEVEL - 1 AS fecha_reserva\r\n" + //
        "FROM DUAL\r\n" + //
        "CONNECT BY (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) + LEVEL - 1 <= (SELECT SYSDATE FROM DUAL)\r\n" + //
        ")\r\n" + //
        "SELECT U.num_documento AS cliente, ROUND(COUNT(DISTINCT CONCAT(U.num_documento, fecha_reserva))/7, 2) AS semanas, SC.suma_consumo\r\n" + //
        "FROM Fechas\r\n" + //
        "INNER JOIN reservas R ON fecha_reserva BETWEEN R.fecha_entrada AND R.fecha_salida\r\n" + //
        "INNER JOIN usuarios U ON R.cliente_reserva = U.num_documento\r\n" + //
        "INNER JOIN consumen C ON C.cliente = U.num_documento\r\n" + //
        "INNER JOIN servicios S ON S.tipo = C.servicio\r\n" + //
        "INNER JOIN Suma_Consumo SC ON U.num_documento = SC.cliente\r\n" + //
        "WHERE U.tipo = 1\r\n" + //
        "GROUP BY (U.num_documento, SC.suma_consumo)\r\n" + //
        "HAVING COUNT(DISTINCT CONCAT(U.num_documento, fecha_reserva)) >= 14 OR SC.suma_consumo > 15000000", nativeQuery = true)
    Collection<Buen_cliente> darBuenosClientes();
}
