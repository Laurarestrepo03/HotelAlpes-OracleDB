package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio.Tipo_servicioRepository;
import uniandes.edu.co.hoteles.repositorio_queries.Ningun_consumoRepository;

@Controller
public class Ningun_consumoController {

    @Autowired
    private Ningun_consumoRepository ningun_consumoRepository;

    @Autowired
    private Tipo_servicioRepository tipo_servicioRepository;
    
    @GetMapping("/consumen/ningunConsumo")
    public String ningunConsumo(Model model, Integer servicio, String num_documento, String tipo_documento, String nombre, String correo, 
    String fecha_inicio, String fecha_fin){

        if (fecha_inicio == null || fecha_fin == null || fecha_inicio.equals("") || fecha_fin.equals("")) { 
            fecha_inicio = null;
            fecha_fin = null;
        }
        else {
            fecha_inicio = fecha_inicio.substring(8,10) + "/" + fecha_inicio.substring(5,7) + "/" + fecha_inicio.substring(0,4); 
            fecha_fin = fecha_fin.substring(8,10) + "/" + fecha_fin.substring(5,7) + "/" + fecha_fin.substring(0,4);    
        }

        model.addAttribute("servicios", tipo_servicioRepository.darTiposServicio());

        if (servicio != null) { 
            model.addAttribute("ningunConsumo", ningun_consumoRepository.darNingunConsumo(servicio, num_documento, nombre, tipo_documento, correo, fecha_inicio, fecha_fin));
        }
        
        return "ningunConsumo";
    }
}
