package uk.org.alienscience;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.conversion.ToString;
import uk.org.alienscience.hammer.generators.Literal;
import uk.org.alienscience.hammer.generators.OneOf;
import uk.org.alienscience.hammer.generators.Repeat;
import uk.org.alienscience.hammer.generators.Sequence;
import uk.org.alienscience.hammer.iterators.ValidValues;
import uk.org.alienscience.hammer.samplers.HeuristicHybrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Thors Hammer
 *
 */
public class Hammer<T> {

    // TODO Look for a safe way to deal with the varargs heap corruption warnings

	private final Expression<T> expression;
	
	// Create a Hammer from a expression
	private Hammer(Expression<T> expression) {
		this.expression = expression;
	}

	public Iterable<List<T>> validLists() {
		return new ValidValues<>(expression, new HeuristicHybrid());
	}

    public Iterable<List<T>> invalidLists() {
        // TODO implement
        return null;
    }

    public Iterable<String> validStrings() {
        ValidValues<String> values = new ValidValues<>(
                (Expression<String>) expression, new HeuristicHybrid());
        return ToString.flatten(values);
    }

	//------ Static helper methods -------------------------------------------
	
	/**
	 * Create a hammer with the given expression
	 * @param expressions The expressions to be used to generate test values
	 * @return An object that will generate test data
	 */
	public static <U> Hammer<U> create(Expression<U>... expressions) {
        if (expressions.length == 1) {
		    return new Hammer<>(expressions[0]);
        }

        List<Expression<U>> expressionList = Arrays.asList(expressions);
        return new Hammer<>(new Sequence<>(expressionList));
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
	 * @param values One of these values will be selected
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
	 * @param values The values
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

    /**
     * Returns a single literal value
     * @param value The value
     * @return An object that can be passed to other methods or used for generation
     */
    public static <U> Expression<U> literal(U value) {
        return new Literal<>(value);
    }
}
