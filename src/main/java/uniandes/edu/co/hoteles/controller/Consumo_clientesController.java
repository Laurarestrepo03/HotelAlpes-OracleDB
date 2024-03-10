package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio.Tipo_servicioRepository;
import uniandes.edu.co.hoteles.repositorio_queries.Consumo_clienteRepository;

@Controller
public class Consumo_clientesController {
    
    @Autowired
    private Consumo_clienteRepository consumo_clienteRepository;

    @Autowired
    private Tipo_servicioRepository tipo_servicioRepository;

    @GetMapping("/consumen/consumoClientes")
    public String consumoClientes(Model model, Integer servicio, String num_documento, String tipo_documento, String nombre, String correo, 
    String fecha_inicio, String fecha_fin, Integer veces_consumo){

        if (fecha_inicio == null || fecha_fin == null || fecha_inicio.equals("") || fecha_fin.equals("")) { 
            fecha_inicio = null;
            fecha_fin = null;
        }
        else {
            fecha_inicio = fecha_inicio.substring(8,10) + "/" + fecha_inicio.substring(5,7) + "/" + fecha_inicio.substring(0,4); 
            fecha_fin = fecha_fin.substring(8,10) + "/" + fecha_fin.substring(5,7) + "/" + fecha_fin.substring(0,4);    
        }

        if (veces_consumo != null && veces_consumo <= 0) {
            veces_consumo = 1;
        }
       
        model.addAttribute("servicios", tipo_servicioRepository.darTiposServicio());
        model.addAttribute("consumoClientes", consumo_clienteRepository.darConsumoclientes(servicio, num_documento, nombre, tipo_documento, correo, fecha_inicio, fecha_fin, veces_consumo));
        
        return "consumoClientes";
    }
}
