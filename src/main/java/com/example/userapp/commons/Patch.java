package com.example.userapp.commons;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Patch<T> {
    private final boolean patched;
    private final T value;

    private Patch(boolean patched, T value) {
        this.patched = patched;
        this.value = value;
    }

    private Patch() {
        this.patched = false;
        this.value = null;
    }

    public static <T> Patch<T> empty() {
        return new Patch<>();
    }

    public T getValue() {
        if (!patched) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public void ifPatched(Consumer<? super T> action) {
        if (isPatched()) {
            action.accept(this.value);
        }
    }

    public boolean isPatched() {
        return patched;
    }

    public <U> Patch<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        return !isPatched() ? Patch.empty() : Patch.of(mapper.apply(this.value));
    }

    public static <T> Patch<T> of(T value) {
        return new Patch<>(true, value);
    }
}