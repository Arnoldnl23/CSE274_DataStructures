import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordTree {

	// Don't add any other instance variables. 
	private Node root;

	/*
	 * Don't modify this.
	 */
	public WordTree() {
		// the root node doesn't need
		// a letter in it because it's just
		// there to hold child nodes.
		root = new Node(' ');
	}
	
	/*
	 * Computes recursively the number of nodes
	 * (NOT including the root node). For example
	 * if the tree contained only the words "apple"
	 * and "apples", then nodeCount() would return 6.
	 * 
	 * COMPLETE
	 */
	public int nodeCount() {
		return nodeCount(root) - 1;
	}
	
	private int nodeCount(Node start) {
	    if (start == null) {
	        return 0;
	    }
	    
	    int count = 1;
	    
	    for (Node n : start.children ) {
	        count += nodeCount(n);
	    }
	    
	    return count;
	}
	
	/*
	 * Computes the total number of letters in all
	 * the words combined. For example
	 * if the tree contained only the words "apple"
	 * and "apples", then letterCount() would return 11.
	 * 
	 * COMPLETE
	 */
	public int letterCount() {
	    int result = 0;
		result = letterCount(root, 1);
		return result;
	}
	
	private int letterCount(Node top, int depth) {
	    if ( top == null ) {
	        return 0;
	    }
	    
        int count = 0;
        
        for (Node n : top.children ) {
            if ( n.endOfWord ) {
                count += depth;
            }
            count += letterCount(n, depth + 1);
        }
        
        return count;
	}
	
	/*
	 * Computes the total number of words. For example
	 * if the tree contained only the words "apple"
	 * and "apples", then wordCount() would return 2.
	 * Solve this recursively.
	 * 
	 * COMPLETE
	 */
	public int wordCount() {
		return wordCount(root);
	}

	private int wordCount(Node start) {
        if (start == null) {
            return 0;
        }
        
        int count = 0;
        
        for (Node n : start.children ) {
            if ( n.endOfWord ) {
                count++;
            }
            count += wordCount(n);
        }
        
        return count;
	}
	
	// See video
	//COMPLETE
	public String toString() {
		return root.toString();
	}
	
	/*
	 * Returns an ArrayList containing all the words
	 * in the tree. Solve this recursively. See the video
	 * on using recursion to build a collection.
	 * 
	 * COMPLETE
	 */
	public ArrayList<String> wordList() {
		ArrayList<String> result = new ArrayList<>();
		
		wordList(root, "", result);
		
		return result;
	}
	
	private void wordList(Node top, String stringSoFar, ArrayList<String> result) { 
	    if ( top == null ) {
	        return;
	    }
	    else if ( top.endOfWord ) {
	        result.add(stringSoFar);
	    }
	    
	    for ( Node n : top.children ) {
	        wordList(n, stringSoFar + n.letter, result);
	    }
	}
	
	
	/*
	 * Adds the specified word to the set, if the word is
	 * not already in the set. The word should not be an empty string.
	 * Returns true if the word was successfully added, and false
	 * otherwise (if the word already existed)
	 * 
	 * COMPLETE
	 */
	public boolean add(String word) {
		if (word.equals("")) {
			throw new IllegalArgumentException("Can't add empty string.");
		}
		
    	if ( contains(word) ) {
    	    return false;
   		}
		
        add(this.root, word);
		
		return true;
	}
	
	private void add(Node top, String word) {
        Node curr = top;
        Node temp = curr.getChild(word.charAt(0));
        if ( temp == null ) {
            curr.children.add(new Node(word.charAt(0)));
        }
        curr = curr.getChild(word.charAt(0));
        
        if ( word.substring(1).equals("")) {
            curr.endOfWord = true;
            return;
        }
        else {
            add(curr, word.substring(1));
        }
	}

	/*
	 * Returns true if the specified word is in the set.
	 * Keep in mind: if the the set only contains the
	 * word "apples", then contains("apples") should return
	 * true, but contains("apple") should return false.
	 * 
	 * COMPLETE
	 */
	public boolean contains(String word) {
		Node curr = root;
	    for ( int i = 0; i < word.length(); i++ ) {
		    Node temp = curr.getChild(word.charAt(i));
		    if ( temp == null ) {
		        return false;
		    }
		    curr = curr.getChild(word.charAt(i));
		}
	    
	    if ( curr.endOfWord ) {
	        return true;
	    }
	    
	    return false;
	}
	
	/*
	 * I RECOMMEND SAVING THIS METHOD FOR LAST.
	 * Removes the specified word from the set if it exists.
	 * Returns true if the word was found and removed, and
	 * false otherwise.
	 * Consider using a stack to hold the nodes of 
	 * the word, so that you can backtrack to remove the
	 * appropriate nodes.
	 * 
	 * TODO
	 */
	public boolean remove(String word) {
	    Node curr = root;
	    Stack<Node> stack = new Stack<>();
	    
	    for ( int i = 0; i < word.length(); i++) {
	        Node temp = curr.getChild(word.charAt(i));
	        if ( temp == null ) {
	            return false;
	        }
	        else {
	            stack.push(curr.getChild(word.charAt(i)));
	        }
	        curr = curr.getChild(word.charAt(i));
	    }
	    
	    while ( !stack.isEmpty() ) {
	        Node top = stack.peek();
	        if ( top.endOfWord ) {
	            top.endOfWord = false;
	            return true;
	        }
	        if ( !top.children.isEmpty() ) {
	            top.endOfWord = false;
	            return true;
	        }
	        stack.pop();
	    }
	    
		return true;
	}
	
	/*
	 * Removes all words.
	 * 
	 * COMPLETE
	 */
	public void clear() {
	    root.children.clear();
	}
	
	/*
	 * Returns a map where the key is a letter, and the value
	 * is the number of words in the set that begin with that
	 * letter. The only keys in the map should be the first 
	 * letters of words in the set. For example, if the set
	 * contained {"apple", "cat", "car"} then letterMap() should
	 * return {'a' --> 1, 'c' --> 2}.
	 * Use recursion to build this map.
	 * 
	 * COMPLETE
	 */
	public TreeMap<Character, Integer> letterMap() {
	    TreeMap<Character, Integer> result = new TreeMap<>();
	    
	    for ( Node n: root.children ) {
	        int temp = wordCount(n);
	        result.put(n.letter, temp);
	    }
	    
		return result;
	}
	
	class Node {
		// Don't add any instance variables to the Node class
		char letter;
		HashSet<Node> children; 
		boolean endOfWord;

		Node(char letter) {
			this.letter = letter;
			this.children = new HashSet<>();
			this.endOfWord = false;
		}
		
		// Implementing this could be helpful.
		/*
		 * Returns the child node containing the specified letter,
		 * or null if that child node doesn't exist.
		 */
		public Node getChild(char c) {
			for (Node n : children ) {
			    if (n.letter == c ) {
			        return n;
			    }
			}
			
			return null;
		}
		
		public String toString() {
		    String result = (endOfWord ? Character.toUpperCase(letter) : letter )  
		            + " " + children;
		    result = result.replace("]", "");
		    return result;
		}
		
		// You can also add other helper methods to the node class if you want.
	}

}
