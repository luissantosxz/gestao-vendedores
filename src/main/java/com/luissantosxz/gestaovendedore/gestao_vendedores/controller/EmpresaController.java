package com.luissantosxz.gestaovendedore.gestao_vendedores.controller;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Empresa;
import com.luissantosxz.gestaovendedore.gestao_vendedores.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<EmpresaResponseDTO> listar(){
        return empresaService.listar();
    }

    @PostMapping
    public EmpresaResponseDTO cadastrar(@RequestBody @Valid EmpresaRequestDTO requestDTO){
       return empresaService.cadastrar(requestDTO);
    }

    @PatchMapping("/cnpj/{cnpj}/inativar")
    public void inativar(@PathVariable String cnpj){
        empresaService.inativarPorCnpj(cnpj);
    }
}
