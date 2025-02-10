import mu.testing.Problem; 
import mu.testing.Problems;
import mu.testing.TestCase;
import mu.testing.Problems.Verbosity;


/*
 * A demo of how MUTools works.
 * Noah Arnold
 */

@Problems(
		debugging = false, 
		doAll = false, 
		defaultRunningTimeMS = 100, 
		tolerance = 0.0000001, 
		verbosity = Verbosity.ERRORS)
public class SomeMethods {

	/*
	 * A CodingBat problem. Source: https://codingbat.com/prob/p136351
	 * Given a string, we'll say that the front is the first 3 chars of the string.
	 * If the string length is less than 3, the front is whatever is there. Return a
	 * new string which is 3 copies of the front.
	 */
	@Problem(doThis = true)
	@TestCase(io = "(`Java`) -> `JavJavJav`")
	@TestCase(io = "(`Chocolate`) -> `ChoChoCho`")
	@TestCase(io = "(`abc`) -> `abcabcabc`")
	@TestCase(io = "(`ab`) -> `ababab`")
	public static String front3(String str) {
	    String front;
		if (str.length() >= 3) {
		   front = str.substring(0, 3); // first three characters
		}
		else {
		    front = str;
		}
		return front + front + front;
	}
	
	/*
	 * Given an array of int values, modify the array so that each
	 * of its even elements is increased by 1. 
	 */
	@Problem(doThis = true)
	@TestCase(io = "([2, 4, 6, 8]) -> void", paramChanges="([3, 5, 7, 9])")
	@TestCase(io = "([4, 7, 10]) -> void", paramChanges="([5, 7, 11])")
	@TestCase(io = "([99]) -> void", paramChanges="([99])")
	@TestCase(io = "([200]) -> void", paramChanges="([201])")
	@TestCase(io = "([]) -> void", paramChanges="([])")
	public static void bumpEvens(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
		    if ( nums[i] % 2 == 0) {
		        nums[i]++;
		    }
		}
	}
	
	public static void main(String[] args) {
		mu.testing.Runner.run();
	}
}
