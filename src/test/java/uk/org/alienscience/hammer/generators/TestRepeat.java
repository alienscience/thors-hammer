package uk.org.alienscience.hammer.generators;

import org.junit.Before;
import org.junit.Test;
import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.Generator;

import static org.junit.Assert.assertEquals;

/**
 * Test of repeated expressions
 */
public class TestRepeat {

    private Literal<String> literal;
    private Repeat<String> repeat;

    @Before
    public void setup() {
        literal = new Literal<String>("a");
        repeat = new Repeat<String>(literal, 0, 8);
    }

    @Test
    public void testRepeatIteration() {

        int count = 0;
        for (Expression<String> n : repeat.select(4)) {
            count += 1;
            assertEquals("a", ((Generator<String>) n).getValid(0));
        }

        assertEquals(4, count);
    }

    @Test
    public void testNestedRepeats() {
        Repeat<String> outer = new Repeat<String>(repeat, 0, 8);

        int count = 0;
        for (Expression<String> r : outer.select(4)) {
            for (Expression<String> n : ((Repeat<String>) r).select(2)) {
                count += 1;
                assertEquals("a", ((Generator<String>) n).getValid(0));
            }
        }

        assertEquals(8, count);
    }
}
