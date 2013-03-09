package uk.org.alienscience;

import uk.org.alienscience.hammer.Node;
import uk.org.alienscience.hammer.generators.Literal;
import uk.org.alienscience.hammer.generators.Repeat;

/**
 *  Thors Hammer
 *
 */
public class Hammer {
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
}
