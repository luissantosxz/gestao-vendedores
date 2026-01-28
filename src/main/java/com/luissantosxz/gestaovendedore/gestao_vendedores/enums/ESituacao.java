package com.luissantosxz.gestaovendedore.gestao_vendedores.enums;


import lombok.Getter;

public enum ESituacao {
    ATIVO("A"),
    INATIVO("I");

    @Getter
    private String descricao;

    ESituacao(String descricao){
        this.descricao = descricao;
    }

}
