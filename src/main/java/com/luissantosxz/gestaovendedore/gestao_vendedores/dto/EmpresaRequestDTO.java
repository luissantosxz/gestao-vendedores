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

    @NotBlank(message = "Razão social é obrigatorio")
    private String razaoSocial;

    @NotBlank(message = "CNPJ deve ser valido")
    @Size(min = 14, max = 14, message = "Número de caracteres invalido")
    private String cnpj;


}