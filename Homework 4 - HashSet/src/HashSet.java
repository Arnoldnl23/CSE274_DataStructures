/**
 * Implements a set of strings using hashing, ideally giving O(1) performance
 * for add, remove, and contains. We say "ideally" because efficiency depends on
 * keeping the number of collisions low.
 * 
 * Implementation details: initial array length = 11 (prime) Load factor is 0.6,
 * which will be checked before doing the add When resizing, resize to the
 * smallest prime number greater than or equal to 2 * the current array length.
 * 
 * @author Norm Krumpe
 *
 */

/*
 * Lab: Make THIS_TEST_MUST_PASS pass + both constructors + toDeluxeString()
 * Homework: Get all methods and constructors working (even the ones not tested here).
 */
public class HashSet implements Set {

	// instance variables: we need just two. One is the array of buckets
	// and the other is the size of the set.
    private Node[] buckets;
    private int size;

	// We will also have some useful constants.
    public static final int DEFAULT_LENGTH = 11; //needs to be prime
    public static final double MAX_LOAD_FACTOR = 0.6;

	/**
	 * Constructs a HashSet with an initial array length of 11
	 */
	public HashSet() { 
		this(DEFAULT_LENGTH);
	}

	// throw exception if capacity is not prime
	public HashSet(int capacity) { 
		if ( !isPrime(capacity) ) {
		    throw new IllegalArgumentException("Non-prime capacity: " + capacity);
		}
		
        this.buckets = new Node[capacity]; //all Nodes are set to null
        this.size = 0;
	}

	@Override
	public boolean add(String s) {
		// For the lab, get add working but don't worry
		// about resizing. For homework, you will get the
		// resizing working.
		// 1. figure out the index.
        int index = getTargetIndex(s);
		// 2. call find() to see if the item is at that index TODO
        if ( find(s,buckets[index]) != null ) {
            return false; //there is a duplicate
        }
        
        checkCapacity();
        index = getTargetIndex(s);
		// 3. if not, add it to the beginning of the chain
        Node temp = new Node(s);
        temp.next = buckets[index];
        buckets[index] = temp;
        size++;
        
        //4. TODO We will also need to resize here if load capacity is exceeded
		return true;
	}

	@Override
	public boolean remove(String s) {
		// 1. figure out the index.
	    int location = getTargetIndex(s);
		// 2. call find() to see if the item is at that index
	    Node goal = find(s,buckets[location]);
	    if ( goal == null ) {
	        return false;
	    }		
	    // 3. if it is, remove that item using the STANDARD remove
		//    algorithm we used with linked nodes. DON'T CALL find()
		//    again and DON'T write another loop. (because you captured
		//    the result of find() in a variable, right?)
	    
	    goal.data = buckets[location].data;
	    buckets[location] = buckets[location].next;
	    size--;
	    
		return true;
	}

	/**
	 * Returns an arbitrary string from the set, if the set is not empty. Otherwise,
	 * returns null; Our algorithm: find the first non-empty bucket and return the
	 * data from the first node in that bucket.
	 * 
	 * @return the removed string, or null if the set is empty
	 */
	@Override
	public String remove() {
	    if ( isEmpty() ) {
	        return null;
	    }
	    
	    for ( int i = 0; i < buckets.length; i++) {
            if ( buckets[i] != null ) {
                String removed = buckets[i].data;
                buckets[i] = buckets[i].next;
                size--;
                return removed;
            }
        }
	    
	    return null;
	}

	@Override
	public void clear() {
	    this.buckets = new Node[buckets.length];
	    size = 0;
	}

	@Override
	public boolean contains(String s) {
		// this should use the find() helper method.
		// 1. figure out the index.
	    int index = getTargetIndex(s);
		// 2. call find() sending s and the appropriate node
	    
		Node temp = find(s, buckets[index]);
		if ( temp == null ) {
		    return false;
		}
		
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
		    return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the current load, which is the size
	 * of the set divided by the length of the underlying
	 * array of buckets
	 * @return the size divided by the length of the underlying
	 * array of buckets
	 */
	public double getCurrentLoad() {
	    if ( isEmpty() ) {
	        return 0;
	    }
	    
	    double dSize = size;
	    double dLength = buckets.length;
		return dSize / dLength;
	}
	
	/**
	 * Returns a string containing all the strings in this set. This string will
	 * contain all buckets, even the empty ones, and within each bucket will be a
	 * space-separated list of the strings in that bucket. Here is a sample showing
	 * the format that you must use (the strings are not necessarily shown in their
	 * correct buckets in this example) 
	 * [0] cat house 
	 * [1] 
	 * [2] car 
	 * [3] tree mouse dog
	 * [4] 
	 * [5] 
	 * [6] apple 
	 * and so on
	 * 
	 * @return a string containing all the strings in the set
	 */
	public String toStringDeluxe() {
	    String result = "";
	    
	    //loop through each bucket
	    for ( int i = 0; i < buckets.length; i++) {
	        result += "[" + i + "] ";
	        //loop through chain of nodes in bucket
            Node curr = buckets[i];
	        while ( curr != null ) {
	            result += curr.data + " ";
	            curr = curr.next;
	        }  
	        result += "\n";
	    }
	    
		return result.trim();
	}

	/**
	 * Returns a space-delimited, trimmed string of the items in the set. The order
	 * will begin by looping through the strings in the first non-empty bucket, and
	 * then the next non-empty bucket, and so on. If the set is empty, return an empty 
	 * string ""
	 */
	public String toString() {	
	    if ( isEmpty() ) {
	        return "";
	    }
		String result = "";
		
        for ( int i = 0; i < buckets.length; i++) {
            //loop through chain of nodes in bucket
            Node curr = buckets[i];
            while ( curr != null ) {
                result += curr.data + " ";
                curr = curr.next;
            }  
        }
        
        return result.trim();
	}
	
	/**
	 * Returns an array containing all the strings in this set. Our algorithm will
	 * be to return them beginning with the strings in the first non-empty bucket,
	 * followed by the next non-empty bucket, and so on. Within each bucket, return
	 * strings beginning with the head node. The order of the strings will be the
	 * same as the order of the strings in toString(). An empty set should return
	 * an array of length 0.
	 * 
	 * @return an array containing all the strings in the set
	 */
	@Override
	public String[] toArray() {
		String[] result = new String[size];
		
		int arrayIndex = 0;
		for ( int i = 0; i < buckets.length; i++) {
            //loop through chain of nodes in bucket
            Node curr = buckets[i];
            while ( curr != null ) {
                result[arrayIndex] = curr.data;
                arrayIndex++;
                curr = curr.next;
            }  
        }
		
		return result;
	}


	/*************************************************************
	 addAll(), union(), intersection(), and equalSets() are NOT
	 tested in the JUnit tester. It is up to you to test these
	 methods on your own. 
	 ************************************************************/
	
	/**
	 * Adds all strings in the specified array to the set
	 * 
	 * @param s the strings to be added
	 * @return true if at least one item is added to the set, and false otherwise
	 */
	public boolean addAll(String[] strings) {
	    int initialSize = this.size();
	    for ( String s : strings ) {
	        this.add(s);
	    }
	    return this.size != initialSize;
	}
	
	/**
	 * Returns the intersection of this HashSet with another set. Try to write this
	 * in the fewest lines of code you can by using other methods you wrote in this
	 * class. Do not use any other collection classes. But you may use arrays
	 * freely. Do not modify this set or the given set.
	 * 
	 * @param other the other HashSet
	 * @return all words common to both this HashSet and the other HashSet
	 */
	public HashSet intersection(HashSet other) {
		HashSet newSet = new HashSet();
		for ( String s : this.toArray() ) {
		    if ( other.contains(s) ) {
		        newSet.add(s);
		    }
		}
		
		return newSet;
	}

	/**
	 * Returns the union of this HashSet with another set. Try to write this in the
	 * fewest lines of code you can by using other methods you wrote in this class.
	 * Do not use any other collection classes. But you may use arrays freely. Do
	 * not modify this set or the given set.
	 * 
	 * @param other the other HashSet
	 * @return all words in either this HashSet or the other HashSet or both
	 */
	public HashSet union(HashSet other) {
		HashSet newSet = new HashSet();
		for ( String s : this.toArray() ) {
		    newSet.add(s);
		}
		for ( String s : other.toArray() ) {
		    newSet.add(s);
		}
		
		return newSet;
	}
	
	/**
	 * Returns true if this HashSet has exactly the same elements as another set,
	 * regardless of order.
	 * (can you write this in two lines of code? or even one?)
	 * @param other the other HashSet
	 * @return true if this HashSet and the other HashSet have the exact same
	 * elements, regardless of order
	 */
	public boolean equalSets(HashSet other) {
	    if ( this.size == 0 || other.size == 0) {
	        if ( this.size == 0 && other.size == 0) {
	            return true;
	        }
	        return false;
	    }
		for ( String s : this.toArray() ) {
		    if ( !other.contains(s) ) {
		        return false;
		    }
		}
		return true;
	}

	////////////////////////////////////////////
	// helper methods
	////////////////////////////////////////////
	/*
	 * Checks if the capacity exceeds MAX_LOAD_FACTOR. If so, then modifies this set
	 * by moving the items in the array to a new array whose size is the first prime
	 * number > length of the current array
	 */
	private void checkCapacity() {
	    if ( getCurrentLoad() > .6 ) {
		// One solution that you could consider if you need to resize
		// 1. Create a new HashSet that has the desired capacity.
	        HashSet other = new HashSet(nextPrime(buckets.length * 2));
		// 2. Add all the elements from this set to the new set.
	        String[] originalArray = this.toArray();
	        for ( String s : originalArray) {
	            other.add(s);
	        }
		// 3. Now the new set has exactly the array that this set wishes it had,
		//    so just "transplant" that new array into this set (which is one
		//    line of code)	     
	        this.buckets = other.buckets;
	    }

	}

	// Returns the node containing a particular string, or null if not found.
	// Useful for add, remove, and contains.
	private Node find(String s, Node start) {
	    if (start == null) {
	        return null;
	    }
	    
	    Node curr = start;
	    while (curr != null) {
	        if (curr.data.equals(s)) {
	            return curr;
	        }
	        curr = curr.next;
	    }
	    
		return null;
	}

	// Converts a string into the index where that string belongs
	private int getTargetIndex(String s) {
		int index = s.hashCode() % buckets.length;
		if (index < 0) {
		    index += buckets.length;
		}
		return index;
	}

	/*
	 * Returns whether the specified value is prime
	 */
	public static boolean isPrime(int n) {
		if (n == 2)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}

		return true;
	}

	/*
	 * Get the next prime number greater than the specified value.
	 */
	public static int nextPrime(int n) {
		n++;
		if (n == 2)
			return 2;

		if (n % 2 == 0)
			n++;

		while (!isPrime(n)) {
			n += 2;
		}

		return n;
	}

	// Inner node class
	private class Node {
		private String data;
		private Node next;
		
		private Node(String data) {
		    this.data = data;
		    this.next = null;
		}

		private Node(String data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

}
