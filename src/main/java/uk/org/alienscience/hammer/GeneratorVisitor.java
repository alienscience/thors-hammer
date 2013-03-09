package uk.org.alienscience.hammer;

/**
 * Visits a datastructure containing selection and generation nodes
 */
public interface GeneratorVisitor<T> {
    public void visit(Selector<T> selector);
    public void visit(Generator<T> generator);
}
