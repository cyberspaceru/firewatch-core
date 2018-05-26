package com.wiley.firewatch.core.utils;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.regex.Pattern;

@Accessors(fluent = true)
public class StringMatcher {
    @Getter
    private final MatchingType type;
    @Getter
    private final String expected;

    private StringMatcher(MatchingType type, String expected) {
        this.type = type;
        this.expected = expected;
    }

    public boolean match(String actual) {
        return StringMatcher.match(actual, type, expected);
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", type, expected);
    }

    public static StringMatcher create(MatchingType type, String expected) {
        return new StringMatcher(type, expected);
    }

    public static boolean match(String actual, MatchingType type, String expected) {
        if (actual == null && expected == null) {
            return true;
        } else if (actual == null || expected == null) {
            return false;
        } else {
            switch (type) {
                case REGEXP:
                    return Pattern.compile(expected).matcher(actual).find();
                case REGEXP_CASE_INSENSITIVE:
                    return Pattern.compile(expected, Pattern.CASE_INSENSITIVE).matcher(actual).find();
                case EQUALS:
                    return actual.equals(expected);
                case EQUALS_IGNORE_CASE:
                    return actual.equalsIgnoreCase(expected);
                case CONTAINS:
                    return actual.contains(expected);
                case START_WITH:
                    return actual.startsWith(expected);
                case END_WITH:
                    return actual.endsWith(expected);
            }
        }
        return false;
    }
}
