package com.luissantosxz.gestaovendedore.gestao_vendedores.controller;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.VendedorRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.VendedorResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.service.VendedorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public VendedorResponseDTO cadastrar(@RequestBody @Valid VendedorRequestDTO dto){
        return vendedorService.cadastrar(dto);
    }

    @GetMapping
    public ResponseEntity<List<VendedorResponseDTO>> listar(){
        return  ResponseEntity.ok(vendedorService.vendedorResponseDTOList());
    }

    @PutMapping("{id}")
    public VendedorResponseDTO editar(@RequestBody VendedorRequestDTO requestDTO,
                                                   @PathVariable UUID id){
        return  vendedorService.update(requestDTO, id);
    }

    @PutMapping("{id}/inativar")
    public void inativar(@PathVariable UUID id){
        vendedorService.inativar(id);
    }

    @PutMapping("{id}/ativar")
    public void  ativar(@PathVariable UUID id){
        vendedorService.ativar(id);
    }

}
