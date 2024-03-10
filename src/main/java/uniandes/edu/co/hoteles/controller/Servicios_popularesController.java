package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Servicio_popularRepository;

import java.sql.Date;

@Controller
public class Servicios_popularesController {
    
    @Autowired
    private Servicio_popularRepository servicio_popularRepository;

    @GetMapping("/servicios/populares")
    public String serviciosPopulares(Model model, Date fecha_inicio, Date fecha_fin){
        if (fecha_inicio != null && fecha_fin != null) {
            model.addAttribute("serviciosPopulares", servicio_popularRepository.darServicios_populares(fecha_inicio, fecha_fin));
        }
        return "serviciosPopulares";
    }

}
