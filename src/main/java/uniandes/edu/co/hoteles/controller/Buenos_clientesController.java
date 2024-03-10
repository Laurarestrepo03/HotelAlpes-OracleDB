package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Buen_clienteRepository;

@Controller
public class Buenos_clientesController {

    @Autowired
    private Buen_clienteRepository buen_clienteRepository;

    @GetMapping("/usuarios/buenosClientes")
    public String buenosClientes(Model model){
        model.addAttribute("buenosClientes", buen_clienteRepository.darBuenosClientes());
        return "buenosClientes";
    }
    
}
