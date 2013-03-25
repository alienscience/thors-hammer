package uk.org.alienscience;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

    @Ignore
    @Test
    public void testInvalid() {
        Hammer<Integer> hammer = Hammer.create(oneOf(1,2,3,4,5));

        int i = 0;
        for (List<Integer> value : hammer.invalidLists()) {
            i += 1;
            if (i > 5) break;
            int invalidNum = value.get(0);
            assertTrue(invalidNum == 0 || invalidNum == 6);
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

    @Test
    public void testReuseExpression() {
        Hammer<Integer> evenNumbers = Hammer.create(oneOf(2,4,6,8));
        Hammer<Integer> oddNumbers = Hammer.create(oneOf(1,3,5,7));
        Hammer<Integer> eoeNumbers = Hammer.create(evenNumbers, oddNumbers, evenNumbers);

        int i = 0;
        for (List<Integer> value : eoeNumbers.validLists()) {
            assertTrue(value.get(0) % 2 == 0);
            assertTrue(value.get(1) % 2 == 1);
            assertTrue(value.get(2) % 2 == 0);
            i += 1;
            if (i > 3) break;
        }
    }
}
