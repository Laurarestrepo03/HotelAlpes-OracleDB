package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.hoteles.modelo.Habitacion;
import uniandes.edu.co.hoteles.repositorio.HabitacionRepository;
import uniandes.edu.co.hoteles.repositorio.Tipo_habitacionRepository;

@Controller
public class HabitacionesController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private Tipo_habitacionRepository tipo_habitacionRepository;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model){
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "habitaciones";
    }

    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model){
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("tiposHabitacion", tipo_habitacionRepository.darTiposHabitacion());
        return "habitacionNuevo";
    }

    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(@ModelAttribute Habitacion habitacion, String rol){
        habitacionRepository.insertarHabitacion(habitacion.getNum_habitacion(), habitacion.getCosto_noche(),
        habitacion.getTipo().getId_tipo());
        return "redirect:/habitaciones?rol="+rol;
    }

    @GetMapping("/habitaciones/{num_habitacion}/edit")
    public String habitacionEditarForm(@PathVariable("num_habitacion") Integer num_habitacion, String rol, Model model){
        Habitacion habitacion = habitacionRepository.darHabitacion(num_habitacion);
        if (habitacion != null){
            model.addAttribute("habitacion", habitacion);
            model.addAttribute("tiposHabitacion", tipo_habitacionRepository.darTiposHabitacion());
            return "habitacionEditar";
        }
        else {
            return "redirect:/habitaciones?rol="+rol;
        }
    }

    @PostMapping("/habitaciones/{num_habitacion}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("num_habitacion") Integer num_habitacion, String rol, @ModelAttribute Habitacion habitacion){
        habitacionRepository.actualizarHabitacion(num_habitacion, habitacion.getNum_habitacion(), habitacion.getCosto_noche(),
        habitacion.getTipo().getId_tipo());
        return "redirect:/habitaciones?rol="+rol;
    }
    
    @GetMapping("/habitaciones/{num_habitacion}/delete")
    public String habitacionEliminar(@PathVariable("num_habitacion") Integer num_habitacion, String rol){
        habitacionRepository.eliminarHabitacion(num_habitacion);
        return "redirect:/habitaciones?rol="+rol;
    }
 
}
