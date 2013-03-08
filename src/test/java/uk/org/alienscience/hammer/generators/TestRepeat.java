package uk.org.alienscience.hammer.generators;

import org.junit.Before;
import org.junit.Test;
import uk.org.alienscience.hammer.Generator;
import uk.org.alienscience.hammer.Node;

import static org.junit.Assert.assertEquals;

/**
 * Test of repeated nodes
 */
public class TestRepeat {

    private Literal<String> literal;
    private Repeat repeat;

    @Before
    public void setup() {
        literal = new Literal<String>("a");
        repeat = new Repeat(literal, 0, 8);
    }

    @Test
    public void testRepeatIteration() {

        int count = 0;
        for (Node n : repeat.select(4)) {
            count += 1;
            assertEquals("a", ((Generator<String>) n).get(0));
        }

        assertEquals(4, count);
    }

    @Test
    public void testNestedRepeats() {
        Repeat outer = new Repeat(repeat, 0, 8);

        int count = 0;
        for (Node r : outer.select(4)) {
            for (Node n : ((Repeat) r).select(2)) {
                count += 1;
                assertEquals("a", ((Generator<String>) n).get(0));
            }
        }

        assertEquals(8, count);
    }
}
