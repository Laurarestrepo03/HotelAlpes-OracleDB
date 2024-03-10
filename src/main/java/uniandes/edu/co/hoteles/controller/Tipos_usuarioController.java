package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.hoteles.modelo.Tipo_usuario;
import uniandes.edu.co.hoteles.repositorio.Tipo_usuarioRepository;

@Controller
public class Tipos_usuarioController {

    @Autowired
    private Tipo_usuarioRepository tipo_usuarioRepository;

    @GetMapping("/tiposUsuario")
    public String tiposUsuario(Model model){
        model.addAttribute("tiposUsuario", tipo_usuarioRepository.darTiposUsuario());
        return "tiposUsuario";
    }

    @GetMapping("/tiposUsuario/new")
    public String tipoUsuarioForm(Model model){
        model.addAttribute("tipoUsuario", new Tipo_usuario());
        return "tipoUsuarioNuevo";
    }

    @PostMapping("/tiposUsuario/new/save")
    public String tipoUsuarioGuardar(@ModelAttribute Tipo_usuario tipo_usuario, String rol){
        tipo_usuarioRepository.insertarTipoUsuario(tipo_usuario.getNombre());
        return "redirect:/tiposUsuario?rol="+rol;
    }

    @GetMapping("/tiposUsuario/{id_tipo}/edit")
    public String tipoUsuarioEditarForm(@PathVariable("id_tipo") int id_tipo, String rol, Model model){
        Tipo_usuario tipo_usuario = tipo_usuarioRepository.darTipoUsuario(id_tipo);
        if (tipo_usuario != null){
            model.addAttribute("tipoUsuario", tipo_usuario);
            return "tipoUsuarioEditar";
        }
        else {
            return "redirect:/tiposUsuario?rol="+rol;
        }
    }

    @PostMapping("/tiposUsuario/{id_tipo}/edit/save")
    public String tipoUsuarioEditarGuardar(@PathVariable("id_tipo") int id_tipo, @ModelAttribute Tipo_usuario tipo_usuario, 
    String rol){
        tipo_usuarioRepository.actualizarTipoUsuario(id_tipo, tipo_usuario.getNombre());
        return "redirect:/tiposUsuario?rol="+rol;
    }
    
    @GetMapping("/tiposUsuario/{id_tipo}/delete")
    public String tipoUsuarioEliminar(@PathVariable("id_tipo") int id_tipo, String rol){
        tipo_usuarioRepository.eliminarTipoUsuario(id_tipo);
        return "redirect:/tiposUsuario?rol="+rol;
    }    
}
