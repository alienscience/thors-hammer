package uk.org.alienscience;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.ExpressionVisitor;
import uk.org.alienscience.hammer.Sampler;
import uk.org.alienscience.hammer.conversion.ToString;
import uk.org.alienscience.hammer.generators.*;
import uk.org.alienscience.hammer.iterators.MultipleValues;
import uk.org.alienscience.hammer.iterators.ValidValue;
import uk.org.alienscience.hammer.samplers.HeuristicHybrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  Thors Hammer
 *
 */
public class Hammer<T> implements Expression<T> {

    // TODO Look for a safe way to deal with the varargs heap corruption warnings

	private final Expression<T> expression;
	
	// Create a Hammer from a expression
	private Hammer(Expression<T> expression) {
		this.expression = expression;
	}

    /**
     * Repeat the expression
     * @param minCount The minimum number of times the expression will be repeated
     * @param maxCount The maximum number of times the expression will be repeated
     * @return A new Hammer that generates repeated values
     */
    public Hammer<T> repeat(int minCount, int maxCount) {
        return repeat(expression, minCount, maxCount);
    }

	public Iterable<List<T>> validLists() {
        Sampler sampler = new HeuristicHybrid();
        return new MultipleValues<>(ValidValue.generate(expression, sampler), sampler);
	}

    @SuppressWarnings("unchecked")
    public Iterable<String> validStrings() {
        Sampler sampler = new HeuristicHybrid();
        Iterable<String> valueIterable;
        try {
            valueIterable = ValidValue.generate((Expression<String>) expression, sampler);
        } catch(ClassCastException e) {
            throw new ClassCastException("Hammer.validStrings can only be called on Hammer<String> types");
        }
        Iterable<List<String>> valuesIterable = new MultipleValues<>(valueIterable, sampler);
        return ToString.flatten(valuesIterable);
    }

    //------ A Hammer can be reused as an expression ------------------------

    @Override
    public int size() {
        return expression.size();
    }

    @Override
    public void applyVisitor(ExpressionVisitor<T> visitor) {
        expression.applyVisitor(visitor);
    }

    //------ Static helper methods -------------------------------------------


    public static Hammer<String> charRange(String minChar, String maxChar) {
        return new Hammer<>(new CharRange(minChar,maxChar));
    }

	/**
	 * Repeat the given value
	 * @param expression The value to repeat
	 * @param minCount The minimum number of repeations
	 * @param maxCount The maximum number of repeations
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Hammer<U> repeat(final Expression<U> expression, int minCount, int maxCount) {
		return new Hammer<>(new Repeat<>(expression, minCount, maxCount));
	}

	/**
	 * Repeat the given value
	 * @param value The value to repeat
	 * @param minCount The minimum number of repeations
	 * @param maxCount The maximum number of repeations
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Hammer<U> repeat(final U value, int minCount, int maxCount) {
		Literal<U> literal = new Literal<>(value);
		return new Hammer<>(new Repeat<>(literal, minCount, maxCount));
	}

    @SafeVarargs
    public static <U> Hammer<U> optional(final Expression<U>... expressions) {
        if (expressions.length == 1) {
            return repeat(expressions[0], 0, 1);
        }
        return repeat(sequence(expressions), 0, 1);
    }

	/**
	 * Returns one of a selection of values
	 * @param expressions One of these values will be selected
	 * @return An object that can be passed to other methods or used for generation
	 */
    @SafeVarargs
	public static <U> Hammer<U> oneOf(final Expression<U>... expressions) {
		List<Expression<U>> expressionList = new ArrayList<>();
        Collections.addAll(expressionList, expressions);
		return new Hammer<>(new OneOf<>(expressionList));
	}
	
	/**
	 * Returns one of a selection of values
	 * @param values One of these values will be selected
	 * @return An object that can be passed to other methods or used for generation
	 */
    @SafeVarargs
	public static <U> Hammer<U> oneOf(final U... values) {
		List<Expression<U>> expressionList = new ArrayList<>();
		for (U v : values) {
			Literal<U> literal = new Literal<>(v);
			expressionList.add(literal);
		}
		return new Hammer<>(new OneOf<>(expressionList));
	}

	/**
	 * Returns a sequence of values
	 * @param expressions The values
	 * @return An object that can be passed to other methods or used for generation
	 */
    @SafeVarargs
	public static <U> Hammer<U> sequence(final Expression<U>... expressions) {
		return new Hammer<>(new Sequence<>(Arrays.asList(expressions)));
	}
	
	/**
	 * Returns a sequence of values
	 * @param values The values
	 * @return An object that can be passed to other methods or used for generation
	 */
    @SafeVarargs
	public static <U> Hammer<U> sequence(final U... values) {
		List<Expression<U>> expressionList = new ArrayList<>();
		for (U v : values) {
			Literal<U> literal = new Literal<>(v);
			expressionList.add(literal);
		}
		return new Hammer<>(new Sequence<>(expressionList));
	}

    /**
     * Returns a single literal value
     * @param value The value
     * @return An object that can be passed to other methods or used for generation
     */
    public static <U> Hammer<U> literal(final U value) {
        return new Hammer<>(new Literal<>(value));
    }

}
