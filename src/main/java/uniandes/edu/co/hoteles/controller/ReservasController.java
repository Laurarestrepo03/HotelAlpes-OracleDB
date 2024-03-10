package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.hoteles.modelo.Reserva;
import uniandes.edu.co.hoteles.repositorio.Plan_consumoRepository;
import uniandes.edu.co.hoteles.repositorio.ReservaRepository;

import java.sql.Date;

@Controller
public class ReservasController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private Plan_consumoRepository plan_consumoRepository;

    @GetMapping("/reservas")
    public String reservas(Model model){
        model.addAttribute("reservas", reservaRepository.darReservas());
        return "reservas";
    }

    @GetMapping("/reservas/new")
    public String reservaForm(Model model){
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("planesConsumo", plan_consumoRepository.darPlanesConsumo());
        return "reservaNuevo";

    }

    @PostMapping("/reservas/new/save")
    public String reservaGuardar(@ModelAttribute Reserva reserva, String rol){
        Date fecha_entrada = reserva.getFecha_entrada();
        Date fecha_salida = reserva.getFecha_salida();
        Integer habitacion = reserva.getHabitacion().getNum_habitacion();
        if (reservaRepository.darReservasHabitacionFechas(habitacion, fecha_entrada, fecha_salida).size() == 0){
            reservaRepository.insertarReserva(reserva.getFecha_entrada(), reserva.getFecha_salida(), reserva.getNum_huespedes(), 
            reserva.getEstado(), reserva.getCliente_reserva().getNum_documento(), 
            reserva.getHabitacion().getNum_habitacion(), reserva.getPlan_consumo().getPk().getTipo().getId_tipo());
        }
        return "redirect:/reservas?rol="+rol;
    }

    @GetMapping("/reservas/{codigo_reserva}/edit")
    public String reservaEditarForm(@PathVariable("codigo_reserva") Integer codigo_reserva, String rol, Model model){
        Reserva reserva = reservaRepository.darReserva(codigo_reserva);
        if (codigo_reserva != null){
            model.addAttribute("reserva", reserva);
            model.addAttribute("planesConsumo", plan_consumoRepository.darPlanesConsumo());
            return "reservaEditar";
        }
        else {
            return "redirect:/reservas?rol="+rol;
        }
    }

    @PostMapping("/reservas/{codigo_reserva}/edit/save")
    public String reservaEditarGuardar(@PathVariable("codigo_reserva") Integer codigo_reserva, String rol, @ModelAttribute Reserva reserva){
        reservaRepository.actualizarReserva(codigo_reserva, reserva.getFecha_entrada(), reserva.getFecha_salida(), reserva.getNum_huespedes(), 
        reserva.getEstado(), reserva.getCliente_reserva().getNum_documento(), 
        reserva.getHabitacion().getNum_habitacion(), reserva.getPlan_consumo().getPk().getTipo().getId_tipo());
        return "redirect:/reservas?rol="+rol;
    }
    
    @GetMapping("/reservas/{codigo_reserva}/delete")
    public String reservaEliminar(@PathVariable("codigo_reserva") Integer codigo_reserva, String rol){
        reservaRepository.eliminarReserva(codigo_reserva);
        return "redirect:/reservas?rol="+rol;
    }
 
}

