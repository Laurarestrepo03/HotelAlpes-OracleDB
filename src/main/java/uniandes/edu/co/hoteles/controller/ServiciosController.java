package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


import uniandes.edu.co.hoteles.modelo.Servicio;
import uniandes.edu.co.hoteles.modelo.ServicioPK;
import uniandes.edu.co.hoteles.modelo.Tipo_servicio;
import uniandes.edu.co.hoteles.repositorio.ServicioRepository;
import uniandes.edu.co.hoteles.repositorio.Tipo_servicioRepository;

@Controller
public class ServiciosController {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private Tipo_servicioRepository tipo_servicioRepository;

    @GetMapping("/servicios")
    public String servicios(Model model){
        model.addAttribute("servicios", servicioRepository.darServicios());
        return "servicios";
    }

    @GetMapping("/servicios/new")
    public String servicioForm(Model model){
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("tiposServicio", tipo_servicioRepository.darTiposServicio());
        return "servicioNuevo";
    }

    @PostMapping("/servicios/new/save")
    public String servicioGuardar(@ModelAttribute Servicio servicio, String rol, @ModelAttribute("tipo") int tipo){
        Tipo_servicio tipo_servicio = tipo_servicioRepository.darTipoServicio(tipo);
        ServicioPK pk = new ServicioPK(tipo_servicio);
        servicio.setPk(pk);
        servicioRepository.insertarServicio(servicio.getPk().getTipo().getId_tipo(), servicio.getCapacidad(), 
        servicio.getTipo_cobro(), servicio.getPrecio());
        return "redirect:/servicios?rol="+rol;
    }

    @GetMapping("/servicios/{tipo}/edit")
    public String servicioEditarForm(@PathVariable("tipo") int tipo, String rol, Model model){
        Servicio servicio = servicioRepository.darServicio(tipo);
        if (servicio != null){
            model.addAttribute("servicio", servicio);
            model.addAttribute("tiposServicio", tipo_servicioRepository.darTiposServicio());
            return "servicioEditar";
        }
        else {
            return "redirect:/servicios?rol="+rol;
        }
    }

    @PostMapping("/servicios/{tipo}/edit/save")
    public String servicioEditarGuardar(@PathVariable("tipo") int tipo, String rol, @ModelAttribute Servicio servicio){
        Tipo_servicio tipo_servicio = tipo_servicioRepository.darTipoServicio(tipo);
        ServicioPK newpk = new ServicioPK(tipo_servicio);
        servicio.setPk(newpk);
        servicioRepository.actualizarServicio(tipo, servicio.getPk().getTipo().getId_tipo(), servicio.getCapacidad(), 
        servicio.getTipo_cobro(), servicio.getPrecio());
        return "redirect:/servicios?rol="+rol;
    }
    
    @GetMapping("/servicios/{tipo}/delete")
    public String servicioEliminar(@PathVariable("tipo") int tipo, String rol){
        servicioRepository.eliminarServicio(tipo);
        return "redirect:/servicios?rol="+rol;
    }
 
}
