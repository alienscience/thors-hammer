package uk.org.alienscience.hammer;

/**
 * TODO: Document
 */
public interface GeneratorVisitor<T> {
    public void visit(Selector selector);
    public void visit(Generator<T> generator);
}
