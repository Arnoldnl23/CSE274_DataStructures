import java.util.Arrays;

// A priority queue of integers, implemented with a resizable array minheap
// Since this is a min heap, the item with the highest priority will be the
// smallest integer

// In order to keep parent-to-child formulas pretty, we will leave index
// 0 empty in the array.
public class Heap {
	public Integer[] data;
	public int size;
	public static final int DEFAULT_ARRAY_LENGTH = 10;

	/**
	 * Constructs an empty priority queue with specified array length. Remember that
	 * index 0 will be unused, so the minimum number of items that can be stored in
	 * the array will be 1 less than the length of the array. Example: new Heap(3)
	 * will create the array [null, null, null], which has the capacity to store two
	 * items.
	 */
	public Heap(int startingLength) {
	    data = new Integer[startingLength];
	    size = 0;
	}

	/**
	 * Constructs an empty priority queue with default array length. Remember that
	 * index 0 will be unused, so the minimum number of items that can be stored in
	 * the array will be 1 less than the length of the array.
	 */
	public Heap() {
		this(DEFAULT_ARRAY_LENGTH);
	}

	/**
	 * Constructs a priority queue from an array of Integer values. The initial
	 * capacity will be the number of Integers in the array.
	 * 
	 * @param ary an array of Integer values that will be put in the priority queue.
	 *            The Integers can be in any order, and this constructor will
	 *            arrange them to form a priority queue.
	 */

	// This constructor doesn't need any changes, but won't work
	// until convertToMinHeap() is implemented
	public Heap(Integer[] ary) {
		// one larger than ary to account for empty spot at 0
		this(ary.length + 1);

		for (int i = 0; i < ary.length; i++) {
			// everything gets put in the new array 1 location higher,
			// so that index 0 is left empty
			data[i + 1] = ary[i];
		}
		size = ary.length;

		convertToMinHeap();
	}

	/**
	 * Adds a value to this heap. Recall that the algorithm we are using is to place
	 * the item after the last node, and then swap the new item upward until the
	 * item is in place.
	 * 
	 * @param n value to be added
	 */
	public void add(int n) {
		checkCapacity(); // first resize if necessary
		size++;
	
		int i = size;
		while ( i > 1 ) {
		    if ( data[i/2] > n ) {
		        data[i] = data[i/2];
		        i = i/2;
		    }
		    else {
		        break;
		    }
		}
		
		data[i] = n;
	}

	/**
	 * Removes and returns the smallest integer in this priority queue. Recall that
	 * the algorithm for fixing the heap is to put the last item of the heap into
	 * index 1, and then swap that item downward until it is in place.
	 * 
	 * @return the lowest integer in this priority queue, or null if empty
	 */
	// This is implemented, and needs no changes, but it calls fixHeapDown() which
	// is not yet implemented.
	public Integer remove() {
		if (isEmpty())
			return null;

		// grabs the item to be returned
		// replaces the item at the root with
		// the last item, sets that last position to null
		// and decreases the size
		Integer result = data[1];		
		data[1] = data[size];
		data[size--] = null;

		// fixes the heap starting at position 1 and working
		// downward.
		fixHeapDown(1);

		return result;
	}

	/**
	 * Returns true if this priority queue is empty, and false otherwise
	 * 
	 * @return true if this priority queue is empty, and false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Gets the size of this priority queue: the number of elements in it
	 * 
	 * @return the size of this priority queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Gets the value of the smallest Integer in this priority queue
	 * 
	 * @return the smallest value in this priority queue, or null if empty
	 */
	public Integer peek() {
		return data[1];
	}

	/**
	 * Fixes a heap downward, beginning at the specified node. 
	 * The algorithm is: if either child node is smaller than the parent node,
	 * then swap the parent node downward with the smaller child value. Repeat from
	 * the new location of that value that was moved down. This is a loop (or could
	 * probably be done recursively).
	 * 
	 * @param i the index where the downward fix should begin
	 */
	// This method will be called once by the remove() method, and called many
	// times in a loop by the convertToMinHeap() method.
	public void fixHeapDown(int i) {
		// TODO #4 Implement fixHeapDown(). This is where you will need to do your most careful
		// thinking.
		// TODO #5 After fixHeapDown() is implemented, test remove() to verify that the
		// heap is fixed after each remove
	    int place = i;
	    while ( place * 2 < size ) {
	        int lowIndex = place * 2;
	        if ( data[(place * 2) + 1] != null && data[place * 2] > data[(place * 2) + 1]) {
	            lowIndex++;
	        }
	        if ( data[place] > data[lowIndex]) {
	            int temp = data[lowIndex];
	            data[lowIndex] = data[place];
	            data[place] = temp;
	        }
	        place = lowIndex;
	    }
	    
	}

	/**
	 * Converts the array to a minheap. This is needed for the
	 * constructor that takes in an array of Integers. The strategy used here should
	 * be the second strategy discussed in the notes and demos: Start at the BOTTOM
	 * of the heap, and work backward until you encounter the first node that is NOT
	 * a leaf. Perform a fixheapDown starting at that node. Continue working
	 * backward, all the way to the root. In other words, this method should call
	 * fixHeapDown roughly size/2 times. This only requires roughly 3 lines of code,
	 * because it can (and should) make use of another helper method.
	 * 
	 * Do you know which node will be the first one that needs fixing? It's whatever
	 * node is the parent of the very last node in the heap.
	 * 
	 */
	public void convertToMinHeap() {
		for ( int i = size/2 ; i > 0; i--) {
		    fixHeapDown(i);
		}
	}
	
	/**
	 * Checks to see if the array is full and, if so, doubles its length.
	 */
	public void checkCapacity() {
		if (size == data.length - 1) {
			data = Arrays.copyOf(data, 2 * data.length);
		}
	}

	// Not useful to the client, but useful to you for testing
	@Override
	public String toString() {
		return Arrays.toString(data) + ", size=" + size;
	}

}
