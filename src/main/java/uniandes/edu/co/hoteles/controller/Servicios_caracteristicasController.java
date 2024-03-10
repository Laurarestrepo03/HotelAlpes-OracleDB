package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Servicio_caracteristicasRepository;

@Controller
public class Servicios_caracteristicasController {

    @Autowired
    private Servicio_caracteristicasRepository servicio_caracteristicasRepository;

    @GetMapping("/servicios/caracteristicas")
    public String serviciosCaracteristicas(Model model, Double precio_menor, Double precio_mayor, String fecha_inicio,
    String fecha_fin, String registrado_por, String categoria){

        Integer tipo_menor;
        Integer tipo_mayor;
        
        if (fecha_inicio == null || fecha_fin == null || fecha_inicio.equals("") || fecha_fin.equals("")) { 
            fecha_inicio = null;
            fecha_fin = null;
        }
        else {
            fecha_inicio = fecha_inicio.substring(8,10) + "/" + fecha_inicio.substring(5,7) + "/" + fecha_inicio.substring(0,4); 
            fecha_fin = fecha_fin.substring(8,10) + "/" + fecha_fin.substring(5,7) + "/" + fecha_fin.substring(0,4);    
        }

        if (categoria != null && categoria.equals("Bienestar")) {
            tipo_menor = 1;
            tipo_mayor = 3; 
        }
        else if (categoria != null && categoria.equals("Comercial")) {
            tipo_menor = 4;
            tipo_mayor = 9; 
        }
        else if (categoria != null &&  categoria.equals("Limpieza")) {
            tipo_menor = 10;
            tipo_mayor = 12;
        }
        else if (categoria != null && categoria.equals("Renta")) {
            tipo_menor = 13;
            tipo_mayor = 14;
        }
        else if (categoria != null && categoria.equals("Prestamo")) {
            tipo_menor = 15;
            tipo_mayor = 15;
        }
        else {
            tipo_menor = null;
            tipo_mayor = null;
        }

        model.addAttribute("serviciosCaracteristicas", servicio_caracteristicasRepository.darServicios_caracteristicas(precio_menor, precio_mayor, fecha_inicio, fecha_fin, registrado_por, tipo_menor, tipo_mayor));
        
        return "serviciosCaracteristicas";
    }
}
