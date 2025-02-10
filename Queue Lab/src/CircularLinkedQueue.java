import java.util.NoSuchElementException;

/**
 * A circular-linked-node implementation of queue, with only a reference to the
 * node that is at the end of the queue.
 * 
 * @author Noah Arnold
 * @param <T> the type of item in the queue.
 */
public class CircularLinkedQueue<T> implements Queue<T> {

	// This should be your only instance variable
	private Node last;

	public CircularLinkedQueue() {
		last = null;
	}

	// When adding and removing nodes, consider:
	// * the general case
	// * the two edge cases:
	// what if there are no nodes? should the code be different?
	// what if there is only one node? should the code be different?

	@Override
	public void add(T newItem) {
		Node temp = new Node(newItem);
		//handles edge case when there are no nodes
		if (isEmpty()) {
		    last = temp;
		    last.next = last;
		}
		//handles general case including when queue has one item
		else {
		    temp.next = last.next;
		    last.next = temp;
		    last = last.next;
		}
	}

	@Override
	public T remove() {
		// Make use of the peek method
		T result = peek();

		if ( last == last.next ) {
		    clear();
		}
		else {
		    last.next = last.next.next;
		}
		return result;
	}

	@Override
	public T peek() {
	    if (isEmpty()) {
	        throw new NoSuchElementException();
	    }
		return last.next.data;
	}

	@Override
	public boolean isEmpty() {
		return last == null;
	}

	@Override
	public void clear() {
		// Just need one line of code here
	    last = null;
	}

	// Returns a string containing the queue from front to back,
	// formatted as follows (assuming a queue of Integers):
	// front [3 4 7] back
	// and an empty queue would look as follows:
	// front [] back
	@Override
	public String toString() {
		if (isEmpty()) {
			return "front [] back";
		}

		String result = "front [";
		
		Node curr = last.next;
		do {
		    result = result + curr.data + " ";
		    curr = curr.next;
		} while ( curr != last.next );


		return result.trim() + "] back";
	}

	private class Node {
		private T data;
		private Node next;

		private Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

}
