package uk.org.alienscience.hammer;

/**
 * A node in the generator tree
 * TODO rename to Expression as this class is exposed in the external API
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
    public void applyVisitor(GeneratorVisitor<T> visitor); 
}
