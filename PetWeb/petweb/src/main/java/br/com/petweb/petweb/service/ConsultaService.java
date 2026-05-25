package br.com.petweb.petweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Animal;
import br.com.petweb.petweb.entity.Consulta;
import br.com.petweb.petweb.repository.ConsultaRepository;

@Service
public class ConsultaService {
    // Injeção de dependencia do repositorio consulta
    @Autowired
    private ConsultaRepository consultaRepository;

    // para salvar consulta
     public Consulta save(Consulta consulta){
        return consultaRepository.save(consulta);
    }

    //Metodo para listar todos as consultas
    public List<Consulta> findAll(){
        return consultaRepository.findAll();
    }

    //Método para excluir uma consulta
    public void deleteById(Integer id) {
        consultaRepository.deleteById(id);
    }

    // Metodo para encontrar uma consulta por ID
    public Consulta findById(Integer id) {
        return consultaRepository.findById(id).orElse(null);
    }



}
