package com.luissantosxz.gestaovendedore.gestao_vendedores.dto;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Pedido;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.EConfirmacao;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.EStatusPedido;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PedidoResponseDTO {
    private Integer id;
    private String nome;
    private Double valor;
    private EStatusPedido status;
    private LocalDateTime dataPedido;
    private Integer vendedorId;
    private String empresaId;
    private EConfirmacao eConfirmacao;
    private String observacaoPedido;

    public static PedidoResponseDTO of(Pedido pedido){
        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .nome(pedido.getNome())
                .vendedorId(pedido.getVendedor().getId())
                .valor(pedido.getValor())
                .status(pedido.getStatusPedido())
                .eConfirmacao(pedido.getEConfirmacao())
                .dataPedido(pedido.getDataPedido())
                .observacaoPedido(pedido.getObservacao())
                .empresaId(pedido.getVendedor().getEmpresa().getRazaoSocial())
                .build();
    }


}
