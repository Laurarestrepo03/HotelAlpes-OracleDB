package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Poca_demandaRepository;

@Controller
public class Poca_demandaController {

    @Autowired
    private Poca_demandaRepository poca_demandaRepository;

    @GetMapping("/servicios/pocaDemanda")
    public String pocaDemanda(Model model){
        model.addAttribute("pocaDemanda", poca_demandaRepository.darServiciosPocaDemanda());
        return "pocaDemanda";
    }
}
