package com.luissantosxz.gestaovendedore.gestao_vendedores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequestDTO {

    @NotBlank
    private String razaoSocial;

    @NotBlank
    @Size(min = 14, max = 14)
    private String cnpj;
}
