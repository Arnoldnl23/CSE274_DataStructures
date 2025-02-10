import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

class BinaryNode {
	int data;
	BinaryNode left, right;

	BinaryNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	// Returns the number of nodes in the binary tree
	// that is rooted at this node. Write recursively.
	int size() {
		int size = 1; //counts the root
		
		//recurse to find the number of nodes in
		// each subtree
		if ( left != null ) {
		    size += left.size();
		}		
		if ( right != null ) {
		    size += right.size();
		}
		
		return size;
	}


	// Returns the smallest value in the tree
	// Write recursively.
	int min() {
		int min = data;
		
		if ( left != null ) {
		    int leftMin = left.min();
		    if ( leftMin < min ) {
		        min = leftMin;
		    }
		}
		
		if ( right != null ) {
		    int rightMin = right.min();
		    if ( rightMin < min ) {
		        min = rightMin;
		    }
		}
		
		return min;
	}

	// Returns true if the key appears in the tree
	boolean find(int key) {
		return false;
	}

	// Prints the pre-order traversal of the tree rooted at this node
	// Space separated. all on one line.
	// Write recursively.
	void preOrderTraversal() {
	    
	    System.out.print(data + " ");
	    
	    if ( left != null ) {
	        left.preOrderTraversal();
	    }
	    
        if ( right != null ) {
            right.preOrderTraversal();
        }
	}

	// Print all the nodes in the tree rooted at this node,
	// using post-order traversal: left subtree, right subtree, then root data
	// Write recursively.
	void postOrderTraversal() {

        if ( left != null ) {
            left.postOrderTraversal();
        }
        
        if ( right != null ) {
            right.postOrderTraversal();
        }
        
        System.out.print(data + " ");       
	}

	// Prints the in-order traversal of the tree rooted at this node
	// Space separated. all on one line
	// Write recursively.
	void inOrderTraversal() {
	    
	    if ( left != null ) {
	        left.inOrderTraversal();
	    }
	    
	    System.out.print(data + " ");
	    
	    if ( right != null ) {
	        right.inOrderTraversal();
	    }
	}

	// Print all the nodes in the tree rooted at this node,
	// using level-order traversal: top-to-bottom, left-to-right
	// Use a queue and a loop to solve this. Recursion doesn't work
	// well here.
	void levelOrderTraversal() {

	}

	// Returns a space-delimited string containing the
	// pre-order traversal, rather than printing it. 
	// Write recursively.
	String preOrderString() {
		return "";
	}

	

}
