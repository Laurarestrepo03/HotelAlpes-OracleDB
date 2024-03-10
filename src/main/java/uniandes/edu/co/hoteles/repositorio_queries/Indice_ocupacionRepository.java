package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.hoteles.modelo_queries.Indice_ocupacion;

import java.util.Collection;

public interface Indice_ocupacionRepository extends JpaRepository<Indice_ocupacion, Integer>{

    //RFC3 - Get occupation index by room
    @Query(value="WITH Fechas AS (\r\n" + //
        "SELECT (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) + LEVEL - 1 AS fecha_reserva\r\n" + //
        "FROM DUAL\r\n" + //
        "CONNECT BY (SELECT SYSDATE - INTERVAL '1' YEAR FROM DUAL) + LEVEL - 1 <= (SELECT SYSDATE FROM DUAL)\r\n" + //
        ")\r\n" + //
        "SELECT H.num_habitacion, COALESCE(ROUND((COUNT(DISTINCT fecha_reserva)/366)*100, 2), 0) porcentaje\r\n" + //
        "FROM Fechas\r\n" + //
        "INNER JOIN reservas R ON fecha_reserva BETWEEN R.fecha_entrada AND R.fecha_salida\r\n" + //
        "RIGHT JOIN habitaciones H ON H.num_habitacion = R.habitacion\r\n" + //
        "GROUP BY (H.num_habitacion)", nativeQuery = true)
    Collection<Indice_ocupacion> darIndices_ocupacion();

}
