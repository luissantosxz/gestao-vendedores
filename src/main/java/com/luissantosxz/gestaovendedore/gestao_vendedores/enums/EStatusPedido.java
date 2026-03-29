package com.luissantosxz.gestaovendedore.gestao_vendedores.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EStatusPedido {
    CADASTRADO("CADASTRADO"),
    EM_TRATEMENTO("EM TRATAMENTO"),
    AJUSTE_SOLICITACAO("AJUSTE"),
    CONCLUIDO("CONCLUIDO");

    private final String descricao;

}
