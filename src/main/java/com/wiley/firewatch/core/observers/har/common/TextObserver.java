package com.wiley.firewatch.core.observers.har.common;

import java.util.function.BiPredicate;

public abstract class TextObserver {
    private final String expected;
    private final BiPredicate<String, String> predicate;

    public TextObserver(String expected, BiPredicate<String, String> predicate) {
        this.expected = expected;
        this.predicate = predicate;
    }

    protected boolean observeText(String actual) {
        try {
            return predicate.test(actual, expected);
        } catch (Exception ignored) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Text(" + expected + ")";
    }
}
