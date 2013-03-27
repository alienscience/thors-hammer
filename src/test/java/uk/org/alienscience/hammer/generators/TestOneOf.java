package uk.org.alienscience.hammer.generators;

import org.junit.Before;
import org.junit.Test;
import uk.org.alienscience.hammer.Expression;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the OneOf selector
 */
public class TestOneOf {

	private OneOf<Integer> oneOf;
	
	@Before
	public void setup() {
		List<Expression<Integer>> expressions = new ArrayList<>();
		expressions.add(new Literal<>(0));
		expressions.add(new Literal<>(1));
		expressions.add(new Literal<>(2));
		expressions.add(new Literal<>(3));
		oneOf = new OneOf<>(expressions);
	}
	
	@Test
	public void testIndex() {
		for (int i = 0; i < 4; i++) {
			for (Expression<Integer> n : oneOf.select(i)) {
				assertTrue(n instanceof Literal<?>);
				Literal<Integer> literal = (Literal<Integer>) n;
				assertEquals(i, (int) literal.getValid(i));
			}
		}
	}
	
}
