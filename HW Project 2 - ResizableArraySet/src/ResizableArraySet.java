import java.awt.Point;
import java.util.Arrays;

/**
 * An implementation of Set that uses a resizable array.
 * When the underlying array is full, and an add 
 * is attempted, a new array will be created that has 
 * twice the capacity of the old array.
 * @author Noah Arnold
 */
public class ResizableArraySet {

    //Initial Variables
    private Point[] data;
    private int size;
    
    public static final int DEFAULT_CAPACITY = 5;
    
    //Constructors
    /**
     * Constructs an empty set with the default capacity.
     */
    public ResizableArraySet() {
        data = new Point[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructs an empty set with the specified capacity.
     * @param capacity: the initial capacity of the set
     */
    public ResizableArraySet(int capacity) {
        data = new Point[capacity];
        size = 0;
    }
    
    /**
     * Constructs a set containing the points in the given array. 
     * The initial capacity of the set will be equal to the 
     * length of the specified array (even if it contains duplicates)
     * @param pointsNew: an array of points to be added
     */
    public ResizableArraySet(Point[] pointsNew) {
       data = new Point[pointsNew.length];
       size = 0;
       
       for ( Point p : pointsNew ) {
           this.add(p);
       }
    }
    
    //Public Methods
    /**
     * Adds the specified point to this set
     * @param pt: the point to be added 
     * @return true if the add was successful,
     * and false otherwise
     */
    public boolean add(Point pt) {
        if ( indexOf(pt) != -1 ) {
            return false;
        }
        
        checkCapacity();
        
        data[size] = pt;
        size++;
        return true;
    }
    
    /**
     * Computes and returns the set of all that can be 
     * obtained by adding each point in this set to each 
     * of the other points in this set (but not adding a point to itself)
     * @return a set with all possible sums of a point with the other points in this set
     */
    public ResizableArraySet allSums() {
        ResizableArraySet allSums = new ResizableArraySet(2);
        
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++) {
                if ( !( i == j) ) {
                    Point newPoint = new Point( data[i].x + data[j].x , data[i].y + data[j].y);
                    if ( indexOf(newPoint) == -1 ) {
                        allSums.add(newPoint);
                    }
                }
            }
        }
        
        return allSums;
    }
    
    /**
     * Removes all points from this set.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }
    
    /**
     * Determines whether the specified point is in this set.
     * @param pt: the point whose presence is to be tested
     * @return true if the point is in this set, 
     * and false otherwise
     */
    public boolean contains(Point pt) {
        if (indexOf(pt) == -1 ) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Returns the length of the underlying array
     * @return the length of the underlying array
     */
    public int getCapacity() {
        return data.length;
    }
    
    /**
     * Computes the intersection of this set and another set 
     * (items common to both sets), and returns the results 
     * as a new set. Order is unimportant.
     * @param other: another set
     * @return a set with all elements that are common to both sets.
     */
    public ResizableArraySet intersection(ResizableArraySet other) {
        ResizableArraySet inter = new ResizableArraySet(1);
        
        for ( Point i : other.toArray() ) {
            if ( this.indexOf(i) != -1 && other.indexOf(i) != -1 && inter.indexOf(i) == -1) {
                inter.add(i);
            }
        }
        
        return inter;
    }
    
    /**
     * Returns true if this set contains no elements, and false otherwise.
     * @return true if this set contains no elements, 
     * and false otherwise.
     */
    public boolean isEmpty () {
        return size == 0;
    }
    
    /**
     * Removes one unspecified entry from this set, if possible.
     * @return either the removed entry, if the removal was successful, 
     * or null.
     */
    public Point remove() {
        if (size == 0) {
            return null;
        }
        
        Point removed = data[size - 1];
        data[size - 1] = null;
        size--;
        return removed;
    }
    
    /**
     * Removes the specified point from this set.
     * @param pt: the point to be removed
     * @return true if the remove was successful, 
     * and false otherwise
     */
    public boolean remove(Point pt) {
        int location = indexOf(pt);
        
        if (location != -1) {
            data[location] = data[size - 1];
            data[size - 1] = null;
            size--;
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns the number of elements in this set.
     * @return the number of elements in this set
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns an array containing all of the points in this set. 
     * If the set contains no elements, an empty array is returned.
     * @return an array of all the elements in this set
     */
    public Point[] toArray() {
        Point[] setArray = Arrays.copyOf(data, size);
        return setArray;
    }
    
    /**
     * Returns a space-separated, list of points in the 
     * form (x, y) (no decimal points in the output).
     * If the set is empty, return the point "EMPTY"
     */
    public String toString() {
        String result = "";
        
        if ( size == 0) {
            return "EMPTY";
        }
        
        for ( int i = 0; i < size; i++) {
            result = result + "(" + data[i].x + ", " + data[i].y + ") ";
        }
        
        return result;
    }
    
    /**
     * Computes the union of this set and another set 
     * (items in either or both sets), and returns the 
     * results as a new set. Order is unimportant.
     * @param other: another set
     * @return a set with all elements that are in either or both sets.
     */
    public ResizableArraySet union(ResizableArraySet other) {
        ResizableArraySet union = new ResizableArraySet(1);
        
        for ( Point i : this.toArray() ) {
            if ( union.indexOf(i) == -1) {
                union.add(i);
            }
        }
        for ( Point i : other.toArray() ) {
            if ( union.indexOf(i) == -1) {
                union.add(i);
            }
        }
        
        return union;
    }
    
    /**
     * Returns a count of the number of points in this set 
     * whose distance from the origin (0, 0) is less than or 
     * equal to the specified radius.
     * @param radius: the radius to check
     * @return the number of points in this set whose distance 
     * from the origin is less than or equal to radius
     */
    public int withinRadius(double radius) {
        int radiusCount = 0;
        
        for ( int i = 0; i < size; i++) {
            if ( Math.sqrt(data[i].x^2 + data[i].y^2) <= radius ) {
                radiusCount++;
            }
        }
        
        return radiusCount;
    }
    
    //Private Methods
    /**
     * Returns the location of the specified string,
     * or -1 if it's not there.
     * This method will be used by add(pt), remove(pt),
     * and contains(pt)
     * @param pt: the Point value you are searching for
     * @return the integer value for the location of the Point,
     * or -1 if the string is not in the Set
     */
    private int indexOf(Point pt) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(pt)) {
                return i;
            }
        }
        
        //If I made it to here, I haven't found s.
        return -1;
    }
    
    /**
     * Checks if resizing is necessary, and, if needed,
     * doubles the length of the array and copies the old 
     * array into the new array
     */
    private void checkCapacity() {
        if ( this.size() == this.getCapacity() ) {
            data = Arrays.copyOf(data, this.getCapacity() * 2);
        }
    }
}
