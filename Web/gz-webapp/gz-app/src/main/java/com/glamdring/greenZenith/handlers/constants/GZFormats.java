package com.glamdring.greenZenith.handlers.constants;

/**
 * Contains regular expressions that establish a certain format and rules to
 * follow when filling a determined value, so it guarantees a correct
 * compatibility with the Database, for the recommended usage @see GZFormatter
 *
 * @author Glamdring (Σxz)
 * @version 1.0.0
 * @since 0.2
 */
public enum GZFormats {

    /**
     * More than 3 characters and less than 50.
     */
    USERNAME("^.{4,49}$"),
    /**
     * 100 characters at maximum, an {@code @} character, and a correct domain
     * name.
     */
    EMAIL("^(?=.{1,99}$)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),
    /**
     * More than 12 characters, one uppercase character, one special character.
     */
    PASSWORD("^(?=.*[A-Z])(?=(.*\\d){3,})(?=.*[!@#$%^&*(),.?\":{}|<>]).{12,}$"); //Formato para contraseñas de mas de 12 letras con una mayuscula y caracter especial

    /**
     * Holds the value of each item's regular expression.
     */
    private final String format;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param format
     */
    GZFormats(String format) {
        this.format = format;
    }

    /**
     * Gets the predefined exception message designed to give significant but
     * concise information.
     *
     * @return The regular expression string value held by the item.
     */
    public String getFormat() {
        return format;
    }
}
