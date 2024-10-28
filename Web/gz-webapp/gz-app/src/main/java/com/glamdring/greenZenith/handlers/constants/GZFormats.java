package com.glamdring.greenZenith.handlers.constants;

public enum GZFormats {

    USERNAME("^.{4,49}$"), //Formato de mas de 3 letras a menos de 50 (Añadir a la wiki)
    EMAIL("^(?=.{1,99}$)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"), //Formato de maximo 100 caracteres y estandar comun de correo (Añadir a la wiki)
    PASSWORD("^(?=.*[A-Z])(?=(.*\\d){3,})(?=.*[!@#$%^&*(),.?\":{}|<>]).{12,}$"); //Formato para contraseñas de mas de 12 letras con una mayuscula y caracter especial

    private final String format;

    GZFormats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
