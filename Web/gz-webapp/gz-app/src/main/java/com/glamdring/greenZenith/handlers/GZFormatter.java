package com.glamdring.greenZenith.handlers;

import com.glamdring.greenZenith.handlers.constants.GZFormats;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GZFormatter {

    private static Pattern pattern;
    private static Matcher matcher;

    public static boolean isValid(String input, GZFormats format) {
        pattern = Pattern.compile(format.getFormat());
        matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
