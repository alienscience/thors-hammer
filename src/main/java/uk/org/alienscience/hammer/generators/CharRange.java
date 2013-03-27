package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Generator;

/**
 * Generates a single character string from a range of characters
 */
public class CharRange extends Generator<String> {

    private final int minIndex;
    private final int maxIndex;

    public CharRange(String minChar, String maxChar) {
        this.minIndex = minChar.charAt(0);
        this.maxIndex = maxChar.charAt(0);
    }

    @Override
    public String getValid(int index) {
        return new String(Character.toChars(index + minIndex));
    }

    @Override
    public int size() {
        return maxIndex - minIndex;
    }

}
