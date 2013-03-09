package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Generator;

/**
 * A generator that always generates the same value
 */
public class Literal<T> extends Generator<T> {
    private final T value;

    public Literal(T value) {
        this.value = value;
    }


    @Override
    public T get(int i) {
       return value;
    }

    @Override
    public int size() {
        return 1;
    }
}
