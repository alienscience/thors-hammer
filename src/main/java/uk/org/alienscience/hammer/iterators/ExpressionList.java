package uk.org.alienscience.hammer.iterators;

import uk.org.alienscience.hammer.*;

import java.util.ArrayList;

/**
 * A class that visits an expression and creates a flat list of generators
 */
class ExpressionList<T> implements ExpressionVisitor<T> {

    private final ArrayList<Expression<T>> flattened;
    private final Sampler sampler;

    public ExpressionList(Expression<T> expression, Sampler sampler) {
        this.sampler = sampler;

        // Flatten the expression 
        this.flattened = new ArrayList<>();
        expression.applyVisitor(this);
    }

    /**
     * Get the flattened list of generators
     * @return The flattened list of generators
     */
    public ArrayList<Expression<T>> getExpressions() {
        return flattened;
    }

    @Override
    public void visit(Selector<T> selector) {
    	// Make note of the selector
        flattened.add(selector);
		        
        // Sample a valid selector value
        int i = sampler.sample1D(selector.size());

        // Iterate through the expressions with this index
        for (Expression<T> e : selector.select(i)) {
            e.applyVisitor(this);
        }
    }

    @Override
    public void visit(Generator<T> generator) {
        flattened.add(generator);
    }

}
