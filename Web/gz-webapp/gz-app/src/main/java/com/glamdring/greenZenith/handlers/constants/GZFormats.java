package com.glamdring.greenZenith.handlers.constants;

/**
 * Contains regular expressions that establish a certain format and rules to
 * follow when filling a determined value, so it guarantees a correct
 * compatibility with the Database, for the recommended usage @see GZFormatter
 *
 * @author Glamdring (Σxz)
 * @version 1.0.3
 * @since 0.2
 */
public enum GZFormats {

    /**
     * Standard mailing format with a limit of 100 characters.
     */
    EMAIL("^[a-zA-Z0-9!#$%&’*+\\-\\/=?^_`{|}~\\.]+@{1}(?:gmail|yahoo|hotmail|outlook|msn|aol|live|mail|ymail|comcast|icloud|protonmail|wanadoo|zoho)(?:\\.[a-z]{2,3})?(?:\\.[a-z]{2}|)$"),
    /**
     * More than 12 characters, one uppercase character, one special character
     * and one number, specially compatible.
     */
    PASSWORD("^(?=.*[A-Z])(?=.*[a-z])(?=.+\\d)(?=.+[\\\\u0021-\\\\u002F\\\\u003A-\\\\u0040\\\\u005B-\\\\u0060\\\\u007B-\\\\u007E\\\\u00A1-\\\\u00BF\\\\u2000-\\\\u206F\\\\u2190-\\\\u21FF\\\\u2600-\\\\u26FF\\\\u2700-\\\\u27BF])[^\\r\\n]{12,100}$");

    /**
     * Holds the value of each item's regular expression.
     */
    private final String format;

    /**
     * Assigns a special value to a certain item in an instance of this class.
     *
     * @param format The regular expression to compare with.
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
