package com.luissantosxz.gestaovendedore.gestao_vendedores.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EStatusPedido {
    EM_TRATEMENTO("EM TRATAMENTO"),
    CADASTRADO("CADASTRADO"),
    CONCLUIDO("CONCLUIDO");

    private final String descricao;


}
