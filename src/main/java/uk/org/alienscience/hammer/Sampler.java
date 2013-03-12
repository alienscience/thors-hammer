package uk.org.alienscience.hammer;

/**
 * An sampling algorithm
 */
public interface Sampler {

    /**
     * Sample from a one dimensional slice of the selection of possibilities
     * @param size The number of alternatives
     */
    int sample1D(int size);

    /**
     * Called when all the dimensions have been sampled and a sample is complete
     */
    void nextPoint();
}
