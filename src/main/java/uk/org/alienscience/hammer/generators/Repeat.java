package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Node;
import uk.org.alienscience.hammer.Selector;

import java.util.Iterator;

/**
 * A selector that repeats a node from minCount .. maxCount
 */
public class Repeat<T> extends Selector<T> {

    private final Node<T> node;
    private final int minCount;
    private final int maxCount;

    public Repeat(Node<T> node, int minCount, int maxCount) {
        this.node = node;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public int size() {
        return maxCount - minCount;
    }

    @Override
    public Iterable<Node<T>> select(int index) {
        return new RepeatIterator(minCount + index);
    }

    // Iterate through the repeats minCount .. length
    private class RepeatIterator implements Iterable<Node<T>> {
        private final int length;

        public RepeatIterator(int length) {
            assert length <= maxCount;
            this.length = length;
        }

        @Override
        public Iterator<Node<T>> iterator() {
            return new Iterator<Node<T>>() {
                private int count = 0;

                @Override
                public boolean hasNext() {
                    return count < length;
                }

                @Override
                public Node<T> next() {
                    count++;
                    return node;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}
