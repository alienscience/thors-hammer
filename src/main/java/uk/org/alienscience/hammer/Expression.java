package uk.org.alienscience.hammer;

/**
 * A node in the generator tree
 */
public interface Expression<T> {

    /**
     * Returns the number of possibilities in the current node
     * @return The number of combinations in the current node
     */
    public abstract int size();

    /**
     * Allow an external object to visit this node in a type safe way
     * @param visitor The object that will visit
     */
    public void applyVisitor(ExpressionVisitor<T> visitor);
}
