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

import br.com.petweb.petweb.entity.Cliente;
import br.com.petweb.petweb.entity.Veterinario;
import br.com.petweb.petweb.service.VeterinarioService;

@Controller
@RequestMapping("/veterinarios")
public class VeterinarioController {
    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Veterinario veterinario) {
        veterinarioService.save(veterinario);
        return "redirect:/veterinarios/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        List <Veterinario> veterinarios = veterinarioService.findAll();
        model.addAttribute("veterinarios", veterinarios);
        return "veterinario/listarVeterinarios";
    }

    @GetMapping("/criar")
    public String criar(Model model){
        model.addAttribute("veterinario", new Veterinario());
        return "veterinario/formularioVeterinario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        veterinarioService.deleteById(id);
        return "redirect:/veterinarios/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Veterinario veterinario = veterinarioService.findById(id);
        model.addAttribute("veterinario", veterinario);
        return "veterinario/formularioVeterinario";
    }


}
