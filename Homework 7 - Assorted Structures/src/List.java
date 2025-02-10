/**
 * An interface for the ADT list.
 * 
 * @param <T> the object type
 * @author Norm Krumpe
 */
public interface List<T> {
	/**
	 * Adds a new item to the end of this list.  
	 * @param item The object to be added
	 */
	public void add(T item);

	/**
	 * Adds a new item at a specified index. Items originally at and above the
	 * given position will end up with positions that are 1 higher. If the
	 * specified position is equal to the size of the list, then the item is added
	 * to the last location.
	 * 
	 * @param index The desired index of the item to be added
	 * @param item  The object to be added
	 * @throws IndexOutOfBoundsException if either index < 0 or index > size().
	 */
	public void add(int index, T item);

	/**
	 * Removes the item at a specified index. Entries originally above
	 * the given position will end up with positions that are 1 lower than their current positions.
	 * 
	 * @param index An integer that indicates the position of the item to be
	 *              removed.
	 * @return A reference to the removed item.
	 * @throws IndexOutOfBoundsException if either index < 0 or
	 *                                   index >= size().
	 */
	public T remove(int index);
	
	/**
	 * Removes the first occurrence of the specified item, if it exists
	 * @param item the item to be removed
	 * @return true if the item was found and removed, and false otherwise
	 */
	public boolean remove(T item);

	/** Removes all entries from this list. */
	public void clear();

	/**
	 * Replaces the item at a given position in this list.
	 * 
	 * @param index An integer that indicates the position of the item to be
	 *              replaced.
	 * @param item  The object that will replace the item at the position
	 *              givenPosition.
	 * @return The original item that was replaced.
	 * @throws IndexOutOfBoundsException if either index < 0 or
	 *                                   index >= size().
	 */
	public T set(int index, T item);

	/**
	 * Gets the item at a given position in this list.
	 * 
	 * @param index An integer that indicates the position of the desired item.
	 * @return A reference to the indicated item.
	 * @throws IndexOutOfBoundsException if either index < 0 or
	 *                                   index >= size().
	 */
	public T get(int index);
	
	/**
	 * Returns the index of the first location of the specified item, or -1 if
	 * the item is not found.
	 * @param item the item sought
	 * @return the smallest index at which the specified item appears, or -1 if
	 * the item is not in the list.
	 */
	public int indexOf(T item);
	
	/**
	 * Returns the index of the last location of the specified item, or -1 if
	 * the item is not found.
	 * @param item the item sought
	 * @return the largest index at which the specified item appears, or -1 if
	 * the item is not in the list.
	 */
	public int lastIndexOf(T item);

	/**
	 * Retrieves all entries that are in this list in the order in which they occur
	 * in the list.
	 * 
	 * @return An array of all the entries in the list. If the list
	 *         is empty, the returned array is empty.
	 */
	public Object[] toArray();

	/**
	 * Sees whether this list contains a given item.
	 * 
	 * @param item The object that is the desired item.
	 * @return true if the list contains the item, and false otherwise
	 */
	public boolean contains(T item);

	/**
	 * Gets the number of items in this list.
	 * 
	 * @return the number of items in this list.
	 */
	public int size();
	
	/**
	 * Sees whether this list is empty.
	 * 
	 * @return True if the list is empty, and false otherwise
	 */
	public boolean isEmpty();
} // end ListInterface
