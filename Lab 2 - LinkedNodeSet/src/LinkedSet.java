/**
 * A linked-node implementation of Set, with a private inner Node class. for
 * storing a set of String objects.
 * 
 * @author Noah Arnold
 */

public class LinkedSet {
    
    // We need exactly two instance variables
    int size;
    Node head;
    
    /*
     * Constructs an empty set.
     */
    public LinkedSet() {
        size = 0;
        head = null;
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
        LinkedSet linkedSet = new LinkedSet();
        
        for ( int i = strings.length - 1; i >= 0; i-- ) {
            linkedSet.add(strings[i]);
        }
        
        head = linkedSet.head;
        size = strings.length;
    }

    // Returns true if the item was added, and false otherwise.
    // Should use the private helper method find(), and have no other loops
    public boolean add(String s) {
        if ( contains(s) ) {
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
        
        if ( curr == null ) {
           return false; 
        }
        
        curr.data = this.head.data;
        head = head.next;
        size--;
        return true;
    }

    // Returns the removed String, or null if this set is empty
    // There should be no loop.
    public String remove() {
        if ( this.head == null ) {
            return null;
        }
        
        String temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    // Returns true if the item is in this set, and false otherwise.
    // Should use the private helper method find(), and have no other loops
    public boolean contains(String s) {
        Node current = this.head;
        
        while ( current != null ) {
            if ( current.data.equals(s) ) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }

    /*
     * Returns the node containing the specified String, or null if it's not in the
     * set. Very useful for add(s) and remove(s) and contains(s).
     * (this is very much like the private indexOf() method we wrote for ArraySet).
     * add(s) and contains(s) care IF the string is in the set.
     * remove(s) cares WHERE the string is located, so it can remove that node. 
     */
    private Node find(String s) {
        Node current = this.head;
        
        while ( current != null ) {
            if ( current.data.equals(s) ) {
                return current;
            }
            current = current.next;
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
        this.head = null;
        size = 0;
    }

    /*
     * Returns a space-separated trimmed list of the items in this set, in
     * the order that the data appears in the underlying chain of nodes, beginning
     * with the head node's data. If the set is empty, return "EMPTY"
     */
    public String toString() {
        if ( this.head == null ) {
            return "EMPTY";
        }
        
        Node current = this.head;
        String result = "";
        while ( current != null ) {
            result = result + current.data + " ";
            current = current.next;
        }
        
        return result.trim();
    }
    
    /*
     * Returns an array containing the data in this set, in
     * the order that the data appears in the underlying chain of nodes, beginning
     * with the head node's data. If the set is empty return an array with length 0.
     */
    public String[] toArray() {
        String[] result = new String[this.size];
        
        if ( this.size == 0) {
            return result = new String[0];
        }
        
        Node current = this.head;
        for ( int i = 0; current != null; i++ ) {
            result[i] = current.data;
            current = current.next;
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