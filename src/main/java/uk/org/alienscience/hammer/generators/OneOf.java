package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Node;
import uk.org.alienscience.hammer.Selector;

import java.util.Iterator;
import java.util.List;

/**
 * TODO: Document
 */
public class OneOf implements Selector {

    private final List<Node> nodes;

    public OneOf(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public Iterable<Node> select(int index) {
        return new OneOfIterator(index);
    }

    private class OneOfIterator implements Iterable<Node> {
        private final int index;

        public OneOfIterator(int index) {
            this.index = index;
        }

        @Override
        public Iterator<Node> iterator() {
            return new Iterator<Node>() {
                boolean done = false;

                @Override
                public boolean hasNext() {
                    return !done;
                }

                @Override
                public Node next() {
                    done = true;
                    return nodes.get(index);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}
