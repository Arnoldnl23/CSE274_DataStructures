import java.awt.Point;
import java.util.*;

public class InterfaceTester {

    public static void main(String[] args) {
        
        /*
         * Java has a Set interface, and several implementations of
         * that interface including HashSet and TreeSet.
         * We should learn to set up our own data structures that way.
         * The part in angle brackets <> is generics, which we will learn later.
         */
//        Set<String> s = new TreeSet<>();
//        s.add("a");
//        s.add("b");
//        s.add("a");
//        System.out.println(s);
//        System.out.println(s.size());
        
        /*
         * For this to work
         * 1. Create an interface called Set
         * 2. 
         */
//        Set s = new ResizableArraySet();
//        s.add("a");
//        s.add("b");
//        s.add("a");
//        System.out.println(s);
//        System.out.println(s.size());
        
        String s = "ZAP";
        System.out.println(s.hashCode());
    }

}
