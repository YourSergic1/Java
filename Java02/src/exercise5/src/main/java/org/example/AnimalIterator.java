package org.example;

import java.util.List;

public class AnimalIterator implements BaseIterator<Animal> {
    private final List<Animal> arr;
    private Integer index;

    AnimalIterator(List<Animal> arr) {
        this.arr = arr;
        this.index = 0;
    }

    @Override
    public Animal next() {
        if (!hasNext()) return null;
        return arr.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < arr.size();
    }

    @Override
    public void reset() {
        index = 0;
    }
}
