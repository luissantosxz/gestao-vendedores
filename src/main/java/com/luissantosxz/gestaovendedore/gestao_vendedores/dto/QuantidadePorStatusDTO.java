package com.luissantosxz.gestaovendedore.gestao_vendedores.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuantidadePorStatusDTO {

    private String status;
    private Long quantidade;
}
