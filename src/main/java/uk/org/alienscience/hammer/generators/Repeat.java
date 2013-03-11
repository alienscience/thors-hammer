package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.Selector;

import java.util.Iterator;

/**
 * A selector that repeats a expression from minCount .. maxCount
 */
public class Repeat<T> extends Selector<T> {

    private final Expression<T> expression;
    private final int minCount;
    private final int maxCount;

    public Repeat(Expression<T> expression, int minCount, int maxCount) {
        this.expression = expression;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public int size() {
        return maxCount - minCount;
    }

    @Override
    public Iterable<Expression<T>> select(int index) {
        return new RepeatIterator(minCount + index);
    }

    // Iterate through the repeats minCount .. length
    private class RepeatIterator implements Iterable<Expression<T>> {
        private final int length;

        public RepeatIterator(int length) {
            assert length <= maxCount;
            this.length = length;
        }

        @Override
        public Iterator<Expression<T>> iterator() {
            return new Iterator<Expression<T>>() {
                private int count = 0;

                @Override
                public boolean hasNext() {
                    return count < length;
                }

                @Override
                public Expression<T> next() {
                    count++;
                    return expression;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}
