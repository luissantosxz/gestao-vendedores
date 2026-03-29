package com.luissantosxz.gestaovendedore.gestao_vendedores.controller;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.MediaPedidoPorVendedorDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.QuantidadePorStatusDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping("/vendedor/{vendedorId}")
    public ResponseEntity<List<PedidoResponseDTO>>
    buscarPedidoPorVendedor(@PathVariable Integer vendedorId, @RequestParam LocalDateTime inicio,
                      @RequestParam LocalDateTime fim)
    {
        return ResponseEntity.ok(service.buscarPedidosPorVendedor(vendedorId, inicio, fim));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<PedidoResponseDTO>>
    buscarPedidoPorEmpresa(@PathVariable Integer empresaId, @RequestParam LocalDateTime inicio,
                     @RequestParam LocalDateTime fim)
    {
        return ResponseEntity.ok(service.buscarPedidosPorEmpresa(empresaId, inicio, fim));
    }

    @GetMapping("listar")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(){
        return ResponseEntity.ok(service.listarTodosPedidos());
    }

    @PutMapping("{id}/confirmar")
    public ResponseEntity<PedidoResponseDTO> confirmaPedido(@PathVariable Integer id){
        return ResponseEntity.ok(service.confirmaPedido(id));
    }

    @PutMapping("{id}/cancelar")
    public ResponseEntity<PedidoResponseDTO> cancelaPedido(@PathVariable Integer id){
        return ResponseEntity.ok(service.cancelaPedido(id));
    }

    @GetMapping("relatorio/status")
    public List<QuantidadePorStatusDTO> quantidadePorStatusDTOS(
            @RequestParam(required = false) Integer empresaId,
            @RequestParam(required = false) Integer vendedorId,
            @RequestParam(required = false) LocalDateTime inicio,
            @RequestParam(required = false) LocalDateTime fim
    ){
        return service.quantidadePorStatus(empresaId, vendedorId, inicio, fim);
    }

    @PutMapping("{id}/ajuste")
    public PedidoResponseDTO solicitarAjuste(
            @PathVariable Integer id,
            @RequestParam String observacao
    ){
        return service.solicitarAjuste(id, observacao);
    }

    @PutMapping("{id}/ajustado")
    public PedidoResponseDTO realizarAjuste(
            @PathVariable Integer id,
            @RequestBody PedidoRequestDTO dto
    ){
        return service.realizarAjuste(id, dto);
    }

    @GetMapping("/relatorio/media-vendedor")
    public List<MediaPedidoPorVendedorDTO> mediaPorVendedor(
            @RequestParam(required = false) Integer empresaId,
            @RequestParam(required = false) Integer vendedorId,
            @RequestParam(required = false) LocalDateTime inicio,
            @RequestParam(required = false) LocalDateTime fim
    ){
        return service.mediaPorVendedor(empresaId, vendedorId, inicio, fim);
    }

}
