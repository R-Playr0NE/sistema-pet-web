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
import br.com.petweb.petweb.service.AnimalService;
import br.com.petweb.petweb.service.ClienteService;


@Controller
@RequestMapping("/animais")
public class AnimalController {

    // Injeção de dependencia do serviço animal
    @Autowired
    private AnimalService animalService;

    // Injeção de dependencia do serviço cliente
    @Autowired
    private ClienteService clienteService;

    // Metodo para salvar animais
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Animal animal){
        animalService.save(animal);
        return "redirect:/animais/listar";
    }

    // para listar animais
    @GetMapping("/listar")
    public String listar(Model model){
        List <Animal> animais = animalService.findAll();
        model.addAttribute("animais", animais);
        return "animal/listarAnimais";
    }

    // para exibir o formulario de cadastro do animal
    @GetMapping("/criar")
    public String criar(Model model){
        model.addAttribute("animal", new Animal());
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "animal/formularioAnimal";
    }

    // para excluir pelo ID
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        animalService.deleteById(id);
        return "redirect:/animais/listar";
    }

    // para editar pelo ID
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Animal animal = animalService.findById(id);
        model.addAttribute("animal", animal);
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "animal/formularioAnimal";
    }
    
}
