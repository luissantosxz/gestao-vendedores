package com.luissantosxz.gestaovendedore.gestao_vendedores.repository;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Pedido;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PedidoRepositoryCustom {

    List<Pedido> findByVendedorEmpresaIdAndDataPedidoBetween(
            Integer empresaId, LocalDateTime inicio, LocalDateTime fim
    );

    List<Tuple> quantidadePorStatus(Predicate predicate);

}
