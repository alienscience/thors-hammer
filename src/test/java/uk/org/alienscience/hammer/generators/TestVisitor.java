package uk.org.alienscience.hammer.generators;

import org.junit.Before;
import org.junit.Test;
import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.ExpressionVisitor;
import uk.org.alienscience.hammer.Generator;
import uk.org.alienscience.hammer.Selector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestVisitor {

	OneOf<Integer> oneOf;
	
	@Before
	public void setup() {
		List<Expression<Integer>> expressions = new ArrayList<>();
		expressions.add(new Literal<>(0));
		expressions.add(new Literal<>(1));
		expressions.add(new Literal<>(2));
		expressions.add(new Literal<>(3));
		oneOf = new OneOf<>(expressions);
	}
	
	private class Visitor implements ExpressionVisitor<Integer> {

		int i;
		
		@Override
		public void visit(Selector<Integer> selector) {
			for (i = 0; i < 4; i++) {
				for (Expression<Integer> e : selector.select(i)) {
					e.applyVisitor(this);
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
