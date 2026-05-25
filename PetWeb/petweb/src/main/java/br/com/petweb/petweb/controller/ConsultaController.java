package br.com.petweb.petweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petweb.petweb.entity.Animal;
import br.com.petweb.petweb.entity.Cliente;
import br.com.petweb.petweb.entity.Consulta;
import br.com.petweb.petweb.entity.Veterinario;
import br.com.petweb.petweb.service.AnimalService;
import br.com.petweb.petweb.service.ConsultaService;
import br.com.petweb.petweb.service.VeterinarioService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    
    @Autowired
    private ConsultaService consultaService;

    // // Só um teste...
    // @Autowired
    // private AnimalService animalService;

    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Consulta consulta){
        consultaService.save(consulta);
        return "redirect:/consultas/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        List <Consulta> consultas = consultaService.findAll();
        model.addAttribute("consultas", consultas);
        return "consulta/listarConsulta";
    }

    @GetMapping("/criar")
    public String criar(Model model){
        model.addAttribute("consulta", new Consulta());
        List<Veterinario> veterinarios = veterinarioService.findAll();
        model.addAttribute("veterinarios", veterinarios);
        return "consulta/formularioConsulta";
    }

    // para excluir pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        consultaService.deleteById(id);
        return "redirect:/consultas/listar";
    }

    // para editar pelo ID
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Consulta consulta = consultaService.findById(id);
        model.addAttribute("consulta", consulta);
        List<Veterinario> veterinarios = veterinarioService.findAll();
        model.addAttribute("veterinarios", veterinarios);
        return "consulta/formularioConsulta";
    }

}
