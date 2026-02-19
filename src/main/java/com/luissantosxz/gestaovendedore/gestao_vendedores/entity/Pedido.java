package com.luissantosxz.gestaovendedore.gestao_vendedores.entity;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.EStatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Data
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "vendedor", nullable = false)
    private Vendedor vendedor;

    @Column(nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStatusPedido statusPedido;

    @Column(nullable = false, name = "data_pedido")
    private LocalDateTime dataPedido;

    public static Pedido of(PedidoRequestDTO dto, Vendedor vendedor){
        return Pedido.builder()
                .vendedor(vendedor)
                .valor(dto.getValor())
                .statusPedido(EStatusPedido.CADASTRADO)
                .dataPedido(LocalDateTime.now())
                .build();
    }
}


