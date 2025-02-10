/**
 * An interface for the ADT deque.
 * 
 * @param <T> the object type
 * @author Norm Krumpe
 */
public interface Deque<T> {

	/**
	 * Adds a new entry to the front of this dequeue.
	 * 
	 * @param item An object to be added.
	 */
	public void addFront(T item);

	/**
	 * Adds a new entry to the back of this dequeue.
	 * 
	 * @param item An object to be added.
	 */
	public void addBack(T item);

	/**
	 * Removes and returns the front entry of this dequeue.
	 * 
	 * @return The item at the front of the dequeue.
	 * @throws NoSuchElementException if the dequeue is empty before the operation.
	 */
	public T removeFront();

	/**
	 * Removes and returns the back entry of this dequeue.
	 * 
	 * @return The object at the back of the dequeue.
	 * @throws NoSuchElementException if the dequeue is empty before the operation.
	 */
	public T removeBack();

	/**
	 * Retrieves the front entry of this dequeue.
	 * 
	 * @return The item at the front of the dequeue.
	 * @throws NoSuchElementException if the dequeue is empty before the operation.
	 */
	public T peekFront();

	/**
	 * Retrieves the back entry of this dequeue.
	 * 
	 * @return The object at the back of the dequeue.
	 * @throws NoSuchElementException if the dequeue is empty before the operation.
	 */
	public T peekBack();

	/**
	 * Detects whether this dequeue is empty.
	 * 
	 * @return True if the queue is empty, or false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Removes all entries from this dequeue.
	 */
	public void clear();

}
