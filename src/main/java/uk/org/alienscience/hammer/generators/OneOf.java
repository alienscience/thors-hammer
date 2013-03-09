package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Node;
import uk.org.alienscience.hammer.Selector;

import java.util.Iterator;
import java.util.List;

/**
 * Select one of a list of nodes
 */
public class OneOf<T> extends Selector<T> {

    private final List<Node<T>> nodes;

    public OneOf(List<Node<T>> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public Iterable<Node<T>> select(int index) {
        return new OneOfIterator(index);
    }

    private class OneOfIterator implements Iterable<Node<T>> {
        private final int index;

        public OneOfIterator(int index) {
            this.index = index;
        }

        @Override
        public Iterator<Node<T>> iterator() {
            return new Iterator<Node<T>>() {
                boolean done = false;

                @Override
                public boolean hasNext() {
                    return !done;
                }

                @Override
                public Node<T> next() {
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
