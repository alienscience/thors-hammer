package uk.org.alienscience.hammer.iterators;

import uk.org.alienscience.hammer.*;

import java.util.ArrayList;

/**
 * A class that visits an expression and creates a flat list of generators
 */
class GeneratorList<T> implements ExpressionVisitor<T> {

    private final ArrayList<Generator<T>> generators;
    private final Sampler sampler;

    public GeneratorList(Expression<T> expression, Sampler sampler) {
        this.sampler = sampler;

        // Flatten the expression into a list of generators
        this.generators = new ArrayList<>();
        expression.applyVisitor(this);
    }

    /**
     * Get the flattened list of generators
     * @return The flattened list of generators
     */
    public ArrayList<Generator<T>> getGenerators() {
        return generators;
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

}
