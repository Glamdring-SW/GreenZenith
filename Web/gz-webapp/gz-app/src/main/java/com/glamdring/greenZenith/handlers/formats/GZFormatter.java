package com.glamdring.greenZenith.handlers.formats;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.glamdring.greenZenith.handlers.constants.GZFormats;

/**
 * Aids in making comparisons with certain established and predefined formats,
 * and a specific input value, providing a concise and decisive result that
 * determines if a pattern of a regular expression is met or not.
 *
 * @see GZFormats
 * @author Glamdring (Σxz)
 * @version 1.1.0
 * @since 0.2
 */
public class GZFormatter {

    /**
     * Compiles a desired regular expression into a pattern to look for.
     */
    private static Pattern pattern;
    /**
     * Utilizes a pattern to realize comparisions and find matches.
     */
    private static Matcher matcher;

    /**
     * Creates a new Formatter, capable of checking if a string is within a
     * certain boundary or if it follows a certain predefined or given pattern.
     */
    public GZFormatter() {
    }

    /**
     * Realizes a comparison, with respect to a selected format, converted into
     * a pattern, to determine if the input given is valid in regards to the
     * predefined regular expression pattern. Has a check for if the given
     * parameter is null or blank.
     *
     * @param input The value to make comparisons with.
     * @param format The predefined format to look for and check in a
     * comparison. See
     * {@link com.glamdring.greenZenith.handlers.constants.GZFormats}
     * @return Approves or negates the validity of the input.
     */
    public static boolean isValid(String input, GZFormats format) {
        if (input == null || input.isBlank()) {
            return false;
        }
        pattern = Pattern.compile(format.getFormat());
        matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * Realizes a comparison, with respect to a given format, converted into a
     * pattern, to determine if the input given is valid in regards to the given
     * regular expression pattern. Has a check for if the given parameter is
     * null or blank.
     *
     * @param input The value to make comparisons with.
     * @param format The format to look for and check in a comparison.
     * @return Approves or negates the validity of the input.
     */
    public static boolean isValid(String input, String format) {
        if (input == null || input.isBlank() || format == null || format.isBlank()) {
            return false;
        }
        pattern = Pattern.compile(format);
        matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * Checks if the length of a given string is within an upper boundary. Has a
     * check for if the given parameter is null or blank.
     *
     * @param input The string whose length needs to be validated.
     * @param maxLength The maximum length it can be.
     * @return Approves or negates the validity of the input.
     */
    public static boolean isValidMaxLength(String input, int maxLength) {
        if (input == null || input.isBlank()) {
            return false;
        }
        return input.length() <= maxLength;
    }

    /**
     * Checks if the length of a given string is within a lower boundary. Has a
     * check for if the given parameter is null or blank.
     *
     * @param input The string whose length needs to be validated.
     * @param minLength The minimum length it can be.
     * @return Approves or negates the validity of the input.
     */
    public static boolean isValidMinLength(String input, int minLength) {
        if (input == null || input.isBlank()) {
            return false;
        }
        return input.length() >= minLength;
    }

    /**
     * Checks if the length of a given string is within an upper and lower
     * boundary. Has a check for if the given parameter is null or blank.
     *
     * @param input The string whose length needs to be validated.
     * @param minLength The maximum length it can be.
     * @param maxLength The minimum length it can be.
     * @return Approves or negates the validity of the input.
     */
    public static boolean isValidLength(String input, int minLength, int maxLength) {
        if (input == null || input.isBlank()) {
            return false;
        }
        return input.length() >= minLength && input.length() <= maxLength;
    }

}
