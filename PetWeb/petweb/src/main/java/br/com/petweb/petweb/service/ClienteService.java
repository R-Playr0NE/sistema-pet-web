package br.com.petweb.petweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Cliente;
import br.com.petweb.petweb.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    //injeção de dependencia do repositório de clientes

    private ClienteRepository clienteRepository;

    //Metodo para salvar clientes
    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //Metodo para listar todos os clientes
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    //Método para excluir um cliente
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }

    // Metodo para encontrar um cliente por ID
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    } 
    
}