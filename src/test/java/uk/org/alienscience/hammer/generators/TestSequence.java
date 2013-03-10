package uk.org.alienscience.hammer.generators;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import uk.org.alienscience.hammer.Node;

public class TestSequence {

	Sequence<Integer> sequence;

	@Before
	public void setup() {
		List<Node<Integer>> nodes = new ArrayList<Node<Integer>>();
		nodes.add(new Literal<Integer>(0));
		nodes.add(new Literal<Integer>(1));
		nodes.add(new Literal<Integer>(2));
		nodes.add(new Literal<Integer>(3));
		sequence = new Sequence<Integer>(nodes);
	}
	
	@Test
	public void testSequence() {
		int  i = 0;
		for (Node<Integer> n: sequence.select(0)) {
			assertTrue(n instanceof Literal<?>);
			Literal<Integer> literal = (Literal<Integer>) n;
			assertEquals(i, (int) literal.get(0));
			i += 1;
		}
	}
}
