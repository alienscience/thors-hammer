package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Generator;

/**
 * Generates from the characters A-Z, a-z
 */
public class AsciiAlpha extends Generator<String> {

    @Override
    public String getValid(int index) {
        if (index < 26) {
            return new String(Character.toChars(index + 0x41));
        } else {
            return new String(Character.toChars(index + 0x61));
        }
    }

    @Override
    public String getInvalid(int i) {
        int charCode;
        if (i % 2 == 0) {
            charCode = 0x41 - i - 1;
        } else if (i < 7) {
            charCode = 0x5a + i;
        } else {
            charCode = 0x7a + i;
        }

        return new String(Character.toChars(charCode));
    }

    @Override
    public int size() {
        return 52;
    }

}
