package uk.org.alienscience.hammer.iterators;

import uk.org.alienscience.hammer.Sampler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An iterator that produces many values from an iterator that produces 1 value
 */
public class MultipleValues<T> implements Iterable<List<T>> {

    private final Iterable<T> iterable;
    private final Sampler sampler;

    public MultipleValues(Iterable<T> iterable, Sampler sampler) {
        this.iterable = iterable;
        this.sampler = sampler;
    }

    @Override
    public Iterator<List<T>> iterator() {
        return new ValidValuesIterator();
    }

    private class ValidValuesIterator implements Iterator<List<T>> {

        @Override
        public boolean hasNext() {
            // Assume that there is a combinatorial explosion
            return true;
        }

        @Override
        public List<T> next() {
            // Generate a single valid value
            List<T> ret = new ArrayList<>();
            for (T atom : iterable) {
                ret.add(atom);
            }

            // Inform the sampler that a value has been obtained
            sampler.nextPoint();

            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
