import java.util.NoSuchElementException;

/**
 * Implements a queue using a circular array.
 * 
 * @author Noah Arnold
 * @param <T> the type of object
 */

public class CircularArrayQueue<T> implements Queue<T> {

	private T[] data;
	private int frontIndex;
	private int size;

	// WE WILL NOT CREATE A backIndex variable. Rather, whenever we need to
	// know the back index, we will compute it using the information we already
	// have. DO NOT CREATE ANY ADDITIONAL INSTANCE VARIABLES

	// Constructs an empty queue with an underlying array of length 10
	@SuppressWarnings("unchecked")
	public CircularArrayQueue() {
		data = (T[]) new Object[10];
		frontIndex = 0;
		size = 0;
	}

	// adds to back of queue
	@Override
	public void add(T newItem) {
		// Resize if the underlying array is full.
		// Use the algorithm from class. At the end of resizing,
		// the front of the queue should be at index 0.
	    if (size == data.length) {
	        //Resize 
	        @SuppressWarnings("unchecked")
            T[] newData = (T[]) new Object[ data.length * 2 ];
	        for ( int i = 0; i < size; i++) {
	            newData[i] = data[ (i + frontIndex) % data.length];
	        }
	        data = newData;
	        frontIndex = 0;
	    }
	    data[ (frontIndex + size) % data.length ] = newItem;
	    size++;
	}

	// removes from front of queue
	@Override
	public T remove() {
		// Make use of the peek method
		T result = peek();

		data[frontIndex] = null;
		
		size--;
		frontIndex = ( frontIndex + 1 ) % data.length;
		return result;
	}

	// peeks front of queue
	@Override
	public T peek() {
		if (isEmpty()) {
		    throw new NoSuchElementException();
		}
		
		return data[frontIndex];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		// Once you have "nulled" the correct indexes,
		// it doesn't really matter what you set frontIndex to.
		// You can leave it where it is, or you can set it to 0.
		// It won't have any impact on how your queue works.
	    for ( int i = 0; i < size; i++) {
	        data[ (i + frontIndex) % data.length] = null;
	    }
	    size = 0;
	    frontIndex = 0;
	}

	// Returns a string containing the queue from front to back,
	// formatted as follows (assuming a queue of Integers):
	// front [3 4 7] back
	// and an empty queue would look as follows:
	// front [] back
	@Override
	public String toString() {
		String result = "front [";

		for ( int i = 0; i < size; i++) {
		    result = result + data[ (i + frontIndex) % data.length ] + " ";
		}

		return result.trim() + "] back";
	}

}
