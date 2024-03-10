package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hoteles.modelo.Plan_consumo;
import uniandes.edu.co.hoteles.modelo.Plan_consumoPK;
import uniandes.edu.co.hoteles.modelo.Tipo_plan;
import uniandes.edu.co.hoteles.repositorio.Plan_consumoRepository;
import uniandes.edu.co.hoteles.repositorio.Tipo_planRepository;

@Controller
public class Planes_consumoController {

    @Autowired
    private Plan_consumoRepository plan_consumoRepository;

    @Autowired
    private Tipo_planRepository tipo_planRepository;

    @GetMapping("/planesConsumo")
    public String planesConsumo (Model model){
        model.addAttribute("planesConsumo", plan_consumoRepository.darPlanesConsumo());
        return "planesConsumo";
    }

    @GetMapping("/planesConsumo/new")
    public String planConsumoForm(Model model) {
        model.addAttribute("planConsumo", new Plan_consumo());
        model.addAttribute("tiposPlan", tipo_planRepository.darTiposPlan());
        return "planConsumoNuevo";
    }

     @PostMapping("/planesConsumo/new/save")
     public String planConsumoGuardar(@ModelAttribute Plan_consumo plan_consumo, String rol, @ModelAttribute("tipo") int tipo){
        Tipo_plan tipo_plan = tipo_planRepository.darTipoPlan(tipo);
        Plan_consumoPK pk = new Plan_consumoPK(tipo_plan);
        plan_consumo.setPk(pk);
        plan_consumoRepository.insertarPlanConsumo(plan_consumo.getPk().getTipo().getId_tipo(),plan_consumo.getGenera_descuento(),
        plan_consumo.getTiempo_estadia(), plan_consumo.getValor_descuento_ser());
        return "redirect:/planesConsumo?rol="+rol;
    }

    @GetMapping("/planesConsumo/{tipo}/edit")
    public String planConsumoEditarForm(@PathVariable("tipo") Integer tipo, String rol, Model model){

        Plan_consumo plan_consumo = plan_consumoRepository.darPlanConsumo(tipo);
        if (plan_consumo != null){
            model.addAttribute("planConsumo", plan_consumo);
            model.addAttribute("tiposPlan", tipo_planRepository.darTiposPlan());
            return "planConsumoEditar";
        }
        else{
            return "redirect:/planesConsumo?rol="+rol;
        }

    }

    @PostMapping("/planesConsumo/{tipo}/edit/save")
    public String planesConsumoEditarGuardar(@PathVariable("tipo") int tipo, String rol, @ModelAttribute Plan_consumo plan_consumo){
        Tipo_plan tipo_plan = tipo_planRepository.darTipoPlan(tipo);
        Plan_consumoPK pk = new Plan_consumoPK(tipo_plan);
        plan_consumo.setPk(pk);
        plan_consumoRepository.actualizarPlanConsumo(tipo, plan_consumo.getPk().getTipo().getId_tipo(), plan_consumo.getGenera_descuento(),
        plan_consumo.getTiempo_estadia(), plan_consumo.getValor_descuento_ser());
        return "redirect:/planesConsumo?rol="+rol;
    }

    @GetMapping("/planesConsumo/{tipo}/delete")
    public String planesConsumoEliminar(@PathVariable("tipo") int tipo, String rol){
        plan_consumoRepository.eliminarPlanConsumo(tipo);
        return "redirect:/planesConsumo?rol="+rol;
    }
}
