package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.hoteles.repositorio.Tipo_usuarioRepository;

@Controller
public class HotelesController {

    @Autowired
    private Tipo_usuarioRepository tipo_usuarioRepository;

    @RequestMapping("/")
    public String roles(Model model){
        model.addAttribute("tiposUsuario", tipo_usuarioRepository.darTiposUsuario());
        return "roles";
    }

    @RequestMapping("/menu")
    public String index(){
        return "index";
    }
    
}
