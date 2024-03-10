package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Semana_habitacionRepository;

@Controller
public class Semana_habitacionController {

    @Autowired
    private Semana_habitacionRepository semana_habitacionRepository;

    @GetMapping("/habitaciones/masSolicitadas")
    public String masSolicitadas(Model model){
        model.addAttribute("semanas", semana_habitacionRepository.darHabitacionesMasSolicitadas());
        return "masSolicitadas";
    }

    @GetMapping("/habitaciones/menosSolicitadas")
    public String menosSolicitadas(Model model){
        model.addAttribute("semanas", semana_habitacionRepository.darHabitacionesMenosSolicitadas());
        return "menosSolicitadas";
    }
    
}
