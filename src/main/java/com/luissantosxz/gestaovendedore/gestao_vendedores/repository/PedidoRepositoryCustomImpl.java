package com.luissantosxz.gestaovendedore.gestao_vendedores.repository;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Pedido;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.luissantosxz.gestaovendedore.gestao_vendedores.entity.QPedido.pedido;

@Repository
@RequiredArgsConstructor
public class PedidoRepositoryCustomImpl implements PedidoRepositoryCustom{

    private final EntityManager entityManager;

    @Override
    public List<Pedido> findByVendedorEmpresaIdAndDataPedidoBetween(Integer empresaId, LocalDateTime inicio, LocalDateTime fim) {
        return new JPAQueryFactory(entityManager)
                .select(pedido)
                .from(pedido)
                .where(pedido.vendedor.empresa.id.eq(empresaId))
                .where(pedido.dataPedido.between(inicio, fim))
                .fetch();
    }
}
