import java.util.NoSuchElementException;
import java.util.EmptyStackException;

/**
 * An implementation of List that uses doubly linked nodes, with references to
 * the first and last nodes.
 * 
 * @param <T> the object type
 * @author Noah Arnold
 */
public class LinkedList<T> implements List<T>, Queue<T>, Deque<T>, Stack<T> {

	private Node first;
	private Node last;
	private int size;

	//List Methods
	
	@Override
	public void add(T item) {
	    add(size, item);
	}
	
	@Override
	public void add(int index, T item) {
	    if ( index < 0 || index > size ) {
	        throw new IndexOutOfBoundsException();
	    }	    
	    
	    Node temp = new Node(item);

	    if ( isEmpty() ) {
	        first = temp;
	        last = temp;
	        size++;
	        return;
	    }
	    
	    if ( index == size ) {
	        last.next = temp;
	        temp.prev = last;
	        last = temp;
	        size++;
	        return;
	    }
	    
	    if ( index == 0 ) {
	        temp.next = first;
	        first.prev = temp;
	        first = temp;
	        size++;
	        return;
	    }
	    
	    Node curr = first;
	    
	    for (int i = 1; i < index; i++) {
	        curr = curr.next;
	    }
	    
	    curr.next.prev = temp;
	    temp.prev = curr;
	    temp.next = curr.next;
	    curr.next = temp;
	    size++;
	}

	@Override
	public T remove(int index) {
        if ( index < 0 || index >= size  || isEmpty() ) {
            throw new IndexOutOfBoundsException();
        }   
        
        T data = null;
        
        if ( size == 1 ) {
            data = first.data;
            clear();
            return data;
        }
        
        if ( index == 0 ) {
            data = first.data;
            first = first.next;
            first.prev = null;
            size--;
            return data;
        }
        
        if ( index == size - 1) {
            data = last.data;
            last = last.prev;
            last.next = null;
            size--;
            return data;
        }
        
        Node curr = first.next;
        
        for (int i = 1; i < index; i++) {
            curr = curr.next;
        }
        
        data = curr.data;
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
        size--;
        return data;
	}

	@Override
	public boolean remove(T item) {
	    int location = indexOf(item);
	    
	    if ( location == -1 ) {
	        return false;
	    }
	    
	    remove(location);
	    return true;
	}

	@Override
	public void clear() {
	    first = null;
	    last = null;
	    size = 0;
	}

	@Override
	public T set(int index, T item) {
	    if ( index < 0 || index >= size ) {
            throw new IndexOutOfBoundsException();
        }
	    
	    T data = null;
	    
	    Node curr = first;
	    
	    for ( int i = 0; i < index; i++) {
	        curr = curr.next;
	    }
	    
	    data = curr.data;
	    curr.data = item;
	    return data;
	}

	@Override
	public T get(int index) {
	    if ( index < 0 || index >= size ) {
            throw new IndexOutOfBoundsException();
        }
	    
	    Node curr = first;
	    
	    for ( int i = 0; i < index; i++) {
	        curr = curr.next;
	    }
	    
	    return curr.data;
	}

	@Override
	public int indexOf(T item) {
		Node curr = first;
		int index = 0;
		
		while ( curr != null ) {
		    if (  curr.data.equals(item)) {
		        return index;
		    }
		    index++;
		    curr = curr.next;
		}
		
        return -1;
	}

	@Override
	public int lastIndexOf(T item) {
		Node curr = first;
		int index = 0;
		int result = -1;
		
		while ( curr != null ) {
		    if ( curr.data == item ) {
		        result = index;
		    }
		    index++;
		    curr = curr.next;
		}
		
		return result;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[this.size];
		
		if ( isEmpty() ) {
		    return result;
		}
		
		for ( int i = 0; i < size; i++) {
		    result[i] = get(i);
		}
		
		return result;
	}

	@Override
	public boolean contains(T item) {
		if ( indexOf(item) == -1 ) {
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
		return size == 0;
	}

/////// SANITY CHECK...
	/*
	 * Things can go wrong when working with nodes, especially when those nodes are
	 * doubly-linked. These two methods are a simple way to check if things are
	 * still connected the way they should be. Check both of these regularly. They
	 * will give you clues on where things are broken. And the debugger will help
	 * you sort out details as well.
	 */

	// toStringNext() builds the toString() by using the .next links.
	// This starts at the first node and moves forward. It should produce the EXACT
	// SAME string as toStringPrev(), but it relies on the .next links to build the
	// list. So, if something is broken, it could be a missing node or it could be
	// that a .next is missing or misdirected.
	public String toStringNext() {
		String result = "";
		Node curr = first;

		while (curr != null) {
			result += curr.data + " ";
			curr = curr.next;
		}

		return result.trim() + " (size=" + size + ")";
	}

	// toStringPrev() builds the toString() by using the .prev links.
	// This starts at the last node and moves backward. It should produce the EXACT
	// SAME string as toStringNext(), but it relies on the .prev links to build the
	// list. So, if something is broken, it could be a missing node or it could be
	// that a .prev is missing or misdirected.
	public String toStringPrev() {
		String result = "";
		Node curr = last;

		while (curr != null) {
			result = " " + curr.data + result;
			curr = curr.prev;
		}

		return result.trim() + " (size=" + size + ")";
	}

	// Don't change this.
	@Override
	public String toString() {
		return toStringNext();
	}

	// A doubly-linked node, where each node has a reference to both the next
	// node and the previous node.
	private class Node {
		private Node prev;
		private T data;
		private Node next;

		// Creates a new node with both its previous and next references set to null.
		private Node(T data) {
			this.prev = null;
			this.data = data;
			this.next = null;
		}

	}

	//Queue Methods
	
    @Override
    public T remove() {
        if ( isEmpty() ) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }

    @Override
    public T peek() {
        if ( isEmpty() ) {
            throw new NoSuchElementException();
        }
        
        return get(0);
    }

    //Deque Methods
    
    @Override
    public void addFront(T item) {
        add(0, item);
    }

    @Override
    public void addBack(T item) {
        add(size, item);
    }

    @Override
    public T removeFront() {
        if ( isEmpty() ) {
            throw new NoSuchElementException();
        }
        
        return remove(0);
    }

    @Override
    public T removeBack() {
        if ( isEmpty() ) {
            throw new NoSuchElementException();
        }
        
        return remove(size - 1);
    }

    @Override
    public T peekFront() {
        if ( isEmpty() ) {
            throw new NoSuchElementException();
        }
        
        return get(0);
    }

    @Override
    public T peekBack() {
        if ( isEmpty() ) {
            throw new NoSuchElementException();
        }
        
        return get(size - 1);
    }
    
    //Stack Methods

    @Override
    public T push(T item) {
        add(0, item);
        return item;
    }

    @Override
    public T pop() {
        return remove(0);
    }
}
