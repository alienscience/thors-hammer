package uk.org.alienscience;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.ValidValues;
import uk.org.alienscience.hammer.generators.Literal;
import uk.org.alienscience.hammer.generators.OneOf;
import uk.org.alienscience.hammer.generators.Repeat;
import uk.org.alienscience.hammer.generators.Sequence;
import uk.org.alienscience.hammer.samplers.HeuristicHybrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Thors Hammer
 *
 */
public class Hammer<T> {

	private final Expression<T> expression;
	
	// Create a Hammer from a expression
	private Hammer(Expression<T> expression) {
		this.expression = expression;
	}

	public Iterable<T> generateValid() {
		return new ValidValues<>(expression, new HeuristicHybrid());
	}
	
	//------ Static helper methods -------------------------------------------
	
	/**
	 * Create a hammer with the given expression
	 * @param expression The expression to used to generate test values
	 * @return An object that will generate test data
	 */
	public static <U> Hammer<U> create(Expression<U> expression) {
		return new Hammer<>(expression);
	}

	/**
	 * Create a hammer with the given expression
	 * @param value The value to used to generate test values
	 * @return An object that will generate test data
	 */
	public static <U> Hammer<U> create(U value) {
		Literal<U> literal = new Literal<>(value);
		return new Hammer<>(literal);
	}
	
	/**
	 * Repeat the given value
	 * @param expression The value to repeat
	 * @param minCount The minimum number of repeations
	 * @param maxCount The maximum number of repeations
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Expression<U> repeat(Expression<U> expression, int minCount, int maxCount) {
		return new Repeat<>(expression, minCount, maxCount);
	}

	/**
	 * Repeat the given value
	 * @param value The value to repeat
	 * @param minCount The minimum number of repeations
	 * @param maxCount The maximum number of repeations
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Expression<U> repeat(U value, int minCount, int maxCount) {
		Literal<U> literal = new Literal<>(value);
		return new Repeat<>(literal, minCount, maxCount);
	}

	/**
	 * Returns one of a selection of values
	 * @param expressions One of these values will be selected
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Expression<U> oneOf(Expression<U>... expressions) {
		List<Expression<U>> expressionList = new ArrayList<>();
		for (Expression<U> n : expressions) {
			expressionList.add(n);
		}
		return new OneOf<>(expressionList);
	}
	
	/**
	 * Returns one of a selection of values
	 * @param nodes One of these values will be selected
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Expression<U> oneOf(U... values) {
		List<Expression<U>> expressionList = new ArrayList<Expression<U>>();
		for (U v : values) {
			Literal<U> literal = new Literal<>(v);
			expressionList.add(literal);
		}
		return new OneOf<>(expressionList);
	}

	/**
	 * Returns a sequence of values
	 * @param expressions The values
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Expression<U> sequence(Expression<U>... expressions) {
		// TODO check this 
		return new Sequence<>(Arrays.asList(expressions));
	}
	
	/**
	 * Returns a sequence of values
	 * @param nodes The values
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Expression<U> sequence(U... values) {
		List<Expression<U>> expressionList = new ArrayList<Expression<U>>();
		for (U v : values) {
			Literal<U> literal = new Literal<>(v);
			expressionList.add(literal);
		}
		return new Sequence<>(expressionList);
	}
}
