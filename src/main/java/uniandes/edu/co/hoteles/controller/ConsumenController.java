package uniandes.edu.co.hoteles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hoteles.modelo.Consumen;
import uniandes.edu.co.hoteles.modelo.ConsumenPK;
import uniandes.edu.co.hoteles.modelo.Servicio;
import uniandes.edu.co.hoteles.modelo.Usuario;
import uniandes.edu.co.hoteles.repositorio.ConsumenRepository;
import uniandes.edu.co.hoteles.repositorio.ReservaRepository;
import uniandes.edu.co.hoteles.repositorio.ServicioRepository;
import uniandes.edu.co.hoteles.repositorio.UsuarioRepository;

@Controller
public class ConsumenController {

    @Autowired
    private ConsumenRepository consumenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("/consumen")
    public String consumen(Model model){
        model.addAttribute("consumen", consumenRepository.darConsumen());
        return "consumen";
    }

    @GetMapping("/consumen/new")
    public String consumenForm (Model model){
        model.addAttribute("consume", new Consumen());
        model.addAttribute("servicios", servicioRepository.darServicios());
        model.addAttribute("empleados", usuarioRepository.darEmpleados());
        return "consumeNuevo";
    }

    @PostMapping("/consumen/new/save")
    public String consumeGuardar(String rol, @ModelAttribute Consumen consume, @ModelAttribute("cliente_pk") String cliente, @ModelAttribute("servicio_pk") int servicio, @ModelAttribute("fecha_pk") Date fecha, @ModelAttribute("hora_pk") String hora){
        Usuario clientepk = usuarioRepository.darUsuario(cliente);
        Servicio serviciopk = servicioRepository.darServicio(servicio);
        ConsumenPK pk = new ConsumenPK(clientepk, serviciopk, fecha, hora);
        consume.setPk(pk);
        if (reservaRepository.darReservasUsuario(cliente, consume.getPk().getFecha()).size() > 0){
            consumenRepository.insertarConsume(consume.getPk().getCliente().getNum_documento(), consume.getPk().getServicio().getPk().getTipo().getId_tipo(), consume.getPk().getFecha(), consume.getPk().getHora(), 
            consume.getCuenta_pagada(), consume.getRegistrado_por().getNum_documento());
        }
        return "redirect:/consumen?rol="+rol;
    }

    @GetMapping("/consumen/{cliente}/{servicio}/{fecha}/{hora}/edit")
    public String consumeEditarForm(String rol, @PathVariable("cliente") String cliente, @PathVariable("servicio") int servicio, @PathVariable("fecha") Date fecha, @PathVariable("hora") String hora, Model model){
        Consumen consume = consumenRepository.darConsume(cliente, servicio, fecha, hora);
        if (consume != null){
            model.addAttribute("consume", consume);
            model.addAttribute("servicios", servicioRepository.darServicios());
            model.addAttribute("empleados", usuarioRepository.darEmpleados());
            return "consumeEditar";
        }
        else {
            return "redirect:/consumen?rol="+rol;
        }
    }

    @PostMapping("/consumen/{cliente}/{servicio}/{fecha}/{hora}/edit/save")
    public String usuarioEditarGuardar(String rol, @PathVariable("cliente") String cliente, @PathVariable("servicio") Integer servicio, @PathVariable("fecha") Date fecha, @PathVariable("hora") String hora, 
    @ModelAttribute("cliente_pk") String cliente_pk, @ModelAttribute("servicio_pk") Integer servicio_pk, @ModelAttribute("fecha_pk") Date fecha_pk, @ModelAttribute("hora_pk") String hora_pk, @ModelAttribute Consumen consume){
        Usuario clientepk = usuarioRepository.darUsuario(cliente_pk);
        Servicio serviciopk = servicioRepository.darServicio(servicio_pk);
        ConsumenPK pk = new ConsumenPK(clientepk, serviciopk, fecha_pk, hora_pk);
        consume.setPk(pk);
        consumenRepository.actualizarConsume(cliente, servicio, fecha, hora, consume.getPk().getCliente().getNum_documento(), consume.getPk().getServicio().getPk().getTipo().getId_tipo(),
        consume.getPk().getFecha(), consume.getPk().getHora(), consume.getCuenta_pagada(), consume.getRegistrado_por().getNum_documento());
        return "redirect:/consumen?rol="+rol;
    }
    @GetMapping("/consumen/{cliente}/{servicio}/{fecha}/{hora}/delete")
    public String consumeEliminar(String rol, @PathVariable("cliente") String cliente, @PathVariable("servicio") int servicio, @PathVariable("fecha") Date fecha, @PathVariable("hora") String hora){
        consumenRepository.eliminarConsume(cliente, servicio, fecha, hora);
        return "redirect:/consumen?rol="+rol;
    }
    
}
