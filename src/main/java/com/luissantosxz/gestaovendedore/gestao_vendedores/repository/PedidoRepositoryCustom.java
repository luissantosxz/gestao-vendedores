package com.luissantosxz.gestaovendedore.gestao_vendedores.repository;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PedidoRepositoryCustom {

    List<Pedido> findByVendedorEmpresaIdAndDataPedidoBetween(
            UUID empresaId, LocalDateTime inicio, LocalDateTime fim
    );


}
