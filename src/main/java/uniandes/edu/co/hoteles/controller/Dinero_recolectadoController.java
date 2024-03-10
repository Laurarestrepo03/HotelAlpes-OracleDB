package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Dinero_recoletadoRepository;

@Controller
public class Dinero_recolectadoController {

    @Autowired
    private Dinero_recoletadoRepository dinero_recoletadoRepository;

    @GetMapping("/habitaciones/dineroRecolectado")
    public String dineroRecolectado(Model model){
        model.addAttribute("dineroRecolectado", dinero_recoletadoRepository.darDineroRecolectado());
        return "dineroRecolectado";
    }
    
}
