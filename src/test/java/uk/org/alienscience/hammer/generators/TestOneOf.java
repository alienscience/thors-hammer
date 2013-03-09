package uk.org.alienscience.hammer.generators;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.org.alienscience.hammer.Node;

/**
 * Test the OneOf selector
 */
public class TestOneOf {

	OneOf<Integer> oneOf;
	
	@Before
	public void setup() {
		List<Node<Integer>> nodes = new ArrayList<Node<Integer>>();
		nodes.add(new Literal<Integer>(0));
		nodes.add(new Literal<Integer>(1));
		nodes.add(new Literal<Integer>(2));
		nodes.add(new Literal<Integer>(3));
		oneOf = new OneOf<Integer>(nodes);
	}
	
	@Test
	public void testIndex() {
		for (int i = 0; i < 4; i++) {
			for (Node<Integer> n : oneOf.select(i)) {
				assertTrue(n instanceof Literal<?>);
				Literal<Integer> literal = (Literal<Integer>) n;
				assertEquals(i, (int) literal.get(i));
			}
		}
	}
	
}
