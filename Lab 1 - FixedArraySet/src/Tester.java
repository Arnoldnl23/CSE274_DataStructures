// Tests my FixedArraySet implementation
// Noah Arnold

import java.util.Arrays;

public class Tester {

    public static void main(String[] args) {

        int CAPACITY = 6;
        FixedArraySet s = new FixedArraySet(CAPACITY);

        System.out.println("size = 0? " + s.size());
        System.out.println("isEmpty() = true? " + s.isEmpty());
        
        System.out.println("Basic adding: ");
        System.out.println("true? " + s.add("A"));
        System.out.println("true? " + s.add("B"));
        System.out.println("true? " + s.add("C"));
        
        System.out.println("Does the set contain A B C? " + s);

        System.out.println("\nAdding duplicates: ");
        System.out.println("false? " + s.add("A"));
        System.out.println("false? " + s.add("B"));
        System.out.println("false? " + s.add("C"));

      System.out.println("\nTesting contains: ");
      System.out.println("true? " + s.contains("A"));
      System.out.println("true? " + s.contains("B"));
      System.out.println("true? " + s.contains("C"));
      System.out.println("false? " + s.contains("D"));

      System.out.println("size = 3? " + s.size());
      System.out.println("isEmpty() = false? " + s.isEmpty());
      
      System.out.println("\nreach the capacity of the set: ");
      int i = 0;
      while (s.size() < CAPACITY) {
          System.out.println("true? " +s.add("item" + i));
          System.out.println("true? " +s.contains("item" + i));
          i++;
      }

      System.out.println("\nsize = 6? " + s.size());
      System.out.println("isEmpty() = false? " + s.isEmpty());

      // EDGE CASE!!!
      System.out.println("Add one more item: ");
      System.out.println("false? " + s.add("TEST"));

      System.out.println("\nTesting remove(String)");

      // Try removing the last item:
      System.out.println("true? " +s.remove("item" + (i - 1)));

      // Try removing the first item:
      System.out.println("true? " +s.remove("A"));

      System.out.println("Here is the set: ");
      System.out.println(s);

      System.out.println("\nTesting remove()");
      System.out.println("item0? " +s.remove());
      System.out.println("C? " +s.remove());

      System.out.println("Here is the set: ");
      System.out.println("item1 B? " + s);

      System.out.println("\nTesting remove() again");
      System.out.println("B? " + s.remove());
      System.out.println("item1? " +s.remove());

      System.out.println("Here is the set: (should be empty)");
      System.out.println(s);

      System.out.println("\nTesting remove with an empty set: null? " + s.remove());

      System.out.println("\nAdd three animals: cat dog mouse");
      s.add("cat");
      s.add("dog");
      s.add("mouse");

      System.out.println("Get the array: ");
      System.out.print("cat dog mouse? ");
      String[] animals = s.toArray();

      for (String animal : animals) {
          System.out.print(animal + " ");
      }
      System.out.println();

      System.out.println("\nClear the set again: ");
      s.clear();
      System.out.println("Get the array: (should be empty)");
      animals = s.toArray();
      
      System.out.println("If you see nulls or animals here, you've made a mistake...");
      for (String animal : animals) {
          System.out.print(animal + " ");
      }
      
      System.out.println("*** Test array with default capacity of 10 and adding 11 items: ");
      s = new FixedArraySet();
      
      // Try adding 11 items:
      for (i = 1; i <= 11; i++) {
          s.add("" + i);
      }
      
      System.out.println("Should see the numbers 1 through 10: " + s);
      System.out.println("Should see a size of 10: " + s.size());

        
        
    }

}
