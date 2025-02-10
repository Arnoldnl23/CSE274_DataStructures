import java.util.Arrays;

/**
 * An implementation of Set of strings that uses a resizable array. Basically, when the
 * underlying array is full, and an add is attempted, a new array will be
 * created that has twice the capacity of the old array.
 * 
 * @author Norm Krumpe
 *
 */

public class ResizableArraySet implements Set {

	private String[] strings;
	private int size;
	private static final int DEFAULT_CAPACITY = 5;

	/**
	 * Constructs an empty set with the specified capacity.
	 * 
	 * @param capacity the initial capacity of the set
	 */
	public ResizableArraySet(int capacity) {
		strings = new String[capacity];
		size = 0;
	}

	/**
	 * Constructs an empty set with the default capacity.
	 */
	public ResizableArraySet() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs a set containing the Strings in the given array. The initial
	 * capacity of the set will be equal to the length of the specified array (even
	 * if it contains duplicates)
	 * 
	 * @param StringsNew an array of Strings to be added
	 */
	public ResizableArraySet(String[] StringsNew) {
		this(StringsNew.length);

		for (String s : StringsNew) {
			add(s);
		}
	}

	/**
	 * Adds the specified String to this set.
	 * 
	 * @param pt the String to be added
	 * @return true if the add was successful, and false otherwise
	 */
	public boolean add(String pt) {
		if (contains(pt)) {
			return false;
		}

		checkCapacity();
		strings[size++] = pt;
		return true;
	}

	/**
	 * Returns the length of the underlying array. (Ordinarily, a method like this
	 * wouldn't exist in a class like this. But it's here to make it easier for you
	 * and your instructor to check if your implementation is working correctly.
	 * 
	 * @return the length of the underlying array
	 */
	public int getCapacity() {
		return strings.length;
	}

	/*
	 * If the array is full, switch to a new array with twice as many elements.
	 */
	private void checkCapacity() {
		if (size == strings.length) {
			strings = Arrays.copyOf(strings, 2 * strings.length);
		}
	}

	/*
	 * A useful helper method for any method that needs to know the location of a
	 * specified String.
	 */
	private int indexOf(String pt) {
		for (int i = 0; i < size; i++) {
			if (pt.equals(strings[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Determines whether the specified String is in this set.
	 * 
	 * @param pt the String whose presence is to be tested
	 * @return true if the String is in this set, and false otherwise
	 */
	public boolean contains(String pt) {
		return indexOf(pt) != -1;
	}

	/**
	 * Removes the specified String from this set.
	 * 
	 * @param pt the String to be removed
	 * @return true if the remove was successful, and false otherwise
	 */
	public boolean remove(String pt) {
		int loc = indexOf(pt);
		if (loc == -1) {
			return false;
		}

		strings[loc] = strings[size - 1];
		strings[size - 1] = null;
		size--;
		return true;
	}

	/**
	 * Returns the number of elements in this set.
	 * 
	 * @return the number of elements in this set
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns true if this set contains no elements, and false otherwise.
	 * 
	 * @return true if this set contains no elements, and false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all Strings from this set.
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			strings[i] = null;
		}
		size = 0;
	}

	/**
	 * Removes one unspecified entry from this set, if possible.
	 * 
	 * @return either the removed entry, if the removal was successful, or null.
	 */
	public String remove() {
		if (size == 0) {
			return null;
		} else {
			String result = strings[size - 1];
			strings[size - 1] = null;
			size--;
			return result;
		}
	}

	/**
	 * Returns an array containing all of the Strings in this set. If the set
	 * contains no elements, an empty array is returned.
	 * 
	 * @return an array of all the elements in this set
	 */
	public String[] toArray() {
		return Arrays.copyOf(strings, size);
	}

	/**
	 * Returns a space-separated, list of Strings in this set 
	 * If the set is empty, return the String "EMPTY". 
	 */
	public String toString() {
		if (size == 0) return "EMPTY";
		String result = "";
		for (int i = 0; i < size; i++) {
			result += strings[i] + " ";
		}
		return result.trim();
	}
}