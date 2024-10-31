package com.glamdring.greenZenith.handlers;

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
 * @version 1.0.0
 * @since 0.1
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
     * Realizes a comparison, with respect to a given format, converted into a
     * pattern, to determine if the input given is valid in regards to the
     * regular expression pattern.
     *
     * @param input The value to make comparisons with.
     * @param format The format to look for and check in a comparison.
     * @return Approves or negates the validity of the input.
     */
    public static boolean isValid(String input, GZFormats format) {
        pattern = Pattern.compile(format.getFormat());
        matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
