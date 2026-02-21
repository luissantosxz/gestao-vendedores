package com.luissantosxz.gestaovendedore.gestao_vendedores.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class PedidoRequestDTO {
    @NotNull(message = "Vendedor é obrigatório")
    private UUID vendedorId;

    @NotNull(message = "Nome do pedido é obrigatorio")
    private String nome;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private Double valor;

}
