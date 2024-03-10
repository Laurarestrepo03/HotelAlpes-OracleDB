package uniandes.edu.co.hoteles.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hoteles.modelo.Plan_consumo;
import uniandes.edu.co.hoteles.modelo.Plan_consumoPK;

import java.util.Collection;

public interface Plan_consumoRepository extends JpaRepository<Plan_consumo, Plan_consumoPK> {

    //Read
    @Query(value = "SELECT * FROM planes_consumo", nativeQuery = true)
    Collection<Plan_consumo> darPlanesConsumo();

    //Read
    @Query(value = "SELECT * FROM planes_consumo WHERE tipo = :tipo", nativeQuery = true)
    Plan_consumo darPlanConsumo(@Param("tipo") Integer tipo);

    //Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planes_consumo WHERE tipo = :tipo", nativeQuery = true)
    void eliminarPlanConsumo(@Param("tipo") Integer tipo);

    //Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE planes_consumo SET tipo = :tipo_actualizado, genera_descuento = :genera_descuento, tiempo_estadia = :tiempo_estadia, valor_descuento_ser = :valor_descuento_ser WHERE tipo = :tipo", nativeQuery = true)
    void actualizarPlanConsumo(@Param("tipo") Integer tipo, @Param("tipo_actualizado") Integer tipo_actualizado, @Param("genera_descuento") String genera_descuento, @Param("tiempo_estadia") Integer tiempo_estadia, @Param("valor_descuento_ser") Double valor_descuento_ser);

    //Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planes_consumo (tipo, genera_descuento, tiempo_estadia, valor_descuento_ser) VALUES (:tipo, :genera_descuento, :tiempo_estadia, :valor_descuento_ser)", nativeQuery = true)
    void insertarPlanConsumo(@Param("tipo") Integer tipo, @Param("genera_descuento") String genera_descuento, @Param("tiempo_estadia") Integer tiempo_estadia, @Param("valor_descuento_ser") Double valor_descuento_ser);
    
}
