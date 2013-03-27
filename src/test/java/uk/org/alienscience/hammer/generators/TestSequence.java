package uk.org.alienscience.hammer.generators;

import org.junit.Before;
import org.junit.Test;
import uk.org.alienscience.hammer.Expression;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSequence {

	private Sequence<Integer> sequence;

	@Before
	public void setup() {
		List<Expression<Integer>> expressions = new ArrayList<>();
		expressions.add(new Literal<>(0));
		expressions.add(new Literal<>(1));
		expressions.add(new Literal<>(2));
		expressions.add(new Literal<>(3));
		sequence = new Sequence<>(expressions);
	}
	
	@Test
	public void testSequence() {
		int  i = 0;
		for (Expression<Integer> n: sequence.select(0)) {
			assertTrue(n instanceof Literal<?>);
			Literal<Integer> literal = (Literal<Integer>) n;
			assertEquals(i, (int) literal.getValid(0));
			i += 1;
		}
	}
}
