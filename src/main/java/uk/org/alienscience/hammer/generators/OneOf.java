package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.Selector;

import java.util.Iterator;
import java.util.List;

/**
 * Select one of a list of expressions
 */
public class OneOf<T> extends Selector<T> {

    private final List<Expression<T>> expressions;

    public OneOf(List<Expression<T>> expressions) {
        this.expressions = expressions;
    }

    @Override
    public int size() {
        return expressions.size();
    }

    @Override
    public Iterable<Expression<T>> select(int index) {
        return new OneOfIterator(index);
    }

    private class OneOfIterator implements Iterable<Expression<T>> {
        private final int index;

        public OneOfIterator(int index) {
            this.index = index;
        }

        @Override
        public Iterator<Expression<T>> iterator() {
            return new Iterator<Expression<T>>() {
                boolean done = false;

                @Override
                public boolean hasNext() {
                    return !done;
                }

                @Override
                public Expression<T> next() {
                    done = true;
                    return expressions.get(index);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

}
