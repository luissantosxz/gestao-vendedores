package com.luissantosxz.gestaovendedore.gestao_vendedores.repository;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Pedido;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.luissantosxz.gestaovendedore.gestao_vendedores.entity.QPedido.pedido;

@Repository
@RequiredArgsConstructor
public class PedidoRepositoryCustomImpl implements PedidoRepositoryCustom{

    private final EntityManager entityManager;

    @Override
    public List<Pedido> findByVendedorEmpresaIdAndDataPedidoBetween(Integer empresaId, LocalDateTime inicio, LocalDateTime fim) {

        BooleanBuilder builder = new BooleanBuilder();
        if(empresaId != null){
            builder.and(pedido.vendedor.empresa.id.eq(empresaId));
        }

        if(inicio != null && fim != null){
            builder.and(pedido.dataPedido.between(inicio,fim));
        }

        return new JPAQueryFactory(entityManager).selectFrom(pedido).where(builder).fetch();

    }

    public List<Tuple> quantidadePorStatus(Predicate predicate){
        return new JPAQueryFactory(entityManager)
                .select(pedido.statusPedido, pedido.id.count())
                .from(pedido)
                .where(predicate)
                .groupBy(pedido.statusPedido)
                .fetch();
    }

    @Override
    public List<Tuple> mediaPedidosPorVendedor(Predicate predicate) {
        return new JPAQueryFactory(entityManager)
                .select(
                        pedido.vendedor.id,
                        pedido.id.count().doubleValue()
                )
                .from(pedido)
                .where(predicate)
                .groupBy(pedido.vendedor.id)
                .fetch();
    }
}