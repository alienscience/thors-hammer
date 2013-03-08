package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Generator;

/**
 * Generates from the characters A-Z, a-z
 */
public class AsciiAlpha implements Generator<String> {

    @Override
    public String get(int index) {
        if (index < 26) {
            return new String(Character.toChars(index + 0x41));
        } else {
            return new String(Character.toChars(index + 0x61));
        }
    }

    @Override
    public int size() {
        return 52;
    }
}
