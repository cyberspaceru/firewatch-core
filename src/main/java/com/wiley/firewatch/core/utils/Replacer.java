package com.wiley.firewatch.core.utils;

import java.util.regex.Pattern;

/**
 * Created by itatsiy on 5/26/2018.
 */
public class Replacer {
    private final MatchingType type;
    private final String expected;
    private final String value;

    private Replacer(MatchingType type, String expected, String value) {
        this.type = type;
        this.expected = expected;
        this.value = value;
    }

    public static Replacer create(MatchingType type, String expected, String value) {
        return new Replacer(type, expected, value);
    }

    public static String replace(String source, MatchingType type, String expected, String value) {
        if (source != null && StringMatcher.match(source, type, expected)) {
            switch (type) {
                case REGEXP:
                    return Pattern.compile(expected).matcher(source).replaceFirst(value);
                case REGEXP_CASE_INSENSITIVE:
                    return Pattern.compile(expected, Pattern.CASE_INSENSITIVE).matcher(source).replaceFirst(value);
                case EQUALS:
                    return value;
                case EQUALS_IGNORE_CASE:
                    return value;
                case CONTAINS:
                    return source.replaceAll(expected, value);
                case START_WITH:
                    return source.replaceFirst(expected, value);
                case END_WITH:
                    return source.substring(0, source.indexOf(expected)) + value;
            }
        }
        return source;
    }
}
