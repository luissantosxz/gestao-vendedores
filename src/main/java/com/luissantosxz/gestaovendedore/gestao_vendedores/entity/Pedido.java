package com.luissantosxz.gestaovendedore.gestao_vendedores.entity;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.EConfirmacao;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.EStatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PEDIDO")
    @SequenceGenerator(sequenceName = "SEQUENCE_PEDIDO", name = "SEQUENCE_PEDIDO", allocationSize = 1)
    private Integer id;

    @NotBlank(message = "Nome do pedido é obrigatorio")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "vendedor", nullable = false)
    private Vendedor vendedor;

    @Column(nullable = false)
    @NotNull
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatusPedido statusPedido;

    private EConfirmacao eConfirmacao;

    @Column(nullable = false, name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "observacao")
    private String observacao;

    public static Pedido of(PedidoRequestDTO dto, Vendedor vendedor){
        return Pedido.builder()
                .nome(dto.getNome())
                .vendedor(vendedor)
                .valor(dto.getValor())
                .statusPedido(EStatusPedido.CADASTRADO)
                .eConfirmacao((EConfirmacao.PENDENTE))
                .dataPedido(LocalDateTime.now())
                .observacao(dto.getObservacao())
                .build();
    }

    public void atualizarPedido(PedidoRequestDTO dto){
        this.nome = dto.getNome();
        this.valor = dto.getValor();
    }

}


