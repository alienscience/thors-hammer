package uk.org.alienscience.hammer.samplers;

import uk.org.alienscience.hammer.Sampler;

import java.util.Random;

/**
 * A sampler that attempts to deterministically choose difficult values
 * before switching to a random selection
 */
public class HeuristicHybrid implements Sampler {

    private int numPoints;
    private final Random random;

    public HeuristicHybrid() {
        numPoints = 0;
        random = new Random();
    }

    @Override
    public int sample1D(int size) {

        // At first points are selected deterministically
        if (numPoints < 16 && numPoints < size) {
            return numPoints;
        }

        // Later random selection is used
        return random.nextInt(size);
    }

    @Override
    public void nextPoint() {
        numPoints += 1;
    }
}
