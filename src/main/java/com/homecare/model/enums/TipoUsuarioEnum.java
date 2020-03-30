package com.homecare.model.enums;

import com.homecare.model.interfaces.EnumInterface;

public enum TipoUsuarioEnum implements EnumInterface {
    PACIENTE,
    PROFISSIONAL;

    private String tipo;

    TipoUsuarioEnum() {
        this.tipo = tipo;
    }

    @Override
    public String getValue() {
        return this.tipo;
    }
}


