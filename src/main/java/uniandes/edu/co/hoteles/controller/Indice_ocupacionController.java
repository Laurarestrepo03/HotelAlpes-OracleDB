package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Indice_ocupacionRepository;

@Controller
public class Indice_ocupacionController {

    @Autowired
    private Indice_ocupacionRepository indice_ocupacionRepository;

    @GetMapping("/habitaciones/indiceOcupacion")
    public String indiceOcupacion(Model model){
        model.addAttribute("indiceOcupacion", indice_ocupacionRepository.darIndices_ocupacion());
        return "indiceOcupacion";
    }
    
}
