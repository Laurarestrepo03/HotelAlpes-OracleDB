package uniandes.edu.co.hoteles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.hoteles.repositorio_queries.Cliente_excelenteRepository;

@Controller
public class Clientes_excelentesController {

    @Autowired
    private Cliente_excelenteRepository cliente_excelenteRepository;

    @GetMapping("/usuarios/clientesExcelentes")
    public String clientesExcelentes(Model model){
        model.addAttribute("clientesExcelentes", cliente_excelenteRepository.darClientesExcelentes());
        return "clientesExcelentes";
    }
    
}
