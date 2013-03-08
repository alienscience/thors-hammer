package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Node;
import uk.org.alienscience.hammer.Selector;

import java.util.Iterator;

/**
 * TODO: Document
 */
public class Repeat implements Selector {

    private final Node node;
    private final int minCount;
    private final int maxCount;

    public Repeat(Node node, int minCount, int maxCount) {
        this.node = node;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public int size() {
        return maxCount - minCount;
    }

    @Override
    public Iterable<Node> select(int index) {
        return new RepeatIterator(minCount + index);
    }

    // Iterate through the repeats minCount .. length
    private class RepeatIterator implements Iterable<Node> {
        private final int length;

        public RepeatIterator(int length) {
            assert length <= maxCount;
            this.length = length;
        }

        @Override
        public Iterator<Node> iterator() {
            return new Iterator<Node>() {
                private int count = 0;

                @Override
                public boolean hasNext() {
                    return count < length;
                }

                @Override
                public Node next() {
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
