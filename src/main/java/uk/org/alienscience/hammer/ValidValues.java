package uk.org.alienscience.hammer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An iterator that produces values that are valid according to a grammar
 */
public class ValidValues<T> implements Iterable<T> {

    private final Expression<T> expression;
    private final Sampler sampler;

    public ValidValues(Expression<T> expression, Sampler sampler) {
        this.expression = expression;
        this.sampler = sampler;
    }

    @Override
    public Iterator<T> iterator() {
        return new ValidValuesIterator();
    }

    private class ValidValuesIterator implements Iterator<T>, ExpressionVisitor<T> {

        private final ArrayList<Generator<T>> generators;
        private final Iterator<Generator<T>> generatorIterator;

        ValidValuesIterator() {

            // Flatten the expression into a list of generators
            generators = new ArrayList<>();
            expression.applyVisitor(this);

            // Extract an iterator from the generator list
            generatorIterator = generators.iterator();
        }

        @Override
        public void visit(Selector<T> selector) {
            // Sample a valid selector value
            int i = sampler.sample1D(selector.size());

            // Iterate through the expressions with this index
            for (Expression<T> e : selector.select(i)) {
                e.applyVisitor(this);
            }
        }

        @Override
        public void visit(Generator<T> generator) {
            generators.add(generator);
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
            return generator.get(i);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
