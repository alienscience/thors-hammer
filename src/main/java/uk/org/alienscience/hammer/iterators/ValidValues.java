package uk.org.alienscience.hammer.iterators;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.Sampler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An iterator that produces values that are consistent with a grammar
 */
public class ValidValues<T> implements Iterable<List<T>> {

    private final Expression<T> expression;
    private final Sampler sampler;

    public ValidValues(Expression<T> expression, Sampler sampler) {
        this.expression = expression;
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
            for (T atom : ValidValue.generate(expression, sampler)) {
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
