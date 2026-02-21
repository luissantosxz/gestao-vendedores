package com.luissantosxz.gestaovendedore.gestao_vendedores.controller;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.PedidoResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.service.PedidoService;
import lombok.RequiredArgsConstructor;
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
    buscarPedidoPorVendedor(@PathVariable UUID vendedorId, @RequestParam LocalDateTime inicio,
                      @RequestParam LocalDateTime fim)
    {
        return ResponseEntity.ok(service.buscarPedidosPorVendedor(vendedorId, inicio, fim));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<PedidoResponseDTO>>
    buscarPedidoPorEmpresa(@PathVariable UUID empresaId, @RequestParam LocalDateTime inicio,
                     @RequestParam LocalDateTime fim)
    {
        return ResponseEntity.ok(service.buscarPedidosPorEmpresa(empresaId, inicio, fim));
    }

    @GetMapping("listar")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(){
        return ResponseEntity.ok(service.listarTodosPedidos());
    }
}
