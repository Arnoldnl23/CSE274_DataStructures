import java.util.EmptyStackException;

/**
 * A linked-node based stack that allows null items to be stored.
 * 
 * @author Noah Arnold
 *
 * @param <T> the type of items to store in the stack. Any object type is valid.
 */

public class LinkedStack<T> implements Stack<T> {

	private Node topNode;

	/**
	 * Constructs a new stack
	 */
	public LinkedStack() {
	    topNode = null;
	}

	/*
	 * Returns the stack elements, starting at the top
	 */
	public String toString() {
		String result = "";
		Node start = topNode;
		while (start != null) {
			result += start.data + " ";
			start = start.next;
		}
		return result.trim();
	}

	private class Node {
		private T data;
		private Node next;

		private Node(T data) {
			this(data, null);
		}

		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

    @Override
    public T push(T item) {
        if ( topNode == null ) {
            topNode = new Node(item);
        }
        else {
            Node temp = new Node(item);
            temp.next = topNode;
            topNode = temp;
        }
        return item;
    }

    @Override
    public T pop() {
        T popped = peek();
        topNode = topNode.next;
        return popped;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topNode.data;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;;
    }
}
