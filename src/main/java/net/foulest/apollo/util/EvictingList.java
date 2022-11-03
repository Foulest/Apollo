package net.foulest.apollo.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.LinkedList;

@AllArgsConstructor
public final class EvictingList<T> extends LinkedList<T> {

    @Getter
    private final int maxSize;

    public EvictingList(Collection<? extends T> c, int maxSize) {
        super(c);
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T t) {
        if (size() >= getMaxSize()) {
            removeFirst();
        }

        return super.add(t);
    }
}