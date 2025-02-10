/**
 * An interface for the ADT queue.
 * 
 * @author Norm Krumpe
 * @param <T> the object type
 */
public interface Queue<T> {
	/**
	 * Adds a new entry to the back of this queue.
	 * 
	 * @param newEntry An object to be added.
	 */
	public void add(T newItem);

	/**
	 * Removes and returns the entry at the front of this queue.
	 * 
	 * @return The object at the front of the queue.
	 * @throws NoSuchElementException if queue is empty before the operation.
	 */
	public T remove();

	/**
	 * Retrieves the entry at the front of this queue.
	 * 
	 * @return The object at the front of the queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public T peek();

	/**
	 * Detects whether this queue is empty.
	 * 
	 * @return true if queue is empty, or false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Removes all entries from this queue.
	 */
	public void clear();
}
