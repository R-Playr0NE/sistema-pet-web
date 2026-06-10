package br.com.petweb.petweb.entity;

import org.hibernate.annotations.ManyToAny;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDoPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;

    //Relacionamento com Pedido
    @ManyToOne
    @JoinColumn(name= "idPedido_fk")
    private Pedido pedido;

    //Relacionamento com produto
    @ManyToOne
    @JoinColumn(name = "idProduto_fk")
    private Produto produto;

    private Integer quantidade;

    private Double preco;

    private Double subtotal;

    //Método calcular subtotal
    public Double calcularSubtotal(){
        return quantidade * preco;
    }

    //Método para atualizar o subtotal
    public void atualizarSubtotal(){
        this.subtotal = calcularSubtotal();
    }

}
