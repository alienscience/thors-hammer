package uk.org.alienscience.hammer;

/**
 * A node in the generator tree
 */
public interface Node<T> {

    /**
     * Returns the number of possibilities in the current node
     * @return The number of combinations in the current node
     */
    public abstract int size();
    
    public void applyVisitor(GeneratorVisitor<T> visitor); 
}
