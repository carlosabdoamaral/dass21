package com.carlosamaral.dass21.enums;

public enum ScaleEnum {
    SEM_SINTOMA("0 - Sem sintomas"),
    LEVE("1 - Sintomas leves"),
    MODERADO("2 - Sintomas moderados"),
    GRAVE("3 - Sintomas Graves"),
    MUITO_GRAVE("4 - Sintomas Muito graves");

    private final String descricao;

    ScaleEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
