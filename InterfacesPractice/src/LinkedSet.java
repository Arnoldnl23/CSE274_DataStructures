/**
 * A linked-node implementation of Set, with a private inner Node class. for
 * storing a set of String objects.
 * 
 * @author Norm Krumpe
 */

public class LinkedSet implements Set{
	
	// We need exactly two instance variables
	private Node head;
	private int size;

	/*
	 * Constructs an empty set.
	 */
	public LinkedSet() {
		head = null;
		size = 0;
	}
	
	/*
	 * Constructs a set from the array of specified strings.
	 * WAIT UNTIL YOU HAVE WRITTEN AND TESTED THE add() METHOD, AND THEN USE
	 * THAT METHOD TO IMPLEMENT THIS CONSTRUCTOR.
	 * Even though the client doesn't care about order, the order we will use
	 * is: if the array is {a, b, c, d, e, e, f} then the resulting set's data
	 * starting at the head will be: a b c d e f
	 * This can be written in 4 lines of code:
	 * 1 line calls on the parent constructor
	 * 3 lines loop to add the items from the array
	 */
	public LinkedSet(String[] strings) {
		this();
		for (int i = strings.length - 1; i >= 0; i--) {
			this.add(strings[i]);
		}
	}

	// Returns true if the item was added, and false otherwise.
	// Should use the private helper method find(), and have no other loops
	public boolean add(String s) {
		if (find(s) != null) {
			return false;
		}
		
		Node temp = new Node(s);
		temp.next = head;
		head = temp;
		size++;
		
		return true;
	}

	// Returns true if the item was removed, and false otherwise.
	// Example, if data starting at head is: a b c d e f
	// Then, remove("d") would change the data to: b c a e f
	// Should use the private helper method find(), and have no other loops
	public boolean remove(String s) {
		Node curr = find(s);
		if (curr == null) {
			return false;
		}
		
		curr.data = head.data;
		head = head.next;
		size--;
		return true;
	}

	// Returns the removed String, or null if this set is empty
	// There should be no loop.
	public String remove() {
		if (head == null) {
			return null;
		}
		
		String result = head.data;
		head = head.next;
		size--;
		
		return result;
	}

	// Returns true if the item is in this set, and false otherwise.
	// Should use the private helper method find(), and have no other loops
	public boolean contains(String s) {
		return find(s) != null;
	}

	/*
	 * Returns the node containing the specified String, or null if it's not in the
	 * set. Very useful for add(s) and remove(s) and contains(s).
	 * (this is very much like the private indexOf() method we wrote for ArraySet).
	 * add(s) and contains(s) care IF the string is in the set.
	 * remove(s) cares WHERE the string is located, so it can remove that node. 
	 */
	private Node find(String s) {
		Node curr = head;
		while (curr != null) {
			if (curr.data.equals(s)) {
				return curr;
			}
			curr = curr.next;
		}
		
		
		return null;
	}

	// Returns the size of this set
	public int size() {
		return size;
	}

	// Returns true if and only if this set is empty
	public boolean isEmpty() {
		return size == 0;
	}

	// Empties the set. This should not have a loop (unlike ArraySet)
	public void clear() {
		head = null;
		size = 0;
	}

	/*
	 * Returns a space-separated trimmed list of the items in this set, in
	 * the order that the data appears in the underlying chain of nodes, beginning
	 * with the head node's data. If the set is empty, return "EMPTY"
	 */
	public String toString() {		
		if (head == null) {
			return "EMPTY";
		}
		
		String result = "";
		Node curr = head;
		
		while (curr != null) {
			result += curr.data + " ";
			curr = curr.next;
		}		
		
		return result.trim();
	}
	
	/*
	 * Returns an array containing the data in this set, in
	 * the order that the data appears in the underlying chain of nodes, beginning
	 * with the head node's data. If the set is empty return an array with length 0.
	 */
	public String[] toArray() {
		if (head == null) {
			return new String[0];
		}
		
		String[] result = new String[size];
		Node curr = head;
		int index = 0;
		
		while (curr != null) {
			result[index++] = curr.data;
			curr = curr.next;
		}		
		
		return result;
	}

	// Typical inner node class.
	// The outer class is LinkedSet. The inner class is Node.
	// We make it private so that client code can't access nodes directly.
	// Even though the inner class is private, the outer class can access it.
	private class Node {
		private String data;
		private Node next;
		
		private Node(String data) {
			this.data = data;
			this.next = null;
		}

	}

}