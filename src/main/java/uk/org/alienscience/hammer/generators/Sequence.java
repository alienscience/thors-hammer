package uk.org.alienscience.hammer.generators;

import uk.org.alienscience.hammer.Expression;
import uk.org.alienscience.hammer.Selector;

import java.util.Iterator;
import java.util.List;

public class Sequence<T> extends Selector<T> {

	List<Expression<T>> expressions;

	public Sequence(List<Expression<T>> expressions) {
		this.expressions = expressions;
	}
	
	@Override
	public int size() {
		return (expressions.isEmpty()) ? 0 : 1;
	}

	@Override
	public Iterable<Expression<T>> select(int index) {
		return new SequenceIterator();
	}

	private class SequenceIterator implements Iterable<Expression<T>> {
	
		int i = 0;
		
		@Override
		public Iterator<Expression<T>> iterator() {
			return new Iterator<Expression<T>>(){

				@Override
				public boolean hasNext() {
					return i < expressions.size();
				}

				@Override
				public Expression<T> next() {
					return expressions.get(i++);
				}

				@Override
				public void remove() { 
					throw new UnsupportedOperationException();
				}
			};
		}
		
	}
}
