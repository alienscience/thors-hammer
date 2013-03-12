package uk.org.alienscience.hammer;

/**
 * Visits a datastructure containing selection and generation nodes
 * TODO rename to ExpressionVisitor
 */
public interface ExpressionVisitor<T> {
    public void visit(Selector<T> selector);
    public void visit(Generator<T> generator);
}
