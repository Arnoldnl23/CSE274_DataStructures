import java.util.LinkedList;
import java.util.Queue;

public class MyTester {

	public static void main(String[] args) {
		
		BinaryNode root = sampleTree();
		
		root.inOrderTraversal(); //7 11 10 15 9 8
		System.out.println();
		
		root.preOrderTraversal(); //10 11 7 9 15 8
		System.out.println();
		
		root.postOrderTraversal(); //7 11 15 8 9 10
		System.out.println();
		
		System.out.println(root.size());
		System.out.println(root.min());
		
		//zigZag(5).inOrderTraversal();
		
	}

	// These will build the various sample trees shown in the slides
	// You should also be testing your code with edge cases.

	// This builds the sample binary tree we built in class
	public static BinaryNode sampleTree() {
		BinaryNode root = new BinaryNode(10); // root

		// Build left subtree:
		BinaryNode leftST = new BinaryNode(11);
		leftST.left = new BinaryNode(7);

		// Build right subtree:
		BinaryNode rightST = new BinaryNode(9);
		rightST.left = new BinaryNode(15);
		rightST.right = new BinaryNode(8);

		root.left = leftST;
		root.right = rightST;

		return root;
	}

	// Same structure as the sample tree, but with repeats 
	// in the data
	public static BinaryNode manyRepeats() {
		BinaryNode root = new BinaryNode(10); // root

		// Build left subtree:
		BinaryNode leftST = new BinaryNode(11);
		leftST.left = new BinaryNode(10);

		// Build right subtree:
		BinaryNode rightST = new BinaryNode(9);
		rightST.left = new BinaryNode(10);
		rightST.right = new BinaryNode(11);

		root.left = leftST;
		root.right = rightST;

		return root;
	}

	// Builds a tree that only branches to the right
	public static BinaryNode rightsOnly(int n) {
		BinaryNode root = new BinaryNode(n);

		for (int i = n - 1; i >= 1; i--) {
			BinaryNode temp = new BinaryNode(i);
			temp.right = root;
			root = temp;
		}

		return root;
	}

	// Builds a tree that only branches to the left
	public static BinaryNode leftsOnly(int n) {
		BinaryNode root = new BinaryNode(n);

		for (int i = n - 1; i >= 1; i--) {
			BinaryNode temp = new BinaryNode(i);
			temp.left = root;
			root = temp;
		}

		return root;
	}

	// A zig-zagging binary tree, with 1 at the top, 2 to the right of 1
	// 3 to the left of 2, etc.
	public static BinaryNode zigZag(int n) {
		BinaryNode root = new BinaryNode(1);
		BinaryNode curr = root;

		for (int i = 2; i <= n; i++) {
			BinaryNode temp = new BinaryNode(i);
			if (i % 2 == 0)
				curr.right = temp;
			else
				curr.left = temp;
			curr = temp;
		}

		return root;
	}

	// A complete binary tree, with level-order traversal
	// producing 1, 2, 3, 4, 5, 6, ... , n.
	// Assumes n >= 1
	// If you want a full tree, just choose appropriate values
	// for n. for example completeTree(7) is a full tree.
	public static BinaryNode completeTree(int n) {
		BinaryNode root = new BinaryNode(1);

		// Nodes get in line to have children attached
		Queue<BinaryNode> q = new LinkedList<>();
		q.add(root);

		int i = 2;

		while (i <= n) {
			BinaryNode temp = new BinaryNode(i);
			BinaryNode front = q.peek();
			if (front.left == null)
				front.left = temp;
			else {
				front.right = temp;
				q.remove();
				q.add(front.left);
				q.add(front.right);
			}
			i++;
		}

		return root;
	}

}
