package uk.org.alienscience.hammer.iterators;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.Generator;
import uk.org.alienscience.hammer.Sampler;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An iterator that produces a value that is valid according to a grammar
 */
public class ValidValue<T> implements Iterable<T> {

    private final Expression<T> expression;
    private final Sampler sampler;

    private ValidValue(Expression<T> expression, Sampler sampler) {
        this.expression = expression;
        this.sampler = sampler;
    }

    @Override
    public Iterator<T> iterator() {
        return new ValidValueIterator();
    }

    public static <U> Iterable<U> generate(Expression<U> expression, Sampler sampler) {
        return new ValidValue<>(expression, sampler);
    }

    private class ValidValueIterator implements Iterator<T> {

        private final ArrayList<Generator<T>> generators;
        private final Iterator<Generator<T>> generatorIterator;

        ValidValueIterator() {

            // Flatten the expression into a list of generators
            GeneratorList<T> generatorList = new GeneratorList<>(expression, sampler);
            this.generators = generatorList.getGenerators();

            // Extract an iterator from the generator list
            generatorIterator = generators.iterator();
        }

        @Override
        public boolean hasNext() {
            return generatorIterator.hasNext();
        }

        @Override
        public T next() {
            // Consider the next generator
            Generator<T> generator = generatorIterator.next();

            // Choose a value to generate
            int i = sampler.sample1D(generator.size());
            return generator.getValid(i);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
