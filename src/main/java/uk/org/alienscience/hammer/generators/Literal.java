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
    public T getValid(int i) {
       return value;
    }

    /**
     * Attempts to generate an invalid value
     * @param i The index
     * @return A possibly invalid value
     */
    @Override
    public T getInvalid(int i) {
        try {
            return (T) value.getClass().newInstance();
        } catch (InstantiationException e) {
            return value;
        } catch (IllegalAccessException e) {
            return value;
        }
    }

    @Override
    public int size() {
        return 1;
    }
}
