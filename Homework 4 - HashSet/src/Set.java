/**
 * Defines expected methods for any set of strings.
 */
public interface Set {	
	/**
	 * Adds the specified string to this set if it does not already
	 * exist
	 * @param s the string to be added
	 * @return true if the string is successfully added, and false otherwise
	 */
	public boolean add(String s);
	
	/**
	 * Removes the specified string to this set if exists
	 * @param s the string to be removed
	 * @return true if the string is successfully removed, and false otherwise
	 */
	public boolean remove(String s);
	
	/**
	 * Returns whether the specified string is in this set
	 * @param s the string to be looked for
	 * @return true if the string is found, and false otherwise
	 */
	public boolean contains(String s);
	
	/**
	 * Removes and returns an item from this set if this set is not empty
	 * @return the item removed, or null if this set is empty
	 */
	public String remove();
	
	/**
	 * Returns the size of this set
	 * @return the size of this set
	 */
	public int size();
	
	/**
	 * Returns whether this set is empty
	 * @return true if this set is empty and false otherwise
	 */
	public boolean isEmpty();
	
	/**
	 * Removes all items from this set
	 */
	public void clear();
	
	/**
	 * Returns an array containing all the items from this set
	 * @return an array of all the items from this set
	 */
	public String[] toArray();
}
