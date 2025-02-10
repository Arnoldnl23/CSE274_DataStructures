import mu.testing.Problem;
import mu.testing.Problems;
import mu.testing.TestCase;
import mu.testing.Problems.Verbosity;

import java.util.Arrays; // so that you can use Arrays.toString() 

// If you need to debug a method, set debugging to true, and set a breakpoint 
@Problems(debugging = false, 
			doAll = false, 
			defaultRunningTimeMS = 1000, 
			tolerance = 0.0000001, 
			verbosity = Verbosity.ERRORS)

public class ArrayPractice {
	// You may assume that all of the arrays are not null. But they could contain null.
	// The arrays may contain 0 elements.
	// You may not use the methods in the Arrays class or Collections class 
	// (except you may use Arrays.toString() to assist with testing in main()
	// You may not create other data structures (such as ArrayList, Map, etc)
    // Assignment completed by Noah Arnold


	/**************** Fundamental Array Operations ****************/
	
	@Problem(doThis = false)
	@TestCase(io = "([0,10,4,9]) -> 0")
	@TestCase(io = "([0,-10,-4,-9]) -> -10")
	@TestCase(io = "([3,3,3]) -> 3")
	/*
	 * Returns the smallest value in the array. The input array will contain at
	 * least one value.
	 * COMPLETE
	 */
	public static int min(int[] ary) {
		int smallest = Integer.MAX_VALUE;
		
		for (int n : ary) {
		    if (n < smallest) {
		        smallest = n;
		    }
		}
		return smallest;
	}
	
	@Problem(doThis = false)
	@TestCase(io = "([0,10,4,9]) -> 0")
	@TestCase(io = "([10,-10,-4,-9]) -> -9")
	@TestCase(io = "([6,1,2,3,7]) -> 6")
	@TestCase(io = "([-273]) -> -273")
	/*
	 * Given a non-empty array, return the value of the first element or the
	 * last element, whichever value is smaller.
	 * COMPLETE
	 */
	public static int minEnds(int[] ary) {
		if (ary[0] < ary[ary.length - 1]) {
		    return ary[0];
		}
		else {
		    return ary[ary.length - 1];
		}
	}

	@Problem(doThis = false)
	@TestCase(io = "([2,6,8,8]) -> true")
	@TestCase(io = "([10]) -> true")
	@TestCase(io = "([]) -> true")
	@TestCase(io = "([-8,-8,0,12]) -> true")
	@TestCase(io = "([2,6,4,8]) -> false")
	@TestCase(io = "([2,6,8,7]) -> false")
	@TestCase(io = "([10,9]) -> false")
	@TestCase(io = "([0,2,4,8,0]) -> false")
	/*
	 * Determines if the array is in ascending order. The array is considered sorted
	 * if every element is at least as large as its predecessor.
	 * COMPLETE
	 */
	public static boolean isSortedInts(int[] ary) {
		boolean isSorted = true;
	    
	    for (int i = 0; i < ary.length - 1; i++) {
		    if ( ary[i] > ary[i + 1] ) {
		        isSorted = false;
		    }
		}
		
		return isSorted;
	}

	@Problem(doThis = false)
	@TestCase(io = "(['a','a','d','z']) -> true")
	@TestCase(io = "(['a']) -> true")
	@TestCase(io = "([]) -> true")
	@TestCase(io = "(['a','m','k', 'z']) -> false")
	@TestCase(io = "(['z','x']) -> false")
	@TestCase(io = "(['a','a','d','z','w']) -> false")
	/*
	 * Determines if the array is in ascending order. The array is considered sorted
	 * if every element is at least as large as its predecessor.
	 * COMPLETE
	 */
	public static boolean isSortedChars(char[] ary) {
		boolean isSorted = true;
		
		for (int i = 0; i < ary.length - 1; i++) {
		    if ( ary[i] > ary[i+1] ) {
		        isSorted = false;
		    }
		}
		
		return isSorted;
	}

	@Problem(doThis = false)
	@TestCase(io = "([`hello`, `there`, `there`, `zoo`]) -> true")
	@TestCase(io = "([`hello`]) -> true")
	@TestCase(io = "([]) -> true")
	@TestCase(io = "([`hello`, `there`, `apple`, `zoo`]) -> false")
	@TestCase(io = "([`there`, `apple`]) -> false")
	@TestCase(io = "([`hello`, `there`, `zoo`, `zap`]) -> false")
	/*
	 * Determines if the array is in ascending order. The array is considered sorted
	 * if every element is at least as large as its predecessor. Use lexicographical
	 * ordering, which is provided by String.compareTo(). All elements of the array
	 * will be non-null.
	 * COMPLETE
	 */
	public static boolean isSortedStrings(String[] ary) {
		boolean isSorted = true;
		
		for (int i = 0; i < ary.length - 1; i++) {
		    if ( ary[i].compareTo(ary[i+1]) > 0 ) {
		        isSorted = false;
		    }
		}
		
		return isSorted;
	}
	
	@Problem(doThis = false)
	@TestCase(io = "([7,5,3,9], 8) -> true")
	@TestCase(io = "([7,5,3,9], 9) -> false")
	@TestCase(io = "([7,5,3,9], 16) -> false")
	@TestCase(io = "([20,-23], -3) -> true")
	@TestCase(io = "([7], 10) -> false")
	@TestCase(io = "([], 0) -> false")
	/*
	 * Returns true if there is a pair of adjacent elements in the array that
	 * add up to the specified target value
	 * COMPLETE
	 */
	public static boolean hasSumAdjacent(int[] ary, int target) {
        boolean hasSum = false;
        
        for (int i = 0; i < ary.length - 1; i++) {
            if ( ary[i] + ary[i+1] == target ) {
                hasSum = true;
            }
        }
        
        return hasSum;
	}

	@Problem(doThis = false)
	@TestCase(io = "([1,2,1,3,4,1], 1) -> 3")
	@TestCase(io = "([1,2,1,3,4,1], 5) -> 0")
	@TestCase(io = "([1,2,1,3,4,1], 2) -> 1")
	@TestCase(io = "([1,2,1,3,4,1], 4) -> 1")
	@TestCase(io = "([], 4) -> 0")
	@TestCase(io = "([4], 4) -> 1")
	@TestCase(io = "([4], 9) -> 0")
	/*
	 * Return the number of times "value" is present in the array.
	 * COMPLETE
	 */
	public static int getCount(int[] ary, int value) {
		int count = 0;
		
		for (int n : ary) {
		    if ( n == value) {
		        count++;
		    }
		}
		
		return count;
	}


	@Problem(doThis = false)
	@TestCase(io = "([1,2,1], [1,2,1]) -> true")
	@TestCase(io = "([1,2], [1,2,1]) -> false")
	@TestCase(io = "([1,2,1], [1,2]) -> false")
	@TestCase(io = "([], [1,2,1]) -> false")
	@TestCase(io = "([1,2,1], []) -> false")
	@TestCase(io = "([15], [15]) -> true")
	/*
	 * Returns only if both arrays have same length and all elements are identical.
	 * COMPLETE
	 */
	public static boolean arraysEqualsInts(int[] a1, int[] a2) {
		boolean equalArray = true;
		
		if ( a1.length == a2.length) {
		    for ( int i = 0; i < a1.length; i++) {
		        if ( a1[i] != a2[i] ) {
		            equalArray = false;
		        }
		    }
		}
		else {
		    equalArray = false;
		}
		
		return equalArray;
	}

	@Problem(doThis = false)
	@TestCase(io = "([`hello`,`there`,`hello`], [`hello`,`there`,`hello`]) -> true")
	@TestCase(io = "([`hello`,`there`], [`hello`,`there`,`hello`]) -> false")
	@TestCase(io = "([`hello`,`there`,`hello`], [`hello`,`there`]) -> false")
	@TestCase(io = "([], [`hello`,`there`,`hello`]) -> false")
	@TestCase(io = "([`hello`,`there`,`hello`], []) -> false")
	@TestCase(io = "([`world`], [`world`]) -> true")
	@TestCase(io = "([`A`], [null]) -> false")
	@TestCase(io = "([null], [`B`]) -> false")
	@TestCase(io = "([`B`, null, null, `A`], [`B`, null, null, `A`]) -> true")
	/*
	 * Returns only if both arrays have same length and all elements are identical.
	 * Here, consider two null values to be equivalent
	 * COMPLETE
	 */
	public static boolean arraysEqualsStrings(String[] a1, String[] a2) {
        boolean equalArray = true;
        
        if ( a1.length == a2.length) {
            for ( int i = 0; i < a1.length; i++) {
                if ( a1[i] != null && a2[i] != null ) {
                    if ( a1[i].compareTo(a2[i]) != 0) {
                        equalArray = false;
                    }
                }
                else if ( a1[i] != null ^ a2[i] != null) {
                    equalArray = false;
                }
            }
        }
        else {
            equalArray = false;
        }
        
        return equalArray;
	}
	
	@Problem(doThis = false)
	@TestCase(io = "([1,2,3,4]) -> void", paramChanges = "([0,1,2,3])")
	@TestCase(io = "([1]) -> void", paramChanges = "([0])")
	@TestCase(io = "([]) -> void", paramChanges = "([])")
	/*
	 * Decrease each element of the array by 1
	 * COMPLETE
	 */
	public static void shrinkAll(int[] ary) {
		for (int i = 0; i < ary.length; i++) {
		    ary[i]--;
		}
	}

	@Problem(doThis = false)
	@TestCase(io = "([1,2,3,4]) -> void", paramChanges = "([4,3,2,1])")
	@TestCase(io = "([1,3,5]) -> void", paramChanges = "([5,3,1])")
	@TestCase(io = "([1]) -> void", paramChanges = "([1])")
	@TestCase(io = "([]) -> void", paramChanges = "([])")
	/*
	 * Reverse the array so that the client's array is reversed.
	 * Note that the method MUST have a return type of void. As with
	 * all of these problems, you should not change the method header
	 * in any way.
	 * COMPLETE (Added a second test case for odd-length arrays)
	 */
	public static void reverseInPlace(int[] ary) {
	    for (int i = 0; i < ary.length/2; i++) {
	        int value1 = ary[i];
	        int value2 = ary[ary.length - 1 - i];
	        ary[i] = value2;
	        ary[ary.length - 1 - i] = value1;
	    }
	}

	/**************** Array Creation ****************/
	
	@Problem(doThis = false)
	@TestCase(io = "([1,2,3,4]) -> [4,3,2,1]")
	@TestCase(io = "([1]) -> [1]")
	@TestCase(io = "([]) -> []")
	/*
	 * Create a new array that is the reverse of the original. Do not change the
	 * client's array.
	 * COMPLETE
	 */
	public static int[] reverse(int[] ary) {
		int result[] = new int[ary.length];
		
		for (int i = 0; i < ary.length; i++) {
		    result[i] = ary[ary.length - 1 - i];
		}
		
		return result;
	}
	
	@Problem(doThis = false)
	@TestCase(io = "([1,2,3,4], 5) -> [1,2,3,4,5]")
	@TestCase(io = "([1],2) -> [1,2]")
	@TestCase(io = "([],7) -> [7]")
	@TestCase(io = "([1,2],-1) -> [1,2,-1]")
	/*
	 * Create an array with all the elements of arr, followed by the value n.
	 * Do not change the contents of the input array.
	 * COMPLETE
	 */
	public static int[] append(int[] arr, int n) {
		int arrNew[] = new int[arr.length + 1];
		
		for ( int i = 0; i < arr.length; i++) {
		    arrNew[i] = arr[i];
		}
		arrNew[arr.length] = n;
		
		return arrNew;
	}
	
	@Problem(doThis = false)
	@TestCase(io = "([1,2,3,4], 4) -> [1,2,3,4]")
	@TestCase(io = "([1,2,3,4], 3) -> [1,2,3]")
	@TestCase(io = "([1,2,3,4], 7) -> [1,2,3,4,-1,-1,-1]")
	@TestCase(io = "([],2) -> [-1,-1]")
	@TestCase(io = "([1,2],0) -> []")
	/*
	 * Create an array that is a copy of the input array, but with the
	 * specified size. If size is smaller than the input array, then
	 * return an array that truncates the original array so that it has
	 * only the number of elements specified by size. If size is larger
	 * than the original array, then pad the end of the new array with -1.
	 * Size will be non-negative
	 * COMPLETE
	 */
	public static int[] copy(int[] arr, int size) {
		int arrNew[] = new int[size];
		
		if ( arr.length <= size ) {
		    for ( int i = 0; i < arr.length; i++) {
		        arrNew[i] = arr[i];
		    }
		    for ( int i = arr.length; i < size; i++) {
		        arrNew[i] = -1;
		    }
		}
		else if ( arr.length > size ) {
            for ( int i = 0; i < size; i++) {
                arrNew[i] = arr[i];
            }
        }
		
		//System.out.println(Arrays.toString(arrNew));
		return arrNew;
	}
		

	@Problem(doThis = false)
	@TestCase(io = "([1,2,3,4], [5,6]) -> [1,2,3,4,5,6]")
	@TestCase(io = "([1],[2]) -> [1,2]")
	@TestCase(io = "([],[]) -> []")
	@TestCase(io = "([1,2],[]) -> [1,2]")
	@TestCase(io = "([],[1,2]) -> [1,2]")
	/*
	 * Create an array with all the elements of a1 followed by the elements of a2.
	 * The size of the array should be the sum of the lengths of the two input
	 * arrays. Do not change the contents of the input arrays.
	 * COMPLETE
	 */
	public static int[] concatenate(int[] a1, int[] a2) {
		int[] newArr = new int[a1.length + a2.length];
		
		for ( int i = 0; i < a1.length; i++ ) {
		    newArr[i] = a1[i];
		}
		for ( int i = 0; i < a2.length; i++ ) {
		    newArr[i + a1.length] = a2[i];
		}
		
		return newArr;
	}

	@Problem(doThis = false)
	@TestCase(io = "([]) -> []")
	@TestCase(io = "([1]) -> [1]")
	@TestCase(io = "([1,2]) -> [1]")
	@TestCase(io = "([1,2,3]) -> [1,3]")
	@TestCase(io = "([1,2,3,4]) -> [1,3]")
	/*
	 * Create an array with only the values at the even indices. The length of the
	 * array should be exactly the length to store these elements. Do not change the
	 * input array.
	 * COMPLETE
	 */
	public static int[] everyOther(int[] ary) {
		int[] newArr = new int[ (ary.length + 1) / 2 ];
		
		for ( int i = 0; i < ary.length; i += 2 ) {
		    newArr[i/2] = ary[i];
		}
		
		//System.out.println(Arrays.toString(newArr));
		return newArr;
	}

	@Problem(doThis = false)
	@TestCase(io = "([1], 0) -> []")
	@TestCase(io = "([1,2,3], 0) -> [2,3]")
	@TestCase(io = "([1,2,3], 1) -> [1,3]")
	@TestCase(io = "([1,2,3], 2) -> [1,2]")
	/*
	 * Return an array that has length one less than the length of ary. The contents
	 * of the new array is the same as array, but the value at index is erased. The
	 * array will have at least one element. index will be >= 0 and ary.length
	 * COMPLETE
	 */
	public static int[] removeAt(int[] ary, int index) {
		int[] newArr = new int[ary.length - 1];
		
		for ( int i = 0; i < index; i++ ) {
	        newArr[i] = ary[i];
		}
		for ( int i = index + 1; i < ary.length ; i++) {
		    newArr[i - 1] = ary[i];
		}
		
		return newArr;
	}

	/**************** Array Manipulation ****************/
	@Problem(doThis = false)
	@TestCase(io = "([1,2,3,4], 0) -> void", paramChanges = "([0,0,0,0], *)")
	@TestCase(io = "([1], 100) -> void", paramChanges = "([100], *)")
	@TestCase(io = "([], 100) -> void", paramChanges = "([], *)")
	/*
	 * Set all the elements of ary to the given value.
	 * COMPLETE
	 */
	public static void fill(int[] ary, int value) {
	    for ( int i = 0; i < ary.length; i++ ) {
	        ary[i] = value;
	    }
	}

	@Problem(doThis = false)
	@TestCase(io = "(0, 'A') -> []")
	@TestCase(io = "(1, 'A') -> ['A']")
	@TestCase(io = "(3, 'A') -> ['A','A','A']")
	/*
	 * Create a array of the given length, where all elements of the array are set
	 * to the given value.
	 * COMPLETE
	 */
	public static char[] constantArray(int len, char value) {
	    char[] newArr = new char[len];
	    
	    for ( int i = 0; i < newArr.length; i++ ) {
            newArr[i] = value;
        }
	    
	    return newArr;
	}

	@Problem(doThis = false)
	@TestCase(io = "([]) -> void", paramChanges = "([])")
	@TestCase(io = "([1]) -> void", paramChanges = "([1])")
	@TestCase(io = "([1,2]) -> void", paramChanges = "([2,1])")
	@TestCase(io = "([1,2,3,4,5,6]) -> void", paramChanges = "([2,3,4,5,6,1])")
	/*
	 * Move all the elements of the array left one spot, with the first element
	 * "rotating around" the to become the last element.
	 * COMPLETE
	 */
	public static void rotateLeft(int[] ary) {
	    if ( ary.length > 0) {
	        int first = ary[0];
	        for ( int i = 0; i < ary.length - 1; i++ ) {
	            ary[i] = ary[i + 1];
	        }
	        
	        ary[ary.length - 1] = first;
	    }
	}

	/**************** Nested Loops ****************/
	@Problem(doThis = false)
	@TestCase(io = "([2,2,2,2,3,3], [2,3]) -> true")
	@TestCase(io = "([2,2,2,2,3,3],[2,4]) -> false")
	@TestCase(io = "([],[]) -> true")
	@TestCase(io = "([1,8,2,3,4],[4,3,8,1,1,1,2]) -> true")
	/*
	 * Returns true if all the values in a1 appear in a2. And, all the values in a2
	 * appear in a1.
	 * COMPLETE
	 */
	public static boolean hasSameValues(int[] a1, int[] a2) {
	    boolean hasSameValues = true;
	    boolean keepChecking = true;
	    
	    for ( int i : a1 ) {
            if ( getCount(a1,i) == 0 || getCount(a2, i) == 0 || keepChecking == false) {
                hasSameValues = false;
                keepChecking = false;
            }
	    }
	    
	    return hasSameValues;
	}

	@Problem(doThis = true)
	@TestCase(io = "([2,3], [3,2]) -> true")
	@TestCase(io = "([2,2,2,2,3,3],[2,4]) -> false")
	@TestCase(io = "([],[]) -> true")
	@TestCase(io = "([1,1,2],[2,1,1]) -> true")
	@TestCase(io = "([1,2,2],[2,1,1]) -> false")
	/*
	 * Returns true if the values that appear in a1 are exactly those that appear in
	 * a2. Unlike the method containsSameValues, the number of appearances of each
	 * value must match. Do not alter the original arrays.
	 */
	public static boolean sameElements(int[] a1, int[] a2) {
	    boolean hasSameElements = true;
	    boolean keepChecking = true;
	    
        for ( int i : a1 ) {
            if ( getCount(a1,i) != getCount(a2, i) || keepChecking == false) {
                hasSameElements = false;
                keepChecking = false;
            }
        }
        
        return hasSameElements;
	}
	
	
	@Problem(doThis = false)
	@TestCase(io = "([7,5,3,9], 8) -> true")
	@TestCase(io = "([7,5,3,9], 9) -> false")
	@TestCase(io = "([7,5,3,9], 16) -> true")
	@TestCase(io = "([1,1,1,1,1], 2) -> true")
	@TestCase(io = "([7], 14) -> false")
	@TestCase(io = "([], 0) -> false")
	/*
	 * Returns true if there is a pair of elements somewhere in the array that
	 * add up to the specified target value
	 * COMPLETED
	 */
	public static boolean hasSum(int[] ary, int target) {
		boolean hasSum = false;
		
		for ( int i = 0; i < ary.length; i++ ) {
		    for ( int j = 0; j < ary.length; j++ ) {
		        if ( i != j ) {
		            if ( ary[i] + ary[j] == target ) {
		                hasSum = true;
		            }
		        }
		    }
		}
		
		return hasSum;
	}
	
	@Problem(doThis = false)
	@TestCase(io = "([[1,2],[3],[],[4,5,6]]) -> 21")
	@TestCase(io = "([[3],[1],[4],[1],[5]]) -> 14")
	@TestCase(io = "([[]]) -> 0")
	@TestCase(io = "([[],[],[]]) -> 0")
	@TestCase(io = "([[4,8,15,16,23,42]]) -> 108")
	@TestCase(io = "([[0]]) -> 0")
	/*
	 * Computes the sum of all the int values in a 2D array
	 * COMPLETE
	 */
	public static int sum2D(int[][] ary) {
	    int total = 0;
	    
		for ( int[] row : ary ) {
		    for ( int i : row ) {
		        total += i;
		    }
		}
		
		return total;
	}
	
	@Problem(doThis = false)
	@TestCase(io = "([[1,2],[3],[],[4,5,6]]) -> [1,2,3,4,5,6]")
	@TestCase(io = "([[3],[1],[4],[1],[5]]) -> [3,1,4,1,5]")
	@TestCase(io = "([[]]) -> []")
	@TestCase(io = "([[],[],[]]) -> []")
	@TestCase(io = "([[4,8,15,16,23,42]]) -> [4,8,15,16,23,42]")
	@TestCase(io = "([[0]]) -> [0]")
	/*
	 * Flattens a 2D array into a 1D array so that
	 * the 1D array contains all the same int values, in the same
	 * order as they appeared in the 2D array. Assume that there
	 * are no null elements.
	 * COMPLETE
	 */
	public static int[] flatten(int[][] ary) {
		int[] flatArray = new int[0];
		
		for ( int[] row : ary) {
		    for ( int i : row ) {
		        flatArray = append(flatArray , i);
		    }
		}
		
		return flatArray;
	}
	

	/**************** Java's class named: Arrays ****************/
	// Some useful methods for CSE 274: binarySearch, copyOf, copyOfRange, equals,
	// fill, sort, toString

	public static void main(String[] args) {
		mu.testing.Runner.run();
		
	}
}