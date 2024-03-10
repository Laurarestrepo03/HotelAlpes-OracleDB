package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hoteles.modelo.Dotacion;
import uniandes.edu.co.hoteles.modelo.Dotacion_incluida;
import uniandes.edu.co.hoteles.modelo.Dotacion_incluidaPK;
import uniandes.edu.co.hoteles.modelo.Tipo_habitacion;
import uniandes.edu.co.hoteles.repositorio.DotacionRepository;
import uniandes.edu.co.hoteles.repositorio.Dotacion_incluidaRepository;
import uniandes.edu.co.hoteles.repositorio.Tipo_habitacionRepository;

@Controller
public class Dotaciones_incluidasController {

    @Autowired
    private Tipo_habitacionRepository tipo_habitacionRepository;

    @Autowired
    private Dotacion_incluidaRepository dotacion_incluidaRepository;

    @Autowired
    private DotacionRepository dotacionRepository;

    @GetMapping("/tiposHabitacion/{id_tipo}/dotacion")
    public String dotacionesIncluidas(@PathVariable("id_tipo") int id_tipo, Model model){
        model.addAttribute("dotacionesIncluidas", dotacion_incluidaRepository.darDotacionIncluidaTipoHabitacion(id_tipo));
        model.addAttribute("tipo_habitacion", tipo_habitacionRepository.darTipoHabitacion(id_tipo));
        return "dotacionesIncluidas";
    } 

    @GetMapping("/tiposHabitacion/{id_tipo}/dotacion/new")
    public String dotacionIncluidaForm(@PathVariable("id_tipo") Integer id_tipo, Model model){
        model.addAttribute("dotacionesIncluidas", dotacion_incluidaRepository.darDotacionIncluidaTipoHabitacion(id_tipo));
        model.addAttribute("dotacionIncluida", new Dotacion_incluida());
        model.addAttribute("dotaciones", dotacionRepository.darDotaciones());
        model.addAttribute("tipo_habitacion", tipo_habitacionRepository.darTipoHabitacion(id_tipo));
        return "dotacionIncluidaNuevo";
    }

    @PostMapping("/tiposHabitacion/{id_tipo}/dotacion/new/save")
    public String dotacionIncluidaGuardar(@PathVariable("id_tipo") int id_tipo, String rol, @ModelAttribute Dotacion_incluida dotacion_incluida, @ModelAttribute("dotacion") Integer id_dotacion_pk){
        Tipo_habitacion tipoHabitacionPK = tipo_habitacionRepository.darTipoHabitacion(id_tipo);
        Dotacion dotacionPK = dotacionRepository.darDotacion(id_dotacion_pk);
        Dotacion_incluidaPK pk = new Dotacion_incluidaPK(tipoHabitacionPK, dotacionPK);
        dotacion_incluida.setPk(pk);
        dotacion_incluidaRepository.insertaDotacionIncluida(dotacion_incluida.getPk().getTipo_habitacion().getId_tipo(), dotacion_incluida.getPk().getDotacion().getId_dotacion(), 
        dotacion_incluida.getCantidad());
        return "redirect:/tiposHabitacion/{id_tipo}/dotacion?rol="+rol;
    }

    @GetMapping("/tiposHabitacion/{id_tipo}/dotacion/{id_dotacion}/edit")
    public String dotacionIncluidaEditarForm(@PathVariable("id_tipo") int id_tipo, String rol, @PathVariable("id_dotacion") int id_dotacion, Model model){
        Dotacion_incluida dotacion_incluida = dotacion_incluidaRepository.darDotacionIncluida(id_tipo, id_dotacion);
        if (dotacion_incluida != null){
            model.addAttribute("dotacionIncluida", dotacion_incluida);
            model.addAttribute("dotaciones", dotacionRepository.darDotaciones());
            model.addAttribute("tipo_habitacion", tipo_habitacionRepository.darTipoHabitacion(id_tipo));
            return "dotacionIncluidaEditar";
        }
        else {
            return "redirect:/tiposHabitacion/{id_tipo}/dotacion?rol="+rol;
        }
    } 

    @PostMapping("/tiposHabitacion/{id_tipo}/dotacion/{id_dotacion}/edit/save")
    public String dotacionIncluidaEditarGuardar(@PathVariable("id_tipo") int id_tipo, String rol, @PathVariable("id_dotacion") int id_dotacion, @ModelAttribute Dotacion_incluida dotacion_incluida, 
    @ModelAttribute("dotacion") Integer id_dotacion_pk){
        Tipo_habitacion tipoHabitacionPK = tipo_habitacionRepository.darTipoHabitacion(id_tipo);
        Dotacion dotacionPK = dotacionRepository.darDotacion(id_dotacion_pk);
        Dotacion_incluidaPK pk = new Dotacion_incluidaPK(tipoHabitacionPK, dotacionPK);
        dotacion_incluida.setPk(pk);
        
        dotacion_incluidaRepository.actualizarDotacionIncluida(id_tipo, id_dotacion, 
        dotacion_incluida.getPk().getDotacion().getId_dotacion(), dotacion_incluida.getCantidad());
        return "redirect:/tiposHabitacion/{id_tipo}/dotacion?rol="+rol;
    }

    @GetMapping("/tiposHabitacion/{id_tipo}/dotacion/{id_dotacion}/delete")
    public String dotacionIncluidaEliminar(@PathVariable("id_tipo") int id_tipo, String rol, @PathVariable("id_dotacion") int id_dotacion){
        dotacion_incluidaRepository.eliminarDotacionIncluida(id_tipo, id_dotacion);
        return "redirect:/tiposHabitacion/{id_tipo}/dotacion?rol="+rol;
    } 
    
}
