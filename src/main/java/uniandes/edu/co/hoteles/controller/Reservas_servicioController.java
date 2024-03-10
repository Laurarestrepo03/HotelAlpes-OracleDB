package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hoteles.modelo.Reserva_servicio;
import uniandes.edu.co.hoteles.repositorio.Reserva_servicioRepository;
import uniandes.edu.co.hoteles.repositorio.Servicio_comercialRepository;
import uniandes.edu.co.hoteles.repositorio.Servicio_rentaRepository;

@Controller
public class Reservas_servicioController {

    @Autowired
    private Reserva_servicioRepository reserva_servicioRepository;

    @Autowired
    private Servicio_comercialRepository servicio_comercialRepository;

    @Autowired
    private Servicio_rentaRepository servicio_rentaRepository;

    @GetMapping("/reservasServicio")
    public String reservasServicio(Model model){
        model.addAttribute("reservasServicio", reserva_servicioRepository.darReservasServicio());
        return "reservasServicio";
     }

    @GetMapping("/reservasServicio/new")
    public String reservaServicioForm( Model model){
        model.addAttribute("reservaServicio", new Reserva_servicio());
        model.addAttribute("serviciosComerciales", servicio_comercialRepository.darServiciosComerciales());
        model.addAttribute("serviciosRenta", servicio_rentaRepository.darServiciosRenta());
        return "reservaServicioNuevo";
    }

    @PostMapping("/reservasServicio/new/save")
    public String reservaServicioGuardar(@ModelAttribute Reserva_servicio reservaServicio, String rol){
        reserva_servicioRepository.insertarReservaServicio(reservaServicio.getDia_reserva(), reservaServicio.getHora_inicio(),reservaServicio.getHora_fin(), reservaServicio.getServicio_comercial().getPk().getTipo().getPk().getTipo().getId_tipo(), 
        reservaServicio.getServicio_renta().getPk().getTipo().getPk().getTipo().getId_tipo(),reservaServicio.getCliente().getNum_documento());
        return "redirect:/reservasServicio?rol="+rol;
    }

    @GetMapping("/reservasServicio/{id_reserva_servicio}/edit")
    public String reservaServicioEditarForm(@PathVariable("id_reserva_servicio") Integer id_reserva_servicio, String rol, Model model){
        Reserva_servicio reservaServicio = reserva_servicioRepository.darReservaServicio(id_reserva_servicio);
        if (reservaServicio != null) {
            model.addAttribute("reservaServicio", reservaServicio);
            model.addAttribute("serviciosComerciales", servicio_comercialRepository.darServiciosComerciales());
            model.addAttribute("serviciosRenta", servicio_rentaRepository.darServiciosRenta());
            return "reservaServicioEditar";
        }
        else {
            return "redirect:/reservasServicio?rol="+rol;
        }
    }

    @PostMapping("/reservasServicio/{id_reserva_servicio}/edit/save")
    public String reservaServicioEditarGuardar (@PathVariable("id_reserva_servicio") Integer id_reserva_servicio, String rol, @ModelAttribute Reserva_servicio reservaServicio){
        reserva_servicioRepository.actualizarReservaServicio(id_reserva_servicio, reservaServicio.getDia_reserva(), reservaServicio.getHora_inicio(), reservaServicio.getHora_fin(), 
        reservaServicio.getServicio_comercial().getPk().getTipo().getPk().getTipo().getId_tipo(), reservaServicio.getServicio_renta().getPk().getTipo().getPk().getTipo().getId_tipo(),
        reservaServicio.getCliente().getNum_documento());
        return "redirect:/reservasServicio?rol="+rol;
    }

    @GetMapping("/reservasServicio/{id_reserva_servicio}/delete")
    public String reservasServicioEliminar(@PathVariable("id_reserva_servicio") Integer id_reserva_servicio, String rol){
        reserva_servicioRepository.eliminarReservaServicio(id_reserva_servicio);
        return "redirect:/reservasServicio?rol="+rol;
    }
    
}
