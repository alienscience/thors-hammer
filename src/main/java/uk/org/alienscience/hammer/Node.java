package uk.org.alienscience.hammer;

/**
 * An node in the generator tree
 */
public interface Node {

    /**
     * Returns the number of possibilities in the current node
     * @return The number of combinations in the current node
     */
    public int size();
}
