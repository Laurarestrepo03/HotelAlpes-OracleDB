package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Semana_servicioRepository;

@Controller
public class Semana_servicioController {

    @Autowired
    private Semana_servicioRepository semana_servicioRepository;

    @GetMapping("/servicios/masConsumidos")
    public String masConsumidos(Model model){
        model.addAttribute("semanas", semana_servicioRepository.darServiciosMasConsumidos());
        return "masConsumidos";
    }

    @GetMapping("/servicios/menosConsumidos")
    public String menosConsumidos(Model model){
        model.addAttribute("semanas", semana_servicioRepository.darServiciosMenosConsumidos());
        return "menosConsumidos";
    }
    
}
