package com.wiley.firewatch.core.observers.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.BiPredicate;

import static java.util.Optional.ofNullable;

public abstract class JsonObserver<K> {
    private static final Gson GSON = new GsonBuilder().create();
    private final Class<K> objectClass;
    private final K instance;
    private final BiPredicate<K, K> predicate;

    public JsonObserver(Class<K> objectClass, K instance, BiPredicate<K, K> predicate) {
        this.objectClass = objectClass;
        this.instance = instance;
        this.predicate = predicate;
    }

    protected boolean observeJson(String text) {
        if (instance != null) {
            K actual = null;
            try {
                actual = GSON.fromJson(text, objectClass);
            } catch (Exception ignored) {
                // Ignored exception
            }
            return actual != null && predicate.test(actual, instance);
        }
        return text == null;
    }

    @Override
    public String toString() {
        return "JSON(" + ofNullable(objectClass).map(Class::getSimpleName).orElse("null") + ")";
    }
}
