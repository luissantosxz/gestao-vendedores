package com.luissantosxz.gestaovendedore.gestao_vendedores.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.time.LocalDateTime;

import static com.luissantosxz.gestaovendedore.gestao_vendedores.entity.QPedido.pedido;

public class PedidoPredicate{
    public static Predicate filtro(Integer empresaId, Integer vendedorId, LocalDateTime inicio, LocalDateTime fim)
    {
        BooleanBuilder builder = new BooleanBuilder();

        if(empresaId != null){
            builder.and(pedido.vendedor.empresa.id.eq(empresaId));
        }

        if(vendedorId != null){
            builder.and(pedido.vendedor.empresa.id.eq(vendedorId));
        }

        if(inicio != null){
            builder.and(pedido.dataPedido.loe(inicio));
        }

        if(fim != null){
            builder.and(pedido.dataPedido.loe(fim));
        }
        return builder;
    }

}
