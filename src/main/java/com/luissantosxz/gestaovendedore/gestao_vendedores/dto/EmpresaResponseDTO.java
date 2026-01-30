package com.luissantosxz.gestaovendedore.gestao_vendedores.dto;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Empresa;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.ESituacao;
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

    private List<VendedorResponseDTO> vendedores;

    private ESituacao situacao;

    public static EmpresaResponseDTO of (Empresa empresa){
        return EmpresaResponseDTO.builder()
                .id(empresa.getId())
                .razaoSocial(empresa.getRazaoSocial())
                .cnpj(empresa.getCnpj())
                .vendedores(empresa.getVendedores() == null ? List.of() : empresa.getVendedores().stream().map(VendedorResponseDTO::of).toList())
                .situacao(empresa.getSituacao())
                .build();
    }
}
