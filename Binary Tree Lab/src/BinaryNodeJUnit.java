import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

public class BinaryNodeJUnit {

	BinaryNode sampleTree, singleNode, rootPlusLeft, rootPlusRight, rootPlusLeftRight, rights, lefts, zigZag,
			complete31, complete32, complete47, repeats, rightsOnlyAll50;

	public void setup() {
		sampleTree = sampleTree();

		singleNode = new BinaryNode(1); // single-node

		rootPlusLeft = new BinaryNode(1); // root + left
		rootPlusLeft.left = new BinaryNode(2);

		rootPlusRight = new BinaryNode(1); // root + right
		rootPlusRight.right = new BinaryNode(2);

		rootPlusLeftRight = new BinaryNode(1); // root + left + right
		rootPlusLeftRight.left = new BinaryNode(2);
		rootPlusLeftRight.right = new BinaryNode(3);

		rights = rightsOnly(5);
		lefts = leftsOnly(9);
		zigZag = zigZag(20);
		complete31 = completeTree(31); // full, balanced, complete
		complete32 = completeTree(32); // full+1: not full, but balanced,
										// complete
		complete47 = completeTree(47); // full+ half of next level: not full,
										// but balanced, complete, left full,
										// right full
		repeats = manyRepeats();

		rightsOnlyAll50 = rightsOnlyAll50(10);
	}

	@Test
	public void testIsBalanced() {
		setup();
		assertTrue(sampleTree.isBalanced());
		assertTrue(singleNode.isBalanced());
		assertTrue(rootPlusLeft.isBalanced());
		assertTrue(rootPlusRight.isBalanced());
		assertTrue(rootPlusLeftRight.isBalanced());

		for (int i = 10; i < 30; i++) {
			assertTrue(completeTree(i).isBalanced());
		}

		BinaryNode root = new BinaryNode(99);
		BinaryNode lefts = leftsOnly(2);
		BinaryNode rights = rightsOnly(2);

		root.left = lefts;
		assertFalse(root.isBalanced());
		root.right = rights;
		assertTrue(root.isBalanced());

		lefts = leftsOnly(3);
		rights = rightsOnly(3);

		root.left = lefts;
		assertFalse(root.isBalanced());
		root.right = rights;
		assertFalse(root.isBalanced());
	}

	@Test
	public void testSize() {
		setup();
		assertEquals(6, sampleTree.size());
		assertEquals(1, singleNode.size());
		assertEquals(2, rootPlusLeft.size());
		assertEquals(2, rootPlusRight.size());
		assertEquals(3, rootPlusLeftRight.size());
		assertEquals(50, completeTree(50).size());
		assertEquals(63, completeTree(63).size());
		assertEquals(64, completeTree(64).size());
		assertEquals(20, zigZag(20).size());
	}

	@Test
	public void testHeight() {
		setup();
		assertEquals(2, sampleTree.height());
		assertEquals(0, singleNode.height());
		assertEquals(1, rootPlusLeft.height());
		assertEquals(1, rootPlusRight.height());
		assertEquals(1, rootPlusLeftRight.height());
		assertEquals(5, completeTree(50).height());
		assertEquals(5, completeTree(63).height());
		assertEquals(6, completeTree(64).height());
		assertEquals(19, zigZag(20).height());
	}

	@Test
	public void testleftMostValue() {
		setup();
		assertEquals(7, sampleTree.leftmostValue());
		assertEquals(1, singleNode.leftmostValue());
		assertEquals(2, rootPlusLeft.leftmostValue());
		assertEquals(1, rootPlusRight.leftmostValue());
		assertEquals(2, rootPlusLeftRight.leftmostValue());
		assertEquals(32, completeTree(50).leftmostValue());
		assertEquals(32, completeTree(63).leftmostValue());
		assertEquals(64, completeTree(64).leftmostValue());
		assertEquals(1, zigZag(20).leftmostValue());
	}

	@Test
	public void testMin() {
		setup();
		assertEquals(7, sampleTree.min());
		assertEquals(1, singleNode.min());
		assertEquals(1, rootPlusLeft.min());
		assertEquals(1, rootPlusRight.min());
		assertEquals(1, rootPlusLeftRight.min());
		assertEquals(1, completeTree(50).min());
		assertEquals(1, zigZag(37).min());

		int rndNegative = (int) (-1E9 * Math.random());
		BinaryNode root = new BinaryNode(rndNegative);
		root.left = new BinaryNode(root.data - (int) (1000 * Math.random()));
		root.right = new BinaryNode(root.left.data - (int) (1 * Math.random()));

		assertEquals(root.right.data, root.min());
	}

	@Test
	public void testLeafCount() {
		setup();
		assertEquals(3, sampleTree.leafCount());
		assertEquals(1, singleNode.leafCount());
		assertEquals(1, rootPlusLeft.leafCount());
		assertEquals(1, rootPlusRight.leafCount());
		assertEquals(2, rootPlusLeftRight.leafCount());
		assertEquals(25, completeTree(50).leafCount());
		assertEquals(32, completeTree(63).leafCount());
		assertEquals(32, completeTree(64).leafCount());
		assertEquals(1, zigZag(20).leafCount());
	}

	@Test
	public void testIsFull() {
		setup();
		assertFalse(sampleTree.isFull());
		assertTrue(singleNode.isFull());
		assertFalse(rootPlusLeft.isFull());
		assertFalse(rootPlusRight.isFull());
		assertFalse(zigZag(3).isFull());
		assertTrue(rootPlusLeftRight.isFull());

		assertTrue(completeTree(3).isFull());
		assertFalse(completeTree(4).isFull());
		assertFalse(completeTree(5).isFull());
		assertFalse(completeTree(32).isFull());
		assertFalse(completeTree(47).isFull());
		assertFalse(completeTree(62).isFull());
	}

	@Test
	public void testPreOrderTraversal() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		sampleTree().preOrderTraversal();
		assertEquals("10 11 7 9 15 8", outContent.toString().trim());

		outContent.reset();

		zigZag(5).preOrderTraversal();
		assertEquals("1 2 3 4 5", outContent.toString().trim());

		System.setOut(originalOut);
	}

	@Test
	public void testPostOrderTraversal() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		sampleTree().postOrderTraversal();
		assertEquals("7 11 15 8 9 10", outContent.toString().trim());

		outContent.reset();

		zigZag(5).postOrderTraversal();
		assertEquals("5 4 3 2 1", outContent.toString().trim());

		System.setOut(originalOut);
	}

	@Test
	public void testInOrderTraversal() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		sampleTree().inOrderTraversal();
		assertEquals("7 11 10 15 9 8", outContent.toString().trim());

		outContent.reset();

		zigZag(5).inOrderTraversal();
		assertEquals("1 3 5 4 2", outContent.toString().trim());

		System.setOut(originalOut);
	}

	@Test
	public void testLevelOrderTraversal() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		sampleTree().levelOrderTraversal();
		assertEquals("10 11 9 7 15 8", outContent.toString().trim());

		outContent.reset();

		zigZag(5).levelOrderTraversal();
		assertEquals("1 2 3 4 5", outContent.toString().trim());

		System.setOut(originalOut);
	}

	@Test
	public void testGetFrequency() {
		setup();
		assertEquals(0, repeats.getFrequency(0));
		assertEquals(3, repeats.getFrequency(10));
		assertEquals(2, repeats.getFrequency(11));
		assertEquals(1, repeats.getFrequency(9));

		BinaryNode b = completeTree(64);
		for (int i = 1; i <= 64; i++) {
			assertEquals(1, b.getFrequency(1));
		}

		for (int i = 1; i <= 20; i++) {
			assertEquals(1, zigZag.getFrequency(1));
		}
		assertEquals(0, zigZag.getFrequency(21));
		assertEquals(1, singleNode.getFrequency(1));
		assertEquals(0, singleNode.getFrequency(2));

		assertEquals(10, rightsOnlyAll50.getFrequency(50));
		assertEquals(0, rightsOnlyAll50.getFrequency(49));
	}

	@Test
	public void testFind() {
		setup();
		assertTrue(singleNode.find(1));
		assertTrue(rootPlusLeft.find(1));
		assertTrue(rootPlusRight.find(1));
		assertTrue(rootPlusLeftRight.find(1));

		assertFalse(singleNode.find(15));
		assertFalse(rootPlusLeft.find(15));
		assertFalse(rootPlusRight.find(15));
		assertFalse(rootPlusLeftRight.find(15));

		assertTrue(repeats.find(11));

		for (int i = 1; i <= 20; i++) {
			assertTrue(zigZag.find(i));
		}
		for (int i = 1; i <= 32; i++) {
			assertTrue(completeTree(32).find(i));
		}
	}

	@Test
	public void testPreOrderString() {
		setup();
		String result = sampleTree.preOrderString();
		assertEquals("10 11 7 9 15 8", result);

		result = singleNode.preOrderString();
		assertEquals("1", result);

		result = rootPlusLeft.preOrderString();
		assertEquals("1 2", result);

		result = rootPlusRight.preOrderString();
		assertEquals("1 2", result);

		result = rootPlusLeftRight.preOrderString();
		assertEquals("1 2 3", result.trim());

		result = completeTree(10).preOrderString();
		assertEquals("1 2 4 8 9 5 10 3 6 7", result);

		result = zigZag(10).preOrderString();
		assertEquals("1 2 3 4 5 6 7 8 9 10", result);
	}

	@Test
	public void testTraverseAndDouble() {
		setup();
		
		rootPlusLeftRight.traverseAndDouble();
		assertEquals(2, rootPlusLeftRight.data);
		assertEquals(4, rootPlusLeftRight.left.data);
		assertEquals(6, rootPlusLeftRight.right.data);
		
		singleNode.traverseAndDouble();
		assertEquals(2, singleNode.data);
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		sampleTree.traverseAndDouble();
		sampleTree.inOrderTraversal();
		assertEquals("14 22 20 30 18 16", outContent.toString().trim());

		outContent.reset();

		BinaryNode zz = zigZag(5);
		zz.traverseAndDouble();
		zz.inOrderTraversal();
		assertEquals("2 6 10 8 4", outContent.toString().trim());

		System.setOut(originalOut);
	}

	@Test
	public void testAllData() {
		setup();
		ArrayList<Integer> result = sampleTree.allData();
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(10);
		expected.add(11);
		expected.add(9);
		expected.add(7);
		expected.add(15);
		expected.add(8);
		assertEquals(expected, result);

		result = singleNode.allData();
		expected = new ArrayList<>();
		expected.add(1);
		assertEquals(expected, result);

		result = rootPlusLeft.allData();
		expected = new ArrayList<>();
		expected.add(1);
		expected.add(2);
		assertEquals(expected, result);

		result = rootPlusRight.allData();
		expected = new ArrayList<>();
		expected.add(1);
		expected.add(2);
		assertEquals(expected, result);

		result = rootPlusLeftRight.allData();
		expected = new ArrayList<>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		assertEquals(expected, result);

		result = rootPlusLeftRight.allData();
		expected = new ArrayList<>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		assertEquals(expected, result);

		result = zigZag(5).allData();
		expected = new ArrayList<>();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		expected.add(4);
		expected.add(5);
		assertEquals(expected, result);

		result = rightsOnlyAll50(7).allData();
		expected = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			expected.add(50);

		}
		assertEquals(expected, result);
	}

	public BinaryNode sampleTree() {
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

	public BinaryNode manyRepeats() {
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

	public BinaryNode rightsOnly(int n) {
		BinaryNode root = new BinaryNode(n);

		for (int i = n - 1; i >= 1; i--) {
			BinaryNode temp = new BinaryNode(i);
			temp.right = root;
			root = temp;
		}

		return root;
	}

	public BinaryNode rightsOnlyAll50(int n) {
		BinaryNode root = new BinaryNode(50);

		for (int i = n - 1; i >= 1; i--) {
			BinaryNode temp = new BinaryNode(50);
			temp.right = root;
			root = temp;
		}

		return root;
	}

	public BinaryNode leftsOnly(int n) {
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
	public BinaryNode zigZag(int n) {
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
	public BinaryNode completeTree(int n) {
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
