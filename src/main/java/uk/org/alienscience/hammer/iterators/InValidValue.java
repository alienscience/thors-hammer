package uk.org.alienscience.hammer.iterators;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.Generator;
import uk.org.alienscience.hammer.Sampler;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An iterator that produces a value that is valid according to a grammar
 */
public class InValidValue<T> implements Iterable<T> {

    private final Expression<T> expression;
    private final Sampler sampler;

    private InValidValue(Expression<T> expression, Sampler sampler) {
        this.expression = expression;
        this.sampler = sampler;
    }

    @Override
    public Iterator<T> iterator() {
        return new InValidValueIterator();
    }

    public static <U> Iterable<U> generate(Expression<U> expression, Sampler sampler) {
        return new InValidValue(expression, sampler);
    }

    private class InValidValueIterator implements Iterator<T> {

        private final ArrayList<Generator<T>> generators;
        private final Iterator<Generator<T>> generatorIterator;
        private final double probabilityInvalid;

        int count;
        boolean atLeastOneInvalid;

        InValidValueIterator() {

            // Flatten the expression into a list of generators
            GeneratorList<T> generatorList = new GeneratorList<>(expression, sampler);
            this.generators = generatorList.getGenerators();

            // Extract an iterator from the generator list
            generatorIterator = generators.iterator();

            // Calculate the probability of swapping a valid value to an invalid one
            probabilityInvalid = calculateInvalidProbability();
            count = 0;
            atLeastOneInvalid = false;
        }

        private double calculateInvalidProbability() {
            // Aim for an average of 2 invalid values and calculate the probability of swapping to
            // an invalid value
            int size = generators.size();
            if (size < 2) return 1.0;
            return 2.0 / (double) generators.size();
        }

        @Override
        public boolean hasNext() {
            return generatorIterator.hasNext();
        }

        @Override
        public T next() {
            // Consider the next generator
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
