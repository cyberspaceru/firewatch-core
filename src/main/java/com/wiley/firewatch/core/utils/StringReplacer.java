package com.wiley.firewatch.core.utils;

import java.util.regex.Pattern;

/**
 * Created by itatsiy on 5/26/2018.
 */
public class StringReplacer {
    private final MatchingType type;
    private final String blank;
    private final String replacement;

    private StringReplacer(MatchingType type, String blank, String replacement) {
        this.type = type;
        this.blank = blank;
        this.replacement = replacement;
    }

    public String replace(String target) {
        return StringReplacer.replace(target, type, blank, replacement);
    }

    public String replaceAll(String target) {
        return StringReplacer.replaceAll(target, type, blank, replacement);
    }

    @Override
    public String toString() {
        return String.format("{%s, %s, %s}", type, blank, replacement);
    }

    public static StringReplacer create(MatchingType type, String blank, String replacement) {
        return new StringReplacer(type, blank, replacement);
    }

    public static String replaceAll(String target, MatchingType type, String blank, String replacement) {
        if (target != null && StringMatcher.match(target, type, blank)) {
            switch (type) {
                case REGEXP:
                    return Pattern.compile(blank).matcher(target).replaceAll(replacement);
                case REGEXP_CASE_INSENSITIVE:
                    return Pattern.compile(blank, Pattern.CASE_INSENSITIVE).matcher(target).replaceAll(replacement);
                case CONTAINS:
                    return target.replaceAll(blank, replacement);
                default:
                    return StringReplacer.replace(target, type, blank, replacement);
            }
        }
        return target;
    }

    public static String replace(String target, MatchingType type, String blank, String replacement) {
        if (target != null && StringMatcher.match(target, type, blank)) {
            switch (type) {
                case REGEXP:
                    return Pattern.compile(blank).matcher(target).replaceFirst(replacement);
                case REGEXP_CASE_INSENSITIVE:
                    return Pattern.compile(blank, Pattern.CASE_INSENSITIVE).matcher(target).replaceFirst(replacement);
                case EQUALS:
                    return replacement;
                case EQUALS_IGNORE_CASE:
                    return replacement;
                case CONTAINS:
                    return target.replaceFirst(blank, replacement);
                case START_WITH:
                    return target.replaceFirst(blank, replacement);
                case END_WITH:
                    return target.substring(0, target.indexOf(blank)) + replacement;
            }
        }
        return target;
    }
}
