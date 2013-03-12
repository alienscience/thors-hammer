package uk.org.alienscience.hammer;


/**
 * A generator of values
 * @param <T> The type that is generated
 */
public abstract class Generator<T> implements Expression<T> {

    /**
     * Generate the item with the given index
     * @param i The index
     * @return A generated item of data
     */
    public abstract T get(int i);

    /**
     * Visit a node with the given visitor
     */
    public void applyVisitor(ExpressionVisitor<T> visitor) {
    	visitor.visit(this);
    }
}
