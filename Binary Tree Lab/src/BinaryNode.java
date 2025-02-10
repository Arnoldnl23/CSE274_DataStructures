import java.util.ArrayList;
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

	// Solved in class already
	// Returns the number of nodes in the binary tree
	// that is rooted at this node. Write recursively.
	int size() {
		int size = 1;

		if (left != null) {
			size += left.size();
		}
		if (right != null) {
			size += right.size();
		}

		return size;
	}

	// returns the height of the tree. If the tree has only
	// one node, its height is 0.
	// Write recursively.
	int height() {
	    int height;
	    
	    int leftHeight = -1;
	    if ( left != null ) {
	        leftHeight = left.height();
	    }
	    
	    int rightHeight = -1;
	    if ( right != null ) {
	        rightHeight = right.height();
	    }
	    
	    if (leftHeight > rightHeight) {
	        height = leftHeight + 1;
	    }
	    else {
	        height = rightHeight + 1;
	    }
	    
		return height;
	}

	// returns the sum of the tree's data.
	// Write recursively.
	int sum() {
		int sum = data;
		
		if ( left != null ) {
		    sum += left.sum();
		}
		
		if ( right != null ) {
		    sum += right.sum();
		}
		
		return sum;
	}

	// Returns the number of leaves in the tree rooted at this node
	// Write recursively.
	int leafCount() {
		int leaves = 0;
		if ( left != null ) {
		    leaves += left.leafCount();
		}
		if ( right != null ) {
		    leaves += right.leafCount();
		}
		if (right == null && left == null) {
		    return 1;
		}
		
		return leaves;
	}

	// Returns true if this tree is full, and false otherwise
	// A tree with exactly one node is full.
	boolean isFull() {
		return size() == Math.pow(2, height() + 1) - 1;
	}

	// Returns true if this tree is balanced, and false otherwise
	// Write recursively.
	boolean isBalanced() {
	    
	    int leftHeight = -1;
	    if ( left != null ) {
	        leftHeight = left.height();
	    }
	    
	    int rightHeight = -1;
	    if ( right != null) {
	        rightHeight = right.height();
	    }
	    
	    if ( Math.abs(leftHeight - rightHeight) <= 1) {
	        return true;
	    }
	    
	    if ( left != null) {
	        if ( !left.isBalanced( ) ) {
	            return false;
	        }
	    }
	    
	    if ( right != null ) {
	        if ( !right.isBalanced() ) {
	            return false;
	        }
	    }
	    
	    return false;
	}

	// Solved in class already
	// Returns the smallest value in the tree
	// Write recursively.
	int min() {
		int min = this.data;

		if (left != null) {
			int leftMin = left.min();
			if (leftMin < min) {
				min = leftMin;
			}
		}
		if (right != null) {
			int rightMin = right.min();
			if (rightMin < min) {
				min = rightMin;
			}
		}

		return min;
	}

	// Return true if a value is in the tree, and false otherwise
	// Write recursively (which could include simply calling another method
	// that you know is recursive).
	boolean find(int key) {
		return getFrequency(key) != 0;
	}

	// Returns the leftmost value in the tree. That is: start at
	// the root and travel left until you can travel no more. Return
	// the value in the last node you reach (which could turn out to be
	// the value in the root node). This can be done recursively, but
	// a loop works well too.
	int leftmostValue() {
		BinaryNode curr = this;
		while ( curr.left != null) {
		    curr = curr.left;
		}
		
		return curr.data;
	}

	// Returns a count of the number of times that the given
	// value appears in the tree rooted at this node.
	// Write recursively.
	int getFrequency(int key) {
		int freq = 0;
		
		if ( data == key ) {
		    freq++;
		}
		if ( left != null ) {
		    freq += left.getFrequency(key);
		}
		if ( right != null ) {
		    freq += right.getFrequency(key);
		}
		
		return freq;
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

	// Solved in class already
	// Prints the in-order traversal of the tree rooted at this node
	// Space separated. all on one line
	// Write recursively.
	void inOrderTraversal() {
		if (left != null) {
			left.inOrderTraversal();
		}
		System.out.print(data + " ");
		if (right != null) {
			right.inOrderTraversal();
		}
	}

	// Print all the nodes in the tree rooted at this node,
	// using level-order traversal: top-to-bottom, left-to-right
	// Use a queue and a loop to solve this. Recursion doesn't work
	// well here.
	void levelOrderTraversal() {
		Queue<BinaryNode> q = new LinkedList<>();
		q.add(this);
		
		while (!q.isEmpty()) {
		    BinaryNode curr = q.remove();
		    System.out.print(curr.data + " ");
		    if ( curr.left != null ) {
		        q.add(curr.left);
		    }
		    if ( curr.right != null ) {
		        q.add(curr.right);
		    }
		}
	}
	
	///////////////////////////////////////////////////////////////////
	// These next methods are variations of the above,
	// but are not void.
	///////////////////////////////////////////////////////////////////

	// Returns a space-delimited string containing the
	// pre-order traversal, rather than printing it. The
	// returned string should have no leading or trailing spaces.
	String preOrderString() {
	    String result = "";
	    
        result = result + data + " ";        
        if ( left != null ) {
            result += left.preOrderString() + " ";
        }        
        if ( right != null ) {
            result += right.preOrderString() + " ";
        }
	    
		return result.trim();
	}
	
	// Double the value in each node. This method traverses all the
	// nodes, doubling each value.
	void traverseAndDouble() {
        Queue<BinaryNode> q = new LinkedList<>();
        q.add(this);
        
        while (!q.isEmpty()) {
            BinaryNode curr = q.remove();
            curr.data *= 2;
            if ( curr.left != null ) {
                q.add(curr.left);
            }
            if ( curr.right != null ) {
                q.add(curr.right);
            }
        }
	}

	// Returns an ArrayList of all the data in this tree, in level-order.
	ArrayList<Integer> allData() {
        Queue<BinaryNode> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        q.add(this);
        
        while (!q.isEmpty()) {
            BinaryNode curr = q.remove();
            result.add(curr.data);
            if ( curr.left != null ) {
                q.add(curr.left);
            }
            if ( curr.right != null ) {
                q.add(curr.right);
            }
        }
        
        return result;
	}

}
