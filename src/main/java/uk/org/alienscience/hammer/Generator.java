package uk.org.alienscience.hammer;


/**
 * A generator of values
 * @param <T> The type that is generated
 */
public interface Generator<T> extends Node {

    /**
     * Generate the item with the given index
     * @param i The index
     * @return A generated item of data
     */
    public T get(int i);

}
