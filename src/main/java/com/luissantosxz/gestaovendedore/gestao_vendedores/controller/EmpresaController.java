package com.luissantosxz.gestaovendedore.gestao_vendedores.controller;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Empresa;
import com.luissantosxz.gestaovendedore.gestao_vendedores.service.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping
    public List<EmpresaResponseDTO> listar(){
        return empresaService.listar();
    }

    @PostMapping
    public EmpresaResponseDTO cadastrar(@RequestBody @Valid EmpresaRequestDTO requestDTO){
       return empresaService.cadastrar(requestDTO);
    }

    @GetMapping("/cnpj/{cnpj}/buscar")
    public EmpresaResponseDTO buscar( @PathVariable String cnpj){
        var empresa = empresaService.buscarPorCnpj(cnpj);
        return EmpresaResponseDTO.of(empresa); }

    @PutMapping("/cnpj/{cnpj}/inativar")
    public void inativar(@PathVariable String cnpj){
        empresaService.inativarPorCnpj(cnpj);
    }

    @PutMapping("/cnpj/{cnpj}/ativar")
    public void ativar(@PathVariable String cnpj){ empresaService.ativarPorCnpj(cnpj);}
}
