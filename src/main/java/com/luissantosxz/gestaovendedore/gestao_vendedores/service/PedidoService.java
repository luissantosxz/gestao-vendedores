package com.luissantosxz.gestaovendedore.gestao_vendedores.service;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Pedido;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Vendedor;
import com.luissantosxz.gestaovendedore.gestao_vendedores.exceptionhandler.NotFound;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.PedidoRepository;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final VendedorRepository vendedorRepository;

    public PedidoResponseDTO criar(PedidoRequestDTO dto) {
        var vendedor = vendedorRepository.findById(dto.getVendedorId())
                .orElseThrow(() -> new NotFound("Vendedor não encontrado"));

        var pedido = Pedido.of(dto, vendedor);

        var salvo = repository.save(pedido);

        return PedidoResponseDTO.of(salvo);
    }

    public List<PedidoResponseDTO> buscarPorVendedor(UUID vendedorId, LocalDateTime inicio, LocalDateTime fim) {
        var pedidos = repository.findByVendedorIdAndDataPedidoBetween(vendedorId, inicio, fim);

        return pedidos.stream().map(PedidoResponseDTO::of).toList();

    }
}
