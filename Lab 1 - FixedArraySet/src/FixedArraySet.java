import java.util.Arrays;

/**
 * Implements a set of Strings using a fixed-length array
 * @author Noah Arnold
 */
public class FixedArraySet {

    //DON'T create more instance variables
    private String[] data;
    private int size;
    
    public static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Constructs a fixed array Set with specified capacity
     * @param capacity the maximum number of 
     * items this set can contain
     */
    public FixedArraySet(int capacity) { 
        data = new String[capacity];
        size = 0;
    }
    
    /**
     * Constructs a set with an underlying
     * array with a default capacity
     */
    public FixedArraySet() {
        data = new String[DEFAULT_CAPACITY];
        size = 0;
    }
    
    /**
     * Adds a specified string to the fixed array Set,
     * provided it's not already in the set,
     * and the set has room to add it
     * @param s the string to be put into the Set
     * @return true if string is added Set correctly, false otherwise
     */
    public boolean add(String s) {
        
        //If it's already in the set, return false
        if ( indexOf(s) != -1 || size >= data.length) {
            return false;
        }
        
        data[size] = s;
        size++;
        return true;
    }
    
    /**
     * Checks whether a specific string is in the fixed array Set
     * @param s the string to be checked in the Set
     * @return true if string is in the Set, false otherwise 
     */
    public boolean contains(String s) {
       
       if (indexOf(s) == -1 ) {
           return false;
       }
       
       return true;
    }
    
    /**
     * Removes the requested string from the fixed array Set
     * @param s the string to be removed from the Set
     * @return true if the string was removed from the Set,
     * false otherwise
     */
    public boolean remove(String s) {
        int location = indexOf(s);
        
        if (location != -1) {
            data[location] = data[size - 1];
            data[size - 1] = null;
            size--;
            return true;
        }
        
        return false;
    }
    
    /**
     * Removes the last string from the fixed array Set
     * @return the string that was removed from the set,
     * null if there was no string removed
     */
    public String remove() {
        if (size == 0) {
            return null;
        }
        
        String removed = data[size - 1];
        data[size - 1] = null;
        size--;
        return removed;
    }
    
    /**
     * Sets all values in the Set to null
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }
    
    /**
     * Returns the size of the Set
     * @return the size of the Set
     */
    public int size() {
        return size;
    }
    
    /**
     * Return whether the set is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Creates an array with the elements of the Set in order
     * @return the array created by the method, with a length
     * equal to the size of the Set
     */
    public String[] toArray() {
        String[] setArray = Arrays.copyOf(data, size);
        return setArray;
    }
    
    /**
     * Returns a space-delimited string
     * containing all the items in the set.
     * @return a string equivalent to the Set,
     * with spaces between each value
     */
    public String toString() {
        String result = "";
        
        for ( int i = 0; i < size; i++) {
            result = result + data[i] + " ";
        }
        
        return result;
        
    }
    
    /**
     * Returns the location of the specified string,
     * or -1 if it's not there.
     * This method will be used by add(s), remove(s),
     * and contains(s)
     * @return the integer value for the location of the string,
     * or -1 if the string is not in the Set
     */
    private int indexOf(String s) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(s)) {
                return i;
            }
        }
        
        //If I made it to here, I haven't found s.
        return -1;
    }
}
