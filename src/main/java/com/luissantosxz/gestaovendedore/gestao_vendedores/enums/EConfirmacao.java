package com.luissantosxz.gestaovendedore.gestao_vendedores.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EConfirmacao {

    PENDENTE("PENDENTE"),
    CONFIRMADO("CONFIRMADO"),
    CANCELADO("CANCELADO");

    private final String descricao;
}