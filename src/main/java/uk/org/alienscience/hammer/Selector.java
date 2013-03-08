package uk.org.alienscience.hammer;

/**
 * TODO: Document
 */
public interface Selector extends Node {

    /**
     * Iterate over the combination that has the given index
     */
    public Iterable<Node> select(int index);


}
