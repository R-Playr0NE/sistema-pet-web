package br.com.petweb.petweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Cliente;
import br.com.petweb.petweb.entity.Veterinario;
import br.com.petweb.petweb.repository.VeterinarioRepository;

@Service
public class VeterinarioService {
    
    @Autowired
    // Injeção de depnedencia do repositorio veterinario
    private VeterinarioRepository veterinarioRepository;

    // Método para salvar
    public Veterinario save(Veterinario veterinario){
        return veterinarioRepository.save(veterinario);
    }

    //Metodo para listar todos os veterinarios
    public List<Veterinario> findAll(){
        return veterinarioRepository.findAll();
    }

    //Método para excluir um veterinarios
    public void deleteById(Integer id) {
        veterinarioRepository.deleteById(id);
    }

    // Metodo para encontrar um veterinario por ID
    public Veterinario findById(Integer id) {
        return veterinarioRepository.findById(id).orElse(null);
    } 
}
