package uk.org.alienscience.hammer.iterators;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.Generator;
import uk.org.alienscience.hammer.Sampler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * An iterator that produces a value that is invalid according to a grammar
 */
public class InvalidValue<T> implements Iterable<T> {

    private final Expression<T> expression;
    private final Sampler sampler;

    private InvalidValue(Expression<T> expression, Sampler sampler) {
        this.expression = expression;
        this.sampler = sampler;
    }

    @Override
    public Iterator<T> iterator() {
        return new InValidValueIterator();
    }

    public static <U> Iterable<U> generate(Expression<U> expression, Sampler sampler) {
        return new InvalidValue(expression, sampler);
    }

    private class InValidValueIterator implements Iterator<T> {

        private final ArrayList<Expression<T>> expressions;
        private final ArrayList<Generator<T>> generators;

        private Iterator<Generator<T>> generatorIterator;
        
        private int mutateIndex;

        InValidValueIterator() {

            // Flatten the expression into a list of generators
            ExpressionList<T> expressionList = new ExpressionList<>(expression, sampler);
            this.expressions = expressionList.getExpressions();

            // The generators list is created later
            this.generators =  null;
            this.generatorIterator = null;
            
            // Get the index of the expression to mutate
            mutateIndex = expressionIndexToMutate();
           
            // Make note of all the generators leading up to this expression
            
            // Mutate the expression
           
            // Collect all the generators following the expression
        }

        private int expressionIndexToMutate() {
        	Random random = new Random();
        	return random.nextInt(expressions.size());
        }

        @Override
        public boolean hasNext() {
            return generatorIterator.hasNext();
        }

        @Override
        public T next() {
            // Consider the next expression
            Generator<T> generator = generatorIterator.next();
            count++;

            // Choose a value to generate
            int i = sampler.sample1D(generator.size());

            // Valid or invalid? At least one value should be invalid.
            if (Math.random() < probabilityInvalid ||
                    (count == generators.size() && atLeastOneInvalid == false) ) {

                T invalidValue = generator.getInvalid(i);
                T validValue = generator.getValid(i);
                atLeastOneInvalid = true;

                System.err.println("Swapping " + validValue + " for " + invalidValue);
                return invalidValue;
            }

            return generator.getValid(i);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
