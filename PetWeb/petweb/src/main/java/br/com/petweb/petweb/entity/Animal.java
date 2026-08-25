package br.com.petweb.petweb.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAnimal;

    @Column(nullable = false, length = 40)
    private String nomeAnimal;

    @Column(nullable = false, length = 40)
    private String raca;

    @Column(nullable = false, length = 40)
    private String porte;

    @Column(nullable = false, length = 100)
    private Double peso;

    @Lob
    private byte[] fotoAnimal;

    @Column(length = 30)
    private String tipoFoto;

    // Relacionamento N para N => Muitos para Um
    @ManyToOne
    @JoinColumn(name = "idCliente_fk")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idAnimal_fk")
    private Animal animal;

}
