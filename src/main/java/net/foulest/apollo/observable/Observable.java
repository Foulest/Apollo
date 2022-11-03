package net.foulest.apollo.observable;

import java.util.HashSet;
import java.util.Set;

public final class Observable<T> {

    private final Set<ChangeObserver<T>> observers = new HashSet<>();
    private T value;

    public Observable(T initValue) {
        value = initValue;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        T oldValue = this.value;
        this.value = value;
        observers.forEach(it -> it.handle(oldValue, value));
    }

    public ChangeObserver<T> observe(ChangeObserver<T> onChange) {
        observers.add(onChange);
        return onChange;
    }

    public void unobserve(ChangeObserver<T> onChange) {
        observers.remove(onChange);
    }

    @FunctionalInterface
    public interface ChangeObserver<T> {
        void handle(T from, T to);
    }
}
