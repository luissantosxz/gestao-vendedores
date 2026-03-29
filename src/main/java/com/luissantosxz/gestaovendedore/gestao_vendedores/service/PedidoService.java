package com.luissantosxz.gestaovendedore.gestao_vendedores.service;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.MediaPedidoPorVendedorDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.QuantidadePorStatusDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Pedido;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.EConfirmacao;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.EStatusPedido;
import com.luissantosxz.gestaovendedore.gestao_vendedores.exceptionhandler.BadRequest;
import com.luissantosxz.gestaovendedore.gestao_vendedores.exceptionhandler.NotFound;
import com.luissantosxz.gestaovendedore.gestao_vendedores.predicate.PedidoPredicate;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.PedidoRepository;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.VendedorRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.luissantosxz.gestaovendedore.gestao_vendedores.entity.QPedido.pedido;

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

    public List<PedidoResponseDTO> buscarPedidosPorVendedor(Integer vendedorId, LocalDateTime inicio, LocalDateTime fim) {
        var pedidos = repository.findByVendedorIdAndDataPedidoBetween(vendedorId, inicio, fim);

        return pedidos.stream().map(PedidoResponseDTO::of).toList();

    }

    public  List<PedidoResponseDTO> buscarPedidosPorEmpresa(Integer empresaId,
                                                            LocalDateTime inicio,
                                                            LocalDateTime fim){
        var pedidos = repository.findByVendedorEmpresaIdAndDataPedidoBetween(empresaId, inicio, fim);

        return pedidos.stream().map(PedidoResponseDTO::of).toList();


    }



    public List<PedidoResponseDTO> listarTodosPedidos (){
        var pedidos = repository.findAll();

        return pedidos.stream().map(PedidoResponseDTO::of).toList();
    }

    public PedidoResponseDTO confirmaPedido(Integer id){
        var pedido = buscarPedidoPorId(id);
        pedido.setStatusPedido(EStatusPedido.CONCLUIDO);
        pedido.setEConfirmacao(EConfirmacao.CONFIRMADO);
        repository.save(pedido);

        return PedidoResponseDTO.of(pedido);
    }

    public PedidoResponseDTO cancelaPedido(Integer id){
        var pedido = buscarPedidoPorId(id);
        pedido.setEConfirmacao(EConfirmacao.CANCELADO);
        pedido.setStatusPedido(EStatusPedido.CONCLUIDO);
        repository.save(pedido);
        return PedidoResponseDTO.of(pedido);
    }

    public List<QuantidadePorStatusDTO> quantidadePorStatus(Integer empresaId, Integer vendedorId,
                                                            LocalDateTime inicio, LocalDateTime fim){
        Predicate predicate = PedidoPredicate.filtro(empresaId, vendedorId, inicio, fim);

        var res =  repository.quantidadePorStatus(predicate);

        return res.stream().map(tuple -> new QuantidadePorStatusDTO(
                tuple.get(pedido.statusPedido).name(),
                tuple.get(pedido.id.count())
        )).toList();
    }

    public PedidoResponseDTO solicitarAjuste(Integer id, String observacao){
        var pedido = buscarPedidoPorId(id);
        pedido.setStatusPedido(EStatusPedido.AJUSTE_SOLICITACAO);
        pedido.setObservacao(observacao);

        repository.save(pedido);

        return PedidoResponseDTO.of(pedido);
    }

    public PedidoResponseDTO realizarAjuste(Integer id, PedidoRequestDTO dto){
        var pedido = buscarPedidoPorId(id);

        if(!pedido.getStatusPedido().equals(EStatusPedido.AJUSTE_SOLICITACAO)){
            throw new BadRequest("Pedido não está em status para ajuste, necessario realizar o tratamento");
        }

        pedido.atualizarPedido(dto);

        pedido.setStatusPedido(EStatusPedido.CONCLUIDO);
        pedido.setObservacao(null);
        repository.save(pedido);

        return PedidoResponseDTO.of(pedido);
    }

    public List<MediaPedidoPorVendedorDTO> mediaPorVendedor(
            Integer empresaId,
            Integer vendedorId,
            LocalDateTime inicio,
            LocalDateTime fim
    ) {

        Predicate predicate = PedidoPredicate.filtro(
                empresaId,
                vendedorId,
                inicio,
                fim
        );

        var res = repository.mediaPedidosPorVendedor(predicate);

        return res.stream()
                .map(tuple -> new MediaPedidoPorVendedorDTO(
                        tuple.get(pedido.vendedor.id),
                        tuple.get(pedido.id.count().doubleValue())
                ))
                .toList();
    }
    private Pedido buscarPedidoPorId(Integer id){
        return repository.findById(id).orElseThrow(()-> new NotFound("Pedido não encontrado"));
    }


}
