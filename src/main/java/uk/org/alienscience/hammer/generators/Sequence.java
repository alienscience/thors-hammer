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
		return new SequenceIterator<T>(nodes.get(index));
	}

	private static class SequenceIterator<T> implements Iterable<Node<T>> {

		public SequenceIterator(Node<T> node) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Iterator<Node<T>> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
