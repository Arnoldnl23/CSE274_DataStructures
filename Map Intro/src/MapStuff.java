import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

import java.awt.Point;
/*
 * maps are all about key-value pairs.
 * The keys are always unique (they form a set)
 * Two common implementations are HashMap and TreeMap.
 * HashMap benefits: O(1) performance for put,get,containsKey
 * TreeMap benefits: O(log n) performance, but sorts by key 
 */
public class MapStuff {

    public static void main(String[] args) {
        //nameAgeMap();
        HashMap<String, String> map = new HashMap<>();
        letterWordsMap();
       
    }
    
    public static void letterWordsMap() {
        //Key: a letter (Character)
        //the value is a set of words starting with that letter
        //a --> {apple, air, amy}
        //g --> {good}
        //y --> {yellow, yak}
        
        Map<Character, Set<String>> map = new TreeMap<>();
        Set<String> aWords = new TreeSet<>();
        aWords.add("apple");
        aWords.add("air");
        
        map.put('a', aWords);
        map.get('a').add("amy");
        
        String[] words = {"amy", "big", "apples", "tree", "auto"};
        for ( String s : words) {
            if (s.charAt(0) == 'a' ) {
                map.get('a').add(s);
            }
        }
        
        System.out.println(map);
    }

    public static void nameAgeMap() {
        //Example: put names and ages in a map
        //Amy, 22
        //Mike, 19
        //Tom, 19
        //Sarah, 30
        //key: Name (string)
        //value: age (integer)
        Map<String, Integer> map = new HashMap<>();
        
        map.put("Amy", 22);
        map.put("Mike", 19);
        map.put("Tom", 19);
        map.put("Sarah", 30);
        map.put("Tom", map.get("Tom") + 1);
        //keySet returns a set containing the keys of the map
        Set<String> names = map.keySet();
        for ( String n : names ) {
            map.put(n, map.get(n) + 10);
        }
        
        
        
        System.out.println(map);
        System.out.println(map.size());
        System.out.println(map.containsKey("Tom"));
    }
}
