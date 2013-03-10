package uk.org.alienscience;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import uk.org.alienscience.hammer.Node;
import uk.org.alienscience.hammer.generators.Literal;
import uk.org.alienscience.hammer.generators.OneOf;
import uk.org.alienscience.hammer.generators.Repeat;
import uk.org.alienscience.hammer.generators.Sequence;

/**
 *  Thors Hammer
 *
 */
public class Hammer<T> {

	private final Node<T> node;
	
	// Create a Hammer from a node
	private Hammer(Node<T> node) {
		this.node = node;
	}

	public Iterable<T> generateValid() {
		return ValidValueIterator<T>(node, new HeuristicHybrid());
	}
	
	//------ Static helper methods -------------------------------------------
	
	/**
	 * Create a hammer with the given expression
	 * @param node The expression to used to generate test values
	 * @return An object that will generate test data
	 */
	public static <U> Hammer<U> create(Node<U> node) {
		return new Hammer<U>(node);
	}

	/**
	 * Create a hammer with the given expression
	 * @param node The expression to used to generate test values
	 * @return An object that will generate test data
	 */
	public static <U> Hammer<U> create(U value) {
		Literal<U> literal = new Literal<U>(value);
		return new Hammer<U>(literal);
	}
	
	/**
	 * Repeat the given value
	 * @param node The value to repeat
	 * @param minCount The minimum number of repeations
	 * @param maxCount The maximum number of repeations
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Node<U> repeat(Node<U> node, int minCount, int maxCount) {
		return new Repeat<U>(node, minCount, maxCount);
	}

	/**
	 * Repeat the given value
	 * @param value The value to repeat
	 * @param minCount The minimum number of repeations
	 * @param maxCount The maximum number of repeations
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Node<U> repeat(U value, int minCount, int maxCount) {
		Literal<U> literal = new Literal<U>(value);
		return new Repeat<U>(literal, minCount, maxCount);
	}

	/**
	 * Returns one of a selection of values
	 * @param nodes One of these values will be selected
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Node<U> oneOf(Node<U>... nodes) {
		List<Node<U>> nodeList = new ArrayList<Node<U>>();
		for (Node<U> n : nodes) {
			nodeList.add(n);
		}
		return new OneOf<U>(nodeList);
	}
	
	/**
	 * Returns one of a selection of values
	 * @param nodes One of these values will be selected
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Node<U> oneOf(U... values) {
		List<Node<U>> nodeList = new ArrayList<Node<U>>();
		for (U v : values) {
			Literal<U> literal = new Literal<U>(v);
			nodeList.add(literal);
		}
		return new OneOf<U>(nodeList);
	}

	/**
	 * Returns a sequence of values
	 * @param nodes The values
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Node<U> sequence(Node<U>...nodes) {
		// TODO check this 
		return new Sequence<U>(Arrays.asList(nodes));
	}
	
	/**
	 * Returns a sequence of values
	 * @param nodes The values
	 * @return An object that can be passed to other methods or used for generation
	 */
	public static <U> Node<U> sequence(U... values) {
		List<Node<U>> nodeList = new ArrayList<Node<U>>();
		for (U v : values) {
			Literal<U> literal = new Literal<U>(v);
			nodeList.add(literal);
		}
		return new Sequence<U>(nodeList);
	}
}
