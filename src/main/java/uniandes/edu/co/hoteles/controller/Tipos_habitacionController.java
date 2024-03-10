package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.hoteles.modelo.Tipo_habitacion;
import uniandes.edu.co.hoteles.repositorio.Dotacion_incluidaRepository;
import uniandes.edu.co.hoteles.repositorio.Tipo_habitacionRepository;

@Controller
public class Tipos_habitacionController {

    @Autowired
    private Tipo_habitacionRepository tipo_habitacionRepository;

    @Autowired
    private Dotacion_incluidaRepository dotacion_incluidaRepository;

    @GetMapping("/tiposHabitacion")
    public String tiposHabitacion(Model model){
        model.addAttribute("tiposHabitacion", tipo_habitacionRepository.darTiposHabitacion());
        return "tiposHabitacion";
    }

    @GetMapping("/tiposHabitacion/new")
    public String tipoHabitacionForm(Model model){
        model.addAttribute("tipoHabitacion", new Tipo_habitacion());
        return "tipoHabitacionNuevo";
    }

    @PostMapping("/tiposHabitacion/new/save")
    public String tipoHabitacionGuardar(@ModelAttribute Tipo_habitacion tipo_habitacion, String rol){
        tipo_habitacionRepository.insertarTipoHabitacion(tipo_habitacion.getNombre(), tipo_habitacion.getCapacidad());
        return "redirect:/tiposHabitacion?rol="+rol;
    }

    @GetMapping("/tiposHabitacion/{id_tipo}/edit")
    public String tipoHabitacionEditarForm(@PathVariable("id_tipo") int id_tipo, String rol, Model model){
        Tipo_habitacion tipo_habitacion = tipo_habitacionRepository.darTipoHabitacion(id_tipo);
        if (tipo_habitacion != null){
            model.addAttribute("tipoHabitacion", tipo_habitacion);
            return "tipoHabitacionEditar";
        }
        else {
            return "redirect:/tiposHabitacion?rol="+rol;
        }
    }

    @PostMapping("/tiposHabitacion/{id_tipo}/edit/save")
    public String tipoHabitacionEditarGuardar(@PathVariable("id_tipo") int id_tipo, String rol, @ModelAttribute Tipo_habitacion tipo_habitacion){
        tipo_habitacionRepository.actualizarTipoHabitacion(id_tipo, tipo_habitacion.getNombre(), tipo_habitacion.getCapacidad());
        return "redirect:/tiposHabitacion?rol="+rol;
    }
    
    @GetMapping("/tiposHabitacion/{id_tipo}/delete")
    public String tipoHabitacionEliminar(@PathVariable("id_tipo") int id_tipo, String rol){
        dotacion_incluidaRepository.eliminarDotacionIncluidaPorTipo(id_tipo);
        tipo_habitacionRepository.eliminarTipoHabitacion(id_tipo);
        return "redirect:/tiposHabitacion?rol="+rol;
    } 
    
}
