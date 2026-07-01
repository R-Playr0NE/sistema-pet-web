package br.com.petweb.petweb.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Veterinario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVeterinario;

    @Column(nullable = false, length = 40)
    private String nomeVeterinario;

    @Column(nullable = false, length = 20)
    private String cpfVeterinario;

    @Column(nullable = false, length = 20)
    private String telefoneVeterinario;

    @Column(nullable = false, length = 25)
    private Integer crmvVeterinario;

    @Column(nullable = false, length = 50)
    private String especialidadeVeterinario;

}
