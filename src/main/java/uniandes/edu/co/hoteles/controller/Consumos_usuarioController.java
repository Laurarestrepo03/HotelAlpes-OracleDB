package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Consumo_usuarioRepository;

import java.sql.Date;

@Controller
public class Consumos_usuarioController {

    @Autowired
    private Consumo_usuarioRepository consumo_usuarioRepository;

    @GetMapping("/consumen/usuario")
    public String consumosUsuario(Model model, String usuario, Date fecha_inicio, Date fecha_fin){
        if (usuario != null && fecha_inicio != null && fecha_fin != null) {
            model.addAttribute("consumosUsuario", consumo_usuarioRepository.darConsumosUsuario(usuario, fecha_inicio, fecha_fin));
        }
        return "consumosUsuario";
    }

}