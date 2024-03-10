package uniandes.edu.co.hoteles.repositorio_queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.hoteles.modelo_queries.Dinero_recolectado;

import java.util.Collection;

public interface Dinero_recoletadoRepository extends JpaRepository<Dinero_recolectado, Integer> {

    //RFC1 - Get collected money by room
    @Query(value="SELECT H.num_habitacion, SUM(NVL(S.precio, 0)) AS dinero\r\n" + //
        "FROM habitaciones H\r\n" + //
        "LEFT JOIN reservas R ON R.habitacion = H.num_habitacion\r\n" + //
        "LEFT JOIN consumen C ON C.cliente = R.cliente_reserva\r\n" + //
        "LEFT JOIN servicios S ON S.tipo = C.servicio\r\n" + //
        "WHERE (C.fecha BETWEEN (SELECT TO_DATE('01/01/' || EXTRACT(YEAR FROM SYSDATE)) FROM DUAL) AND (SELECT SYSDATE FROM DUAL))OR R.habitacion IS NULL\r\n" + //
        "GROUP BY (H.num_habitacion)", nativeQuery = true)
    Collection<Dinero_recolectado> darDineroRecolectado();
    
}
