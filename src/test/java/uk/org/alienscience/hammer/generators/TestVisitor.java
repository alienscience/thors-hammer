package uk.org.alienscience.hammer.generators;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.org.alienscience.hammer.Generator;
import uk.org.alienscience.hammer.GeneratorVisitor;
import uk.org.alienscience.hammer.Node;
import uk.org.alienscience.hammer.Selector;


public class TestVisitor {

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
	
	private class Visitor implements GeneratorVisitor<Integer> {

		int i;
		
		@Override
		public void visit(Selector<Integer> selector) {
			for (i = 0; i < 4; i++) {
				for (Node<Integer> n : selector.select(i)) {
					n.applyVisitor(this);
				}
			}
		}

		@Override
		public void visit(Generator<Integer> generator) {
			assertTrue(generator instanceof Literal<?>);
			assertEquals(i, (int) generator.get(0));
		}
		
	}
	
	@Test
	public void testSimpleApply() {
		Visitor visitor = new Visitor();
		oneOf.applyVisitor(visitor);
	}
}
