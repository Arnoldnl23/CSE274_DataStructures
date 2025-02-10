import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * A resizable-array based stack that allows null items to be stored.
 * 
 * @author Noah Arnold
 *
 * @param <T> the type of items to store in the stack. 
 */

public class ArrayStack<T> implements Stack<T> {

	// This variable will keep track of the index where the top item
	// is located. We initialize top to -1 to indicate that the stack is
	// empty.
	private int topIndex;
	private T[] data;

	/**
	 * Constructs a new stack with underlying array of length 10
	 */
	@SuppressWarnings("unchecked")
    public ArrayStack() {
		topIndex = -1;
		data = (T[]) new Object[10];
	}

	/*
	 * Returns the stack elements, starting at the top
	 */
	public String toString() {
		String result = "";
		
		for (int i = topIndex; i >= 0; i--) {
			result += data[i] + " ";
		}
		
		return result.trim();
	}

    @Override
    public T push(T item) {
        if ( topIndex + 1 == data.length ) {
           data = Arrays.copyOf(data, data.length * 2);
        }
        data[topIndex + 1] = item;
        topIndex++;
        return item;
    }

    @Override
    public T pop() {
        T popped = peek();
        data[topIndex] = null;
        topIndex--;
        return popped;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public void clear() {
        for ( int i = 0; i <= topIndex; i++) {
            data[i] = null;
        }
        topIndex = -1;
    }
}
