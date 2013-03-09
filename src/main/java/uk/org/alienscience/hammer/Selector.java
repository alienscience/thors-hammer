package uk.org.alienscience.hammer;

/**
 * A Selector selects nodes to use for generation or further selection
 */
public abstract class Selector<T> implements Node<T> {

    /**
     * Iterate over the combination of nodes that has the given index
     */
    public abstract Iterable<Node<T>> select(int index);

    /**
     * Visit a node with the given visitor
     */
    public void applyVisitor(GeneratorVisitor<T> visitor) {
    	visitor.visit(this);
    }
}
