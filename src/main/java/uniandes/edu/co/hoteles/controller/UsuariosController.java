package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.hoteles.modelo.Usuario;
import uniandes.edu.co.hoteles.repositorio.Tipo_usuarioRepository;
import uniandes.edu.co.hoteles.repositorio.UsuarioRepository;

@Controller
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Tipo_usuarioRepository tipo_usuarioRepository;

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        return "usuarios";
    }

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tiposUsuario", tipo_usuarioRepository.darTiposUsuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario, String rol){
        usuarioRepository.insertarUsuario(usuario.getNum_documento(), usuario.getTipo_documento(), usuario.getNombre(),
        usuario.getCorreo(), usuario.getTipo().getId_tipo());
        return "redirect:/usuarios?rol="+rol;
    }

    @GetMapping("/usuarios/{num_documento}/edit")
    public String usuarioEditarForm(@PathVariable("num_documento") String num_documento, String rol, Model model){
        Usuario usuario = usuarioRepository.darUsuario(num_documento);
        if (usuario != null){
            model.addAttribute("usuario", usuario);
            model.addAttribute("tiposUsuario", tipo_usuarioRepository.darTiposUsuario());
            return "usuarioEditar";
        }
        else {
            return "redirect:/usuarios?rol="+rol;
        }
    }

    @PostMapping("/usuarios/{num_documento}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("num_documento") String num_documento, String rol, @ModelAttribute Usuario usuario){
        usuarioRepository.actualizarUsuario(num_documento, usuario.getNum_documento(), usuario.getTipo_documento(), usuario.getNombre(),
        usuario.getCorreo(), usuario.getTipo().getId_tipo());
        return "redirect:/usuarios?rol="+rol;
    }
    
    @GetMapping("/usuarios/{num_documento}/delete")
    public String usuarioEliminar(@PathVariable("num_documento") String num_documento, String rol){
        usuarioRepository.eliminarUsuario(num_documento);
        return "redirect:/usuarios?rol="+rol;
    }
 
}
