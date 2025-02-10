/*
 * YOUR INFORMATION HERE:
 * Name: Noah Arnold
 * Practice working with a Node class.
 * Before writing the code below, we need to write a Node class.
 * We will make it a node where the data is an int.
 * 
 * RULES: Except for the toArray() method, you cannot create any other
 * collections. Don't create arrays or lists or sets or anything else like
 * that.
 */
public class LinkedNodeExercises {

	public static void main(String[] args) {
		// Do testing of your own here. It's still a reliable way
		// to test as you go. But once you think a method is
		// complete, check the JUnit tester to see if it passes.
	    Node n = new Node(1);
	    System.out.println(n.data);
	    System.out.println(n.next);
	    
	    n.next = new Node(4);
	    System.out.println(n.next.data);
	    n.next.next = new Node(99);
	    System.out.println(n.next.next.data);
	    
	    //Insert 100 between the first two nodes
	    Node temp = new Node(100);
	    temp.next = n.next;
	    n.next = temp;
	    System.out.println(n.next.data);
	    
	    temp = new Node(50);
	    temp.next = n;
	    n = temp;
	    System.out.println(n.data);
	    System.out.println(n.next.data);
	    
	    Node current = n;
	    while ( current != null ) {
	        System.out.println(current.data + " ");
	        current = current.next;
	    }
	    
	    System.out.println(n.data);
	    System.out.println(dataToString(n));
	    
	    //remove the 1 from the chain
	    n.next = n.next.next;
	    
	    //Tests sumFirstTwo
	    System.out.println(sumFirstTwo(n));
	    Node single = new Node(1000);
	    System.out.println(sumFirstTwo(single));
	    
	    //Build a chain of nodes from an array:
	    int[] data = {4, 8, 15, 16, 23, 42};
	    Node chain = buildChain(data);
	    System.out.println(dataToString(chain));
	    int[] data2 = {};
	    System.out.println(buildChain(data2));
	    
	   
	    
	}
	
	/*
	 * Returns the sum of the numbers in the first two nodes in a non-empty
	 * chain of nodes. If there is only one node, then just return the number
	 * in that node.
	 */
	public static int sumFirstTwo(Node start) {
		if (start.next == null) {
		    return start.data;
		}
		
		return start.data + start.next.data;
	}
	
	/*
	 * Returns the number of nodes in a chain of nodes beginning at the
	 * specified node.
	 */
	public static int count(Node start) {
		int count = 0;
		
		Node current = start;
		while ( current != null ) {
		    count++;
		    current = current.next;
		}
		
		return count;
	}
	
	/*
	 * Returns the sum of the numbers in the last two nodes in a non-empty
	 * chain of nodes. If there is only one node, then just return the number
	 * in that node.
	 */
	public static int sumLastTwo(Node start) {
		Node current = start;
		int sum = 0;
		
		if ( start.next == null ) {
		    return start.data;
		}
		
		while ( current.next != null ) {
            sum = current.data + current.next.data;
            current = current.next;
        }
		
		return sum;
	}
	
	/*
	 * Returns a String containing all the data in a chain of nodes starting at the
	 * specified node. The data is space-separated, with no leading or trailing
	 * spaces. If the start node is null, return the string "EMPTY".
	 * COMPLETE
	 */
	public static String dataToString(Node start) {
		String result = "";
		
		Node current = start;
		if (current == null) {
		    return "EMPTY";
		}
		
		while ( current != null ) {
		    result += current.data + " ";
		    current = current.next;
		}
		
		return result.trim();
	}

	/*
	 * Creates a chain of nodes from the specified array of data, where the head
	 * node will contain the data at index 0. If the array is empty, returns null.
	 */
	public static Node buildChain(int[] nums) {
	    
	    Node start = null;
	    for ( int i = nums.length - 1; i >= 0; i-- ) {
		    Node temp = new Node(nums[i]);
	        temp.next = start;
		    start = temp;
		}
		
		return start;
	}
	
	/*
	 * Returns the sum of the data in a chain of nodes beginning at the
	 * specified node.
	 */
	public static int sum(Node start) {
        int total = 0;
        
        Node current = start;
        while ( current != null ) {
            total += current.data;
            current = current.next;
        }
        
        return total;	
	}

	/*
	 * Modifies a non-empty chain of nodes by swapping the data in the first and
	 * last nodes. This does NOT rewire nodes but just changes the data. It's possible
	 * that the first node IS the last node.
	 */
	public static void swapEnds(Node start) {
	    if ( count(start) == 1) {
	        return;
	    }
	    
	    Node current = start;
	    Node finalNode = null;
	    
	    while ( current != null ) {
	        if ( current.next == null ) {
	            finalNode = current;
	        }
	        current = current.next;
	    }
	    
	    Node tempLast = new Node(start.data);
	    start.data = finalNode.data;
	    removeLast(start);
	    append(start,tempLast);
	}

	/*
	 * Returns true if the key appears as data in the chain of nodes beginning at
	 * the specified start node, and false otherwise.
	 */
	public static boolean contains(Node start, int key) {
        Node current = start;
        
        while ( current != null ) {
            if ( current.data == key ) {
                return true;
            }
            current = current.next;
        }
        
        return false;   
	}

	/*
	 * Returns the index of the first occurrence of key in the chain of nodes,
	 * beginning with the specified start node, or returns -1 if the key is not in
	 * the chain. For example, if the chain were: 3, 7, 9, 3 Then indexOf(start, 3)
	 * would return 0 indexOf(start, 9) would return 2 indexOf(start, 50) would
	 * return -1
	 */
	public static int indexOf(Node start, int key) {
        int index = 0;
        
        Node current = start;
        while ( current != null ) {
            if ( current.data == key ) {
                return index;
            }
            index++;
            current = current.next;
        }
          
		return -1;
	}

	/*
	 * Returns the number of times that the key appears as data in the chain of
	 * nodes beginning at the specified start node.
	 */
	public static int getFrequency(Node start, int key) {
        int freq = 0;
        
        Node current = start;
        while ( current != null ) {
            if ( current.data == key ) {
                freq++;
            }
            current = current.next;
        }
          
        return freq;
	}

	/*
	 * Appends the second chain of linked nodes to the end of the first chain of
	 * linked nodes. For example, if first were: 3, 4, 5, and second were 8, 4, 9,
	 * then at the end of this method, first will be 3, 4, 5, 8, 4, 9 Assume each
	 * chain contains at least one node.
	 */
	public static void append(Node first, Node second) {
	    if ( first == null ) {
	        first = second;
	        return;
	    }
	    
	    Node current = first;
	    Node previous = null;    
	    while (current != null) {
	        previous = current;
	        current = current.next;
	    }
	    
	    previous.next = second;
	}

	/*
	 * Gets the data in the last node of a non-empty chain of nodes
	 */
	public static int getLast(Node start) {
	    Node current = start;
	    int lastNum = 0;
	    
	    while ( current != null ) {
            lastNum = current.data;
            current = current.next;
        }
	    
	    return lastNum;
	}

	/*
	 * Removes the last node of a chain of nodes. Assume that the chain contains at
	 * least 1 nodes.
	 */
	public static void removeLast(Node start) {
	    Node current = start;
	    
	    if ( current.next == null ) {
	        current = null;
	    }
	    
	    while ( current != null ) {
            if ( current.next.next == null) {
                current.next = null;
            }
            current = current.next;
        }
	    
	}

	/*
	 * Removes the first node of a non-empty chain of nodes. NOTE: this will change
	 * the object that start refers to, but start is just a parameter variable, it
	 * is necessary to RETURN the reference to the new starting node. Generally
	 * speaking when we are writing code that changes what the starting node points
	 * to, we will need to treat that in a special way. If the chain of nodes 
	 * has only one node, then this should return null. 
	 */
	public static Node removeFirst(Node start) {
	    Node current = start;
	    
		if ( current.next == null ) {
		    return null;
		}
		
		start = start.next;
		return start;
	}
	
	/*
	 * Removes the node containing the first occurrence of the specified key, if it
	 * exists. Otherwise, makes no changes. The method returns the head of the
	 * resulting chain of nodes. If the starting or ending chain of nodes is empty,
	 * return null.
	 * Any method that changes what the head node points to needs to be
	 * treated in a special way.
	 * INCOMPLETE
	 */
	public static Node removeFirstOccurrence(Node start, int key) {
	    if ( start == null ) {
	        return null;
	    }
		if ( start.data == key ) {
		    start = start.next;
		    return start;
		}
		
		Node current = start;
		Node previous = null;
		while ( current != null ) {
		    if ( current.data != key ) {
		        if ( previous == null ) {
		            previous = new Node(current.data);
		        }
		        else {
		            append(previous, new Node(current.data));
		        }
		    }
		    else {
		        append(previous, current.next);
		        return previous;
		    }
		    current = current.next;
		}
		
		return previous;
	}
	/*
	 * Given a chain of nodes THAT COULD BE EMPTY, return true if there are two
	 * side-by-side nodes whose data add up to 10.
	 * For example:
	 * If the chain were 1, 7, 2, 8, 5 then return true (because 2 + 8 = 10)
	 * If the chain were 3, 10, 7 then return false
	 * If the chain were 3, 10, 0 then return true
	 * If the chain were 10 then return false
	 * If the chain were 9 then return false
	 * If the chain has no nodes then return false
	 */
	public static boolean has10SumAdjacent(Node start) {
		Node current = start;
		
		if ( current == null ) {
		    return false;
		}
		
		while ( current.next != null ) {
		    if ( current.data + current.next.data == 10 ) {
		        return true;
		    }
		    current = current.next;
		}
		
		return false;
	}
	
	/*
	 * Given a chain of nodes, return true if there are two different
	 * nodes in the chain whose data add up to 10.
	 * For example:
	 * If the chain were 1, 7, 2, 5, 8 then return true (because 2 + 8 = 10)
	 * If the chain were 3, 10, 7 then return true
	 * If the chain were 3, 10 then return false
	 * If the chain were 5 then return false
	 * If the chain were 10 then return false
	 * If the chain were 5, 1, 5 then return true
	 * If the chain has no nodes then return false
	 */
	public static boolean has10SumAnywhere(Node start) {
	    Node current = start;
		if ( current == null ) {
		    //System.out.println("At the front");
		    return false;
		}
		
		while ( current != null ) {
		    Node current2 = start;
		    while ( current2 != null ) {
		        if ( current.data + current2.data == 10 ) {
		            if ( current.data != current2.data ) {
		                //System.out.println("in the middle");
		                return true;
		            }
		        }
		        current2 = current2.next;
		    }
		    current = current.next;
		}
		
		if ( getFrequency(start,5) > 1 ) {
		    //System.out.println("Checking fives");
		    return true;
		}
		
		//System.out.println("At the end");
		return false;
	}
	
	/*
	 * Given a chain of nodes THAT COULD BE EMPTY, compute the sum of all values that appear
	 * immediately before a 10. For example: if the chain were 10, 7, 5, 10, 8, 10
	 * then return 13 (5 + 8) if the chain were 10, 10, 10, 10 then return 30 (10 +
	 * 10 + 10) if the chain were 3, 10 then return 3. If the chain were 10, 3 then return
	 * 0. If the chain were a single node or is empty, return 0.
	 */
	public static int sumBefore10(Node start) {
		int sum = 0;
		
		Node current = start;
		if ( current == null || current.next == null ) {
		    return 0;
		}
		
		while ( current.next != null ) {
		    if ( current.next.data == 10 ) {
		        sum += current.data;
		    }
		    current = current.next;
		}
		
		return sum;
	}
	
	/*
	 * Creates an array containing the data in the chain of nodes,
	 * in the same order that it appears in the chain. If the start node
	 * is null, returns an empty array.
	 */
	public static int[] toArray(Node start) {
		Node current = start;
		int[] finalArray = new int[count(start)];
		
		if ( current == null ) {
		    return finalArray;
		}
		
		int size = 0;
		while ( current != null ) {
		    finalArray[size] = current.data;
		    size++;
		    current = current.next;
		}
		
		return finalArray;
	}

	/*
	 * Creates a chain of nodes consisting of only the even numbers from the
	 * given chain of nodes, and returns the starting node of the new chain.
	 * The order of the new nodes should be the same as their order in the
	 * original chain.
	 * Do not modify the original chain of nodes. Don't use arrays or any
	 * other collections. Just work with the given chain of nodes to build
	 * a new chain of nodes. 
	 */
	public static Node justTheEvens(Node start) {
		Node current = start;
		Node evens = null;
		
		while ( current != null ) {
		    if ( current.data % 2 == 0) {
		        if ( evens == null ) {
		            evens = new Node(current.data);
		        } else {
		            Node temp = new Node(current.data);
		            Node tempEvens = evens;
		            while ( tempEvens.next != null ) {
		                tempEvens = tempEvens.next;
		            }
		            tempEvens.next = temp;
		            tempEvens = evens;
		        }
		    }
		    current = current.next;
		}
		
		return evens;
	}
	
}
