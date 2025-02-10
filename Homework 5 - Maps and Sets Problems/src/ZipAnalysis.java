import java.util.*;
import java.io.*;

/*
 * Analysis of data from a large table. Data source:
 * https://data.opendatasoft.com/explore/dataset/georef-united-states-of-america-zc-point%40public
 */

public class ZipAnalysis {
	private static ArrayList<Location> locations = readLocations("zipcodes.tsv");

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("STARTING");
		PrintWriter output = new PrintWriter(new File("output.txt"));
		
		//System.out.println(getZips("Ohio"));
		
		// If you want to print a method's output to the console,
		// just call it inside a println() statement. For example,
		// System.out.println(getStates());
		// But for longer output, it may be more helpful to see
		// it printed in a file.
		
		// When uncommented, the output is put into an output file named output.txt
//		display(output, getZips("Massachusetts"));        
//		 display(output, countiesAbovePopulation("Indiana", 30000));   
//		 display(output, getStates()); 
//		
//		 display(output, statesAndAbbreviations());     
//		 display(output, zipCountByStateCode());     
//		 display(output, statesByFirstLetter());    
//		 display(output, populationByState());      
//		 display(output, populationByTimeZone());     
//
//		 display(output, largeZipsInEachState());    
//		 display(output, commonCountyNames());     
//		 display(output, countyCount("Indiana"));       
//		 display(output, countyCount());        
//		 display(output, highestPopulationsInEachSate());      

		output.close();
		System.out.println("DONE!");
	}
	
	// Don't modify this method. It reads the file into the ArrayList
	public static ArrayList<Location> readLocations(String fname) {
		locations = new ArrayList<>();
		Scanner input = null;
		try {
			input = new Scanner(new File(fname));
		} catch (FileNotFoundException e) {
			System.out.println("Error reading file");
			e.printStackTrace();
			System.exit(0);
		}
		input.nextLine(); // skip first line (headers)
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] toks = line.split("\t");
			Location loc = new Location(toks);
			locations.add(loc);
		}
		input.close();
		return locations;
	}

	// Don't modify this method. It assists with displaying results.
	public static <K, V> void display(PrintWriter output, Map<K, V> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		for (K key : items.keySet()) {
			output.print(key + "---------->");
			display(output, items.get(key));
		}
	}

	// Don't modify this method. It assists with displaying results.
	public static void display(PrintWriter output, Object item) {
		if (item == null) {
			output.println("null");
			return;
		}
		output.println(item);
	}

	// Don't modify this method. It assists with displaying results.
	public static <T> void display(PrintWriter output, Collection<T> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		int LEN = 80;
		String line = "[";
		for (T item : items) {
			line += item.toString() + ",";
			if (line.length() > LEN) {
				output.println(line);
				line = "";
			}
		}
		output.println(line + "]");
	}

	/*
	 * THE SAMPLE OUTPUTS SHOWN BELOW ARE FOR THE DISTRIBUTED FILE. GETTING THESE
	 * CORRECT DOES NOT NECESSARILY IMPLY THAT YOUR CODE WILL WORK FOR NEW LOCATION
	 * DATA. YOU SHOULD BE TESTING YOUR CODE THOROUGHLY. YOU CAN CONSIDER CREATING A
	 * NEW DATA FILE FOR TESTING PURPOSES.
	 */

	/*
	 * Returns a set containing the zip codes for a specified state. 
	 * getZips("Ohio") -> [43001,43002,43003,...
	 */
	public static Set<String> getZips(String state) {
		Set<String> result  = new TreeSet<>();
		
		for ( Location loc : locations) {
		    if ( loc.state.equals(state) ) {
		        result.add(loc.zip);
		    }
		}
		
		return result;
	}

	/*
	 * Returns a set containing the counties in a specified state that have a
	 * population greater than the specified population
	 * getCountiesAbovePopulation("Georgia", 20000) -> [Baldwin,Barrow,Bartow,...
	 */
	public static Set<String> countiesAbovePopulation(String state, int minPopulation) {
        Set<String> result  = new TreeSet<>();
        
        for ( Location loc : locations) {
            if ( loc.state.equals(state) && loc.population > minPopulation) {
                result.add(loc.county);
            }
        }
        
        return result;
	}

	/*
	 * Returns a set containing all the states in the data 
	 * getStates() ->
	 * [Alabama,Alaska,Arizona,...
	 */
	public static Set<String> getStates() {
        Set<String> result  = new TreeSet<>();
        
        for ( Location loc : locations) {
            if ( !result.contains(loc.state)) {
                result.add(loc.state);
            }
        }
        
        return result;
	}

	/*
	 * Returns a map where the key is a state name, and the value is the state's
	 * code (abbreviation) 
	 * statesAndAbbreviations() -> Alabama---------->AL, ...
	 */
	public static Map<String, String> statesAndAbbreviations() {
		Map<String,String> result = new TreeMap<>();
		
		for ( Location loc : locations ) {
		    if ( !result.containsKey(loc.state) ) {
		        result.put(loc.state,loc.stateCode);
		    }
		}
		
		return result;
	}

	/*
	 * Returns a map where the key is a stateCode and the value is the number of zip
	 * codes in that state. 
	 * zipCountByStateCode() -> AK---------->238,
	 * AL---------->642, ...
	 */
	public static Map<String, Integer> zipCountByStateCode() {
        Map<String, Integer> result = new TreeMap<>();
        
        for ( Location loc : locations ) {
            if ( !result.containsKey(loc.stateCode) ) {
                result.put(loc.stateCode,1);
            }
            else {
                result.put(loc.stateCode,result.get(loc.stateCode) + 1);
            }
        }
        
        return result;
	}

	/*
	 * Returns a map where the key is a letter of the alphabet, and the value is a
	 * set of all the states beginning with that letter. 
	 * statesByFirstLetter() ->
	 * A---------->[Alabama, Alaska, Arizona, Arkansas], ...
	 */
	public static Map<Character, Set<String>> statesByFirstLetter() {
        Map<Character, Set<String>> result = new TreeMap<>();
        
        for ( Location loc : locations ) {
            if ( !result.containsKey(loc.state.charAt(0)) ) {
                Set<String> temp = new TreeSet<>();
                temp.add(loc.state);
                result.put(loc.state.charAt(0), temp);
            }
            else {
                result.get(loc.state.charAt(0)).add(loc.state);
            }
        }
        
        return result;
	}

	/*
	 * Returns a map where the key is a state and the value is the population of
	 * that state 
	 * populationByState() -> Alabama---------->4876208,
	 * Alaska---------->736502, ...
	 */
	public static Map<String, Integer> populationByState() {
        Map<String, Integer> result = new TreeMap<>();
        
        for ( Location loc : locations ) {
            if ( !result.containsKey(loc.state) ) {
                result.put(loc.state,loc.population);
            }
            else {
                result.put(loc.state,result.get(loc.state) + loc.population);
            }
        }
        
        return result;
	}

	/*
	 * Returns a map where the key is a time zone and the value is the population of
	 * that time zone 
	 * populationByTimeZone() -> America/Adak---------->269,
	 * America/Anchorage---------->631396
	 */
	public static Map<String, Integer> populationByTimeZone() {
        Map<String, Integer> result = new TreeMap<>();
        
        for ( Location loc : locations ) {
            if ( !result.containsKey(loc.timeZone) ) {
                result.put(loc.timeZone,loc.population);
            }
            else {
                result.put(loc.timeZone,result.get(loc.timeZone) + loc.population);
            }
        }
        
        return result;
	}

	/*
	 * Returns a map where the key is a state and the value is a set of all zip
	 * codes in that state that have a population of 50,000 or more
	 * populationByState() largeZipsInEachState() -> Florida---------->[33024,
	 * 33311], New Mexico---------->[87121], ...
	 */
	public static Map<String, Set<String>> largeZipsInEachState() {
        Map<String, Set<String>> result = new TreeMap<>();
        
        for ( Location loc : locations ) {
            if ( loc.population > 50000) {
                if ( !result.containsKey(loc.state) ) {
                    Set<String> temp = new TreeSet<>();
                    temp.add(loc.zip);
                    result.put(loc.state, temp);
                }
                else {
                    result.get(loc.state).add(loc.zip);
                }
            }
        }
        
        return result;
	}

	/*
	 * Returns a set of county names that are in more than one state. Note that some
	 * counties might be listed more than once in the same state, and that's not the
	 * same as being in more than one state. 
	 * commonCountyNames() -> [Adair,Adams,Albany,Alexander, ...
	 */
	public static Set<String> commonCountyNames() {
		Set<String> result = new TreeSet<>();
		Map<String, String> helper = new TreeMap<>();
		
		for ( Location loc : locations ) {
		    if ( !helper.containsKey(loc.county) ) {
		        helper.put(loc.county, loc.state);
		    }
		    else if ( !result.contains(loc.county) && !helper.get(loc.county).equals(loc.state)) {
		        result.add(loc.county);
		    }
		    
		}
		
		return result;
	}

	/*
	 * Returns the number of counties in a specified state. Note that some larger
	 * counties are listed more than once in a state because they have more than one
	 * zip code, but they are still the same county. Assume that county names are
	 * unique in each state. 
	 * countyCount("Ohio") -> 88
	 */
	public static int countyCount(String state) {
		Set<String> result = new TreeSet<>();
		
		for ( Location loc : locations ) {
		    if ( loc.state.equals(state) && !result.contains(loc.county) ) {
		        result.add(loc.county);
		    }
		}
		
		return result.size();
	} 

	/*
	 * Returns the number of counties in each state. Note that some larger counties
	 * are listed more than once in a state because they have more than one zip
	 * code, but they are still the same county. Assume that county names are unique
	 * in each state. countyCount() -> Ohio---------->88, Oklahoma---------->77,
	 * Oregon---------->37
	 */
	public static Map<String, Integer> countyCount() {
		Map<String, Set<String>> helper = new TreeMap<>();
		Map<String, Integer> result = new TreeMap<>();
		
		for ( Location loc : locations ) {
		    if ( !result.containsKey(loc.state)) {
		        helper.put(loc.state, new TreeSet<String>() );
		        result.put(loc.state, 0 );
		    }
		    if ( !helper.get(loc.state).contains(loc.county) ) {
		        helper.get(loc.state).add(loc.county);
		        result.put(loc.state, result.get(loc.state) + 1);
		    }
		}
		
		return result;
	}

	/*
	 * The goal of this method is to find the zip code in each state that has the
	 * highest population in that state. Since it's possible for two zip codes in a
	 * state to be tied for the highest population, each state should have a SET of
	 * zip codes that are tied for having the highest population in that state. You
	 * should be sure to test your code with sample data that includes ties. Return
	 * a map where the key is a state, and the value is the set of zip codes in that
	 * state that are tied for the highest population in that state.
	 * highestPopulationsInEachSate() -> Alabama---------->[35242],
	 * Alaska---------->[99654]
	 */
	public static Map<String, Set<String>> highestPopulationsInEachSate() {
	    Map<String, Set<String>> result = new TreeMap<>();
        Map<String, Integer> helper = new TreeMap<>();
        
        for ( Location loc : locations ) {
            if ( !result.containsKey(loc.state)) {
                result.put(loc.state, new TreeSet<String>() );
                helper.put(loc.state, 0 );
            }
            if ( loc.population == helper.get(loc.state) ) {
                result.get(loc.state).add(loc.zip);
            }
            else if ( loc.population > helper.get(loc.state) ) {
                result.get(loc.state).clear();
                result.get(loc.state).add(loc.zip);
                helper.put(loc.state, loc.population);
            }
        }
        
        return result;
	}

}
