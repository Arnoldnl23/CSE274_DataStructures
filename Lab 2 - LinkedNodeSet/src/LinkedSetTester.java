import java.util.Arrays;

public class LinkedSetTester {

    public static void main(String[] args) {
        LinkedSet s = new LinkedSet();
        // Tests add(s) 
        s.add("a");
        System.out.println("True? " + s.add("b")); // true
        System.out.println("False? " + s.add("a")); // false
        s.add("c");
        s.add("d");
        s.add("e");
        // Tests toString(), size(), and isEmpty()
        System.out.println(s.toString());  // "e d c b a"
        System.out.println("Size equals 5? " + s.size()); // 5
        System.out.println("Is empty? False: " + s.isEmpty());
        
        // Tests contains(s)
        System.out.println("True? " + s.contains("c")); //true
        System.out.println("False? " + s.contains("z"));
        
        // A basic remove(s) test 
        System.out.println("True? " + s.remove("b")); // true
        System.out.println(s.toString()); // "d c e a"
        System.out.println("False? " + s.remove("z"));
        
        // A basic remove() test 
        System.out.println("d? " + s.remove()); // "d"
        System.out.println(s.toString()); // "c e a"

        // Tests toArray()
        System.out.println(Arrays.toString(s.toArray()));
        
        // Tests clear() and empty array on all other methods
        s.clear();
        System.out.println("Should be empty: " + s.toString());
        System.out.println("Null? " + s.remove());
        System.out.println("False? " + s.remove("a"));
        System.out.println("False? " + s.contains("a"));
        System.out.println("Empty? " + Arrays.toString(s.toArray()));
        
        //Constructs linkedSet from array
        String[] strings = {"race", "car", "backwards", "is", "racecar"};
        LinkedSet s2 = new LinkedSet(strings);
        System.out.println("Full set? " + s2.toString());
        
        LinkedSet s3 = new LinkedSet();
        for (int i = 1; i <= 10; i++) {
            s3.add(Integer.toString(2 * i));
        }

        s3.remove();
        s3.remove("6");
        System.out.println("\n" + s3.toString());
    }

}
