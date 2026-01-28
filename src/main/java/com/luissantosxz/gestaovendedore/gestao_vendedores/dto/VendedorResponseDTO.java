package com.luissantosxz.gestaovendedore.gestao_vendedores.dto;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Vendedor;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.ESituacao;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Builder
@Getter
public class VendedorResponseDTO {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private String empresaCnpj;
    private ESituacao situacao;

    public static VendedorResponseDTO of(Vendedor vendedor){
        return VendedorResponseDTO.builder()
                .id(vendedor.getId())
                .nome(vendedor.getNome())
                .cpf(vendedor.getCpf())
                .email(vendedor.getEmail())
                .senha(vendedor.getSenha())
                .empresaCnpj(vendedor.getEmpresa().cnpj)
                .situacao(vendedor.getSituacao())
                .build();
    }
}
