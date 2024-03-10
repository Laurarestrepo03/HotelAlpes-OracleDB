package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.FechaRepository;

@Controller
public class FechasController {

    @Autowired
    private FechaRepository fechaRepository;

    @GetMapping("/habitaciones/fechasMayorOcupacion")
    public String fechasMayorOcupacion(Model model){
        model.addAttribute("fechas", fechaRepository.darFechasMayorOcupacion());
        return "fechasMayorOcupacion";
    }

    @GetMapping("/consumen/fechasMayorIngreso")
    public String fechasMayorIngreso(Model model){
        model.addAttribute("fechas", fechaRepository.darFechasMayorIngreso());
        return "fechasMayorIngreso";
    }

    @GetMapping("/habitaciones/fechasMenorOcupacion")
    public String fechasMenorOcupacion(Model model){
        model.addAttribute("fechas", fechaRepository.darFechasMenorOcupacion());
        return "fechasMenorOcupacion";
    }

}