package com.luissantosxz.gestaovendedore.gestao_vendedores.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ESituacao {
    ATIVO("A"),
    INATIVO("I");

    private final String descricao;
}
