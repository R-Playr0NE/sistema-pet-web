package br.com.petweb.petweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Animal;
import br.com.petweb.petweb.repository.AnimalRepository;

@Service
public class AnimalService {
    @Autowired
    // injeção de dependencias do repositorio aniaml

    private AnimalRepository animalRepository;

    // para salvar aniaml
     public Animal save(Animal animal){
        return animalRepository.save(animal);
    }

    //Metodo para listar todos os animais
    public List<Animal> findAll(){
        return animalRepository.findAll();
    }

    //Método para excluir um animal
    public void deleteById(Integer id) {
        animalRepository.deleteById(id);
    }

    // Metodo para encontrar um animal por ID
    public Animal findById(Integer id) {
        return animalRepository.findById(id).orElse(null);
    }
    
}
