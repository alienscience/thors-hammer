package uk.org.alienscience;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.org.alienscience.Hammer.*;

public class TestHammer {

    @Test
    public void testSimpleCreate() {
        Hammer<Integer> hammer = Hammer.create(oneOf(1,2,3,4,5));

        int i = 0;
        for (List<Integer> value : hammer.validLists()) {
            i += 1;
            if (i > 5) break;
            assertEquals(i, (int) value.get(0));
        }
    }

    @Test
    public void testCompositeCreate() {
        Hammer<Integer> hammer = Hammer.create(
                sequence(1,2,3),
                repeat(4, 1, 3),
                literal(5)
        );

        int numRepeats = 0;
        for (List<Integer> value : hammer.validLists() ) {
            numRepeats += 1;
            if (numRepeats > 3) break;
            for (int i = 0; i < value.size(); i++) {
                if (i < 3) {
                    assertEquals(i + 1, (int) value.get(i));
                } else if (i < numRepeats + 3) {
                    assertEquals(4, (int) value.get(i));
                } else {
                    assertEquals(5, (int) value.get(i));
                }

            }

        }

    }
}
