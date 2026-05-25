package br.com.petweb.petweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petweb.petweb.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {


}
