import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;

// Stack lab - Implement the methods in this class
// Name: YOUR_NAME_HERE

public class StackProblems {

	public static void main(String[] args) {
		// Do your testing here. Write test code BEFORE writing the method.
		Stack<Integer> stk = new LinkedStack<>();
		stk.push(4);
		stk.push(5);
		stk.push(5);
		stk.push(6);
		stk.push(7);
		stk.push(7);
		
//		System.out.println(stk);
//		swapTopBottom(stk);
//		System.out.println(stk);
		
		//System.out.println(sum(stk));
		
		//pushUnder(stk,99);
		//System.out.println(stk);
		
		//System.out.println(fillStack(0));
		
		//System.out.println(sumSkipDuplicates(stk));
		
		//System.out.println(stringToStack("wombat"));
		
		stk.clear();
		System.out.println(stk);
		reverseStack(stk);
		System.out.println(stk);
		
		//System.out.println("Duplicate: " + duplicateStack(stk));
		
//		System.out.println("Bottom: " + peekBottom(stk));
//		System.out.println("Stack: " + stk);
		
//		stk.clear();
//		stk.push(4);
//		stk.push(7);
//		stk.push(null);
//		stk.push(6);
//		stk.push(null);
//		stk.push(10);
//		System.out.println("Old stack: " + stk);
//		removeNulls(stk);
//		System.out.println("New stack: " + stk);
		
//		Stack<String> stk2 = new LinkedStack<>();
//		stk2.push("cat");
//		stk2.push("dog");
//		stk2.push("cat");
//		stk2.push("fish");
//		stk2.push("rat");
//		System.out.println("Stack beforehand: " + stk2.toString());
//		Set<String> stkSet = getUniqueItems(stk2);
//		System.out.println(stkSet);
//		System.out.println("Stack afterwards: " + stk2.toString());
	}

	/*
	 * Computes the sum of all the numbers in the stack. It's ok to destroy the
	 * stack in the process.
	 */
	public static int sum(Stack<Integer> data) {
		int sum = 0;
		while ( !data.isEmpty() ) {
		    sum += data.pop();
		}
		return sum;
	}

	/*
	 * Puts an integer under the top item in a stack. If the stack is empty, just
	 * put the item on the top two times. For example: if stk starting at the top
	 * is: 7, 8, 5, 11, then pushUnder(stk, 20) would result in: 7, 20, 8, 5, 11 For
	 * example: if stk is empty, then pushUnder(stk, 20) would result in: 20, 20
	 */
	public static void pushUnder(Stack<Integer> data, int value) {
	    if ( data.isEmpty() ) {
	        data.push(value);
	        data.push(value);
	    }
	    else {
	        Integer popped = data.pop();
	        data.push(value);
	        data.push(popped);
	    }
	}

	/*
	 * Returns a stack with n items, where each item is the value n. Assume that n
	 * is non-negative. For example: fillStack(5) returns a stack containing the
	 * strings 5, 5, 5, 5, 5 fillStack(2) returns a stack containing the strings 2,
	 * 2 fillStack(0) returns an empty stack.
	 */
	public static Stack<String> fillStack(int n) {
		Stack<String> newStack = new ArrayStack<String>();
	    for ( int i = 0; i < n; i++) {
		    newStack.push(""+n);
		}
	    return newStack;
	}

	/*
	 * Computes the sum of all the numbers in the stack. However, if two or more
	 * numbers in a row are equal, only add one of them. So, for example, if the
	 * stack contained 4, 4, 1, 2, 2, 7, 2, 8, 8, 8, 4, 4 then the numbers that
	 * would be added would be 4 + 1 + 2 + 7 + 2 + 8 + 4 = 28.
	 */
	public static int sumSkipDuplicates(Stack<Integer> data) {
        int sum = 0;
        Integer lastNum = null;
        while ( !data.isEmpty() ) {
            if ( lastNum != data.peek()) {
                lastNum = data.peek();
                sum += data.peek();
            }
            data.pop();
        }
        return sum;
	}

	/*
	 * Puts all of the characters of a string into a stack, in order, with the first
	 * character of the string at the top of the stack and the last character of the
	 * string at the bottom of the stack.
	 */
	public static Stack<Character> stringToStack(String s) {
		Stack<Character> result = new ArrayStack<>();
	    for ( int i = s.length() - 1; i >= 0; i-- ) {
		    result.push(s.charAt(i));
		}
	    return result;
	}

	/*
	 * Reverses a given stack, so that the top of the stack becomes the bottom and
	 * the bottom becomes the top. YOU MAY USE ADDITIONAL STACKS AS NEEDED BUT YOU
	 * MAY NOT USE ANY OTHER DATA STRUCTURES (no arrays, ArrayList, HashSet, etc.)
	 */
	public static <T> void reverseStack(Stack<T> s) {
	    if (s.isEmpty()) {
	        return;
	    }
	    
        Stack<T> temp = copyStack(s);
        s.clear();
        
        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }

	}

	/*
	 * Returns an exact copy of the given stack. At the end of this method the
	 * original stack should be the same as when it started, and a new copy of that
	 * stack should be returned. YOU MAY USE ADDITIONAL STACKS AS NEEDED BUT YOU MAY
	 * NOT USE ANY OTHER DATA STRUCTURES (no arrays, ArrayList, HashSet, etc.)
	 */
	public static Stack<Integer> duplicateStack(Stack<Integer> s) {
	    return copyStack(s);
	}

	/*
	 * Modify a stack of Integers, removing all occurrences of null from the stack.
	 * For example, if the stack from top to bottom were: 3, 0, null, -5, null, then
	 * at the end of the method the stack should be 3, 0, -5 You may use another
	 * stack, but don't use any other collections to solve this. NOTE: you cannot
	 * store null in a primitive. BAD: int i = null; OK: Integer i = null;
	 */
	public static void removeNulls(Stack<Integer> stk) {
	    Stack<Integer> temp = new ArrayStack<>();
	    
	    while(!stk.isEmpty()) {
	        if ( stk.peek() != null ) {
	            temp.push(stk.peek());
	        }
	        stk.pop();
	    }
	    
        while (!temp.isEmpty()) {
            stk.push(temp.pop());
        }
	}

	/*
	 * Returns a set of all values in the stack. At the end of this method, the
	 * stack should be in the same order as when it started. Use another stack to
	 * help solve this problem.
	 */
	public static Set<String> getUniqueItems(Stack<String> stk) {
	    if (stk.isEmpty()) {
	        return null;
	    }
		Stack<String> temp = copyStack(stk);
		Set<String> result = new HashSet<>();
		
		while (!temp.isEmpty()) {
		    result.add(temp.pop());
		}
		
		return result;
	}

	/*
	 * Returns the item at the bottom of the stack. If the stack is empty, throw an
	 * EmptyStackException. At the end of this method, the stack should be in the
	 * same order as when it started.
	 */
	public static <T> T peekBottom(Stack<T> stk) {
	    if (stk.isEmpty()) {
            throw new EmptyStackException();
        }
	    T result = null;
	    Stack<T> temp = copyStack(stk);
	    
	    while (!temp.isEmpty()) {
	        result = temp.pop();
	    }
	    
	    return result;
	}
	
	/*
	 * Modifies the stack by swapping the top and bottom elements.
	 * No change will be made if there are fewer than two elements.
	 */
	private static <T> void swapTopBottom(Stack<T> stk) {
	    if (stk.isEmpty()) {
	        return;
	    }
	    Stack<T> temp = new ArrayStack<>();
	    T oldTop = stk.pop();
	    
	    //Checks if there is only one item, and returns if there is
	    if (stk.isEmpty()) {
	        stk.push(oldTop);
	        return;
	    }
	    
	    while (!stk.isEmpty()) {
	        temp.push(stk.pop());
	    }
	    
	    T oldBottom = temp.pop();
	    
	    stk.push(oldTop);
	    while (!temp.isEmpty()) {
	        stk.push(temp.pop());
	    }
	    stk.push(oldBottom);
	    
	}
	
	/*
	 * Returns an exact copy of a given stack for any type T. The
	 * original stack is left the same and a new one is returned.
	 */
    private static <T> Stack<T> copyStack(Stack<T> s) {
        Stack<T> result = new ArrayStack<>();
        Stack<T> temp = new ArrayStack<>();

        while (!s.isEmpty()) {
           temp.push(s.pop());
        }
        while (!temp.isEmpty()) {
           s.push(temp.peek());
           result.push(temp.pop());
        }

        return result;
    }

	// Runs some very basic tests on a stack implementation
	public static void quickCheck(Stack<Integer> stk) {
		System.out.println("true? " + stk.isEmpty());
		System.out.print("1 2 3 4 5 6 7 8 9 10 11? ");
		for (int i = 1; i <= 11; i++) {
			System.out.print(stk.push(i) + " ");
		}
		System.out.println();
		System.out.println("false? " + stk.isEmpty());

		System.out.println("11? " + stk.peek());
		System.out.println("11? " + stk.peek());

		System.out.println("11? " + stk.pop());
		System.out.println("10? " + stk.pop());

		stk.clear();
		System.out.println("true? " + stk.isEmpty());

		String message = "Exception not thrown correctly for peek().";
		try {
			stk.peek();
		} catch (EmptyStackException e) {
			message = "Correct exception thrown for peek(). Yay!";
		}

		System.out.println(message);

		message = "Exception not thrown correctly for pop().";
		try {
			stk.pop();
		} catch (EmptyStackException e) {
			message = "Correct exception thrown for pop(). Yay!";
		}

		System.out.println(message);
	}
}
