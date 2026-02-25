package com.luissantosxz.gestaovendedore.gestao_vendedores.repository;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID>, PedidoRepositoryCustom {

    List<Pedido> findByVendedorIdAndDataPedidoBetween(
            UUID vendedorId, LocalDateTime inicio, LocalDateTime fim
    );


}
