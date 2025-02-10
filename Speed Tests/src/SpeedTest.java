import java.util.*;
import java.io.*;

/*
 * Demonstrates the performance of various data structures
 * on text analysis. Counting words in a file, and counting
 * unique words in a file.
 */

public class SpeedTest {

    public static void main(String[] args) throws FileNotFoundException {
        //time stats for counting unique words in a file w/n words
        //ArrayList: O(n^2) on average
        //LinkedList: O(n^2) on average
        //HashSet: O(n) on average
        
        Scanner in = new Scanner(new File("warandpeace.txt"));
        Set words = new HashSet();
        
        long time = System.nanoTime();
        //loop through all words in the file
        while ( in.hasNext() ) {
          String s = in.next();
          
          if (!words.contains(s)) {
              words.add(s);              
          } 
        }
        
        time = System.nanoTime() - time; //gives elapsed time in billionths of a second
        
        System.out.println(time / 1E9);
        System.out.println(words.size());
        in.close();
    }

}
