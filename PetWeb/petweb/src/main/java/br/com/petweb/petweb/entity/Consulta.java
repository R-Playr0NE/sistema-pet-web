package br.com.petweb.petweb.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConsulta;

    // Data e hora consulta
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;

    @Column(nullable = false, length = 100)
    private String observacaoConsulta;

    // Valor da consulta
    private Double valorConsulta;

    @Column(nullable = false, length = 100)
    private String diagnosticoConsulta;

    @Column(nullable = false, length = 100)
    private String tratamentoConsulta;

    // Relacionamento N para N => Muitos para Um
    @ManyToOne
    @JoinColumn(name = "idVeterinario_fk")
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "idAnimal_fk")
    private Animal animal;

}
