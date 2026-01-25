package com.luissantosxz.gestaovendedore.gestao_vendedores.dto;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Empresa;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class EmpresaResponseDTO {
    private UUID id;
    private String razaoSocial;
    private String cnpj;
    private Boolean ativo;

    private List<VendedorResponseDTO> vendedores;

    public static EmpresaResponseDTO of (Empresa empresa){
        return EmpresaResponseDTO.builder()
                .id(empresa.getId())
                .razaoSocial(empresa.getRazaoSocial())
                .cnpj(empresa.getCnpj())
                .ativo(empresa.getAtivo())
                .vendedores(empresa.getVendedores() == null ? List.of() : empresa.getVendedores().stream().map(VendedorResponseDTO::of).toList())
                .build();
    }
}
