package br.com.petweb.petweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petweb.petweb.entity.Cliente;

//extends é bastante utilizada para herança
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
