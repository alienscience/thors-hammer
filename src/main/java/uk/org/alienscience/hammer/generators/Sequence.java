package uk.org.alienscience.hammer.generators;

import java.util.Iterator;
import java.util.List;

import uk.org.alienscience.hammer.Node;
import uk.org.alienscience.hammer.Selector;

public class Sequence<T> extends Selector<T> {

	List<Node<T>> nodes;

	public Sequence(List<Node<T>> nodes) {
		this.nodes = nodes;
	}
	
	@Override
	public int size() {
		return (nodes.isEmpty()) ? 0 : 1;
	}

	@Override
	public Iterable<Node<T>> select(int index) {
		return new SequenceIterator();
	}

	private class SequenceIterator implements Iterable<Node<T>> {
	
		int i = 0;
		
		@Override
		public Iterator<Node<T>> iterator() {
			return new Iterator<Node<T>>(){

				@Override
				public boolean hasNext() {
					return i < nodes.size();
				}

				@Override
				public Node<T> next() {
					return nodes.get(i++);
				}

				@Override
				public void remove() { 
					throw new UnsupportedOperationException();
				}
			};
		}
		
	}
}
