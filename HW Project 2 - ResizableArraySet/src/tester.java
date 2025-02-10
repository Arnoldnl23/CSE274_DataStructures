import java.awt.Point;
import java.util.Arrays;

public class tester {

    public static void main(String[] args) {
        ResizableArraySet s = new ResizableArraySet(3);
        System.out.println("Capacity of this newly created set: " + s.getCapacity());
        System.out.println("toString() of this new set: " + s);
        
        //add 3 new points:
        s.add(new Point(2,3));
        s.add(new Point(5,10));
        s.add(new Point(0,1));
        System.out.println("\ncapacity after adding 3 items: " + s.getCapacity());
        
        //4th point, but its a duplicate so nothing should happen
        s.add(new Point(5,10));
        System.out.println("capacity after adding a duplicate: " + s.getCapacity());
        
        //New point, resizing should happen
        s.add(new Point(7,12));
        System.out.println("capacity after adding a 4th item: " + s.getCapacity());
        System.out.println("Contains (0,1)? true: " + s.contains(new Point(0,1)));
        
        //Testing of various methods
        System.out.println("\ntoString(): " + s);
        System.out.println("toArray(): " + Arrays.toString(s.toArray()));
        System.out.println("allSums(): " + s.allSums().toString());
        System.out.println("withinRadius(): " + s.withinRadius(3));
        
        //Constructor 2 test
        ResizableArraySet s2 = new ResizableArraySet();
        s2.add(new Point(2,3));
        s2.add(new Point(4,6));
        s2.add(new Point(7,12));
        s2.add(new Point(6,9));
        
        //Testing of Union and Intersection methods NEED TO DO
        ResizableArraySet sUnion = s.union(s2);
        System.out.println("\nArray from union() method: " + Arrays.toString(sUnion.toArray()));
        ResizableArraySet sInter = s.intersection(s2);
        System.out.println("Array from intersection() method: " + Arrays.toString(sInter.toArray()));
        
        //Testing remove and clear methods
        System.out.println("\nRemoving an existing point: " + s.remove(new Point(2,3)));
        System.out.println("Array after removing a point: " + Arrays.toString(s.toArray()));
        System.out.println("Removing a non-existent point: " + s.remove(new Point(6,9)));
        System.out.println("Removing last point in array: " + s.remove());
        s.clear();
        System.out.println("Array after clearing the array: " + Arrays.toString(s.toArray()) + " this should be empty");

        //Constructor 3 test
        Point[] arr = {new Point(2,7), new Point(3,8), new Point(), new Point(2,7)};
        ResizableArraySet s3 = new ResizableArraySet(arr);
        
        System.out.println("\nHere is tests for the third constructor type:");
        System.out.println("Starting Array: " + Arrays.toString(arr));
        System.out.println("toString() for the set: " + s3);
        System.out.println("Capacity of the set: " + s3.getCapacity());
    }

}
