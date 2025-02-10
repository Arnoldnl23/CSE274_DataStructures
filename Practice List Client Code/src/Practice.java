// practice using a couple implementations of List
// Noah Arnold

import java.util.*;

public class Practice {

    public static void main(String[] args) {
        //List is an interface
        List<String> myList;
        //ArrayList is a class that implements List
        myList = new ArrayList<>();
        
        myList.add("cat");
        myList.add("dog");
        myList.add("fish");
        myList.add("cat");
        
        System.out.println(myList);
        System.out.println(myList.size());
        System.out.println(myList.get(0));
        
        myList.remove("cat");
        System.out.println(myList);
    }

}
