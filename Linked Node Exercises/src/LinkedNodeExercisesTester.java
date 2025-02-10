import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LinkedNodeExercisesTester {

	
	@Test
	void testSumFirstTwo() {
		int[] data = new int[] { 5, 17, 11, 10 };
		Node head = LinkedNodeExercises.buildChain(data);
		assertEquals(22, LinkedNodeExercises.sumFirstTwo(head));
		
		data = new int[] { 3, 5 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(8, LinkedNodeExercises.sumFirstTwo(head));
		
		data = new int[] { 53 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(53, LinkedNodeExercises.sumFirstTwo(head));
	}
	
	
	@Test
	void testBuildChain() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeExercises.buildChain(data);

		Node curr = head;
		for (int n : data) {
			assertEquals(n, curr.data);
			curr = curr.next;
		}

		assertNull(curr);
	}

	@Test
	void testDataToString() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeExercises.buildChain(data);

		assertEquals("5 17 11 10", LinkedNodeExercises.dataToString(head));

		data = new int[] { -9 };
		head = LinkedNodeExercises.buildChain(data);

		assertEquals("-9", LinkedNodeExercises.dataToString(head));
		
		assertEquals("EMPTY", LinkedNodeExercises.dataToString(null));
	}
	
	@Test
	void testSumLastTwo() {
		int[] data = new int[] { 5, 17, 11, 10 };
		Node head = LinkedNodeExercises.buildChain(data);
		assertEquals(21, LinkedNodeExercises.sumLastTwo(head));
		
		data = new int[] { 3, 5 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(8, LinkedNodeExercises.sumLastTwo(head));
		
		data = new int[] { 53 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(53, LinkedNodeExercises.sumLastTwo(head));
	}

	@Test
	void testCount() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeExercises.buildChain(data);

		assertEquals(4, LinkedNodeExercises.count(head));
		assertEquals(3, LinkedNodeExercises.count(head.next));
		assertEquals(2, LinkedNodeExercises.count(head.next.next));
		assertEquals(1, LinkedNodeExercises.count(head.next.next.next));
		assertEquals(0, LinkedNodeExercises.count(null));
	}
	
	@Test
	void testSum() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeExercises.buildChain(data);

		assertEquals(43, LinkedNodeExercises.sum(head));
		assertEquals(38, LinkedNodeExercises.sum(head.next));
		assertEquals(21, LinkedNodeExercises.sum(head.next.next));
		assertEquals(10, LinkedNodeExercises.sum(head.next.next.next));
		assertEquals(0, LinkedNodeExercises.sum(null));
	}

	@Test
	void testSwapEnds() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeExercises.buildChain(data);
		LinkedNodeExercises.swapEnds(head);

		assertEquals("10 17 11 5", LinkedNodeExercises.dataToString(head));

		data = new int[] { 42 };
		head = LinkedNodeExercises.buildChain(data);
		LinkedNodeExercises.swapEnds(head);

		assertEquals("42", LinkedNodeExercises.dataToString(head));
	}

	@Test
	void testContains() {
		int[] data1 = { 3, 9, 2, 3, 3, 4, 5, 6, 8, 13 };
		int[] data2 = { 13 };
		Node head1 = LinkedNodeExercises.buildChain(data1);
		Node head2 = LinkedNodeExercises.buildChain(data2);

		assertTrue(LinkedNodeExercises.contains(head1, 3));
		assertTrue(LinkedNodeExercises.contains(head1, 13));
		assertTrue(LinkedNodeExercises.contains(head1, 4));
		assertTrue(LinkedNodeExercises.contains(head1, 2));
		assertFalse(LinkedNodeExercises.contains(head1, 99));
		
		assertTrue(LinkedNodeExercises.contains(head2, 13));
		assertFalse(LinkedNodeExercises.contains(head2, -13));
		assertFalse(LinkedNodeExercises.contains(null, -13));
	}
	
	@Test
	void testIndexOf() {
		int[] data1 = { 3, 9, 2, 3, 3, 4, 5, 6, 8, 13 };
		int[] data2 = { 13 };
		Node head1 = LinkedNodeExercises.buildChain(data1);
		Node head2 = LinkedNodeExercises.buildChain(data2);

		assertEquals(1, LinkedNodeExercises.indexOf(head1, 9));
		assertEquals(0, LinkedNodeExercises.indexOf(head1, 3));
		assertEquals(9, LinkedNodeExercises.indexOf(head1, 13));
		assertEquals(8, LinkedNodeExercises.indexOf(head1, 8));
		assertEquals(-1, LinkedNodeExercises.indexOf(head1, 20));
		
		assertEquals(-1, LinkedNodeExercises.indexOf(head2, -13));
		assertEquals(0, LinkedNodeExercises.indexOf(head2, 13));
		
		assertEquals(-1, LinkedNodeExercises.indexOf(null, 13));
	}
	
	@Test
	void testGetFrequency() {
		int[] data1 = { 3, 9, 2, 3, 3, 4, 5, 6, 3, 3 };
		int[] data2 = { -13 };
		Node head1 = LinkedNodeExercises.buildChain(data1);
		Node head2 = LinkedNodeExercises.buildChain(data2);

		assertEquals(1, LinkedNodeExercises.getFrequency(head1, 9));
		assertEquals(5, LinkedNodeExercises.getFrequency(head1, 3));
		assertEquals(0, LinkedNodeExercises.getFrequency(head1, 99));

		assertEquals(1, LinkedNodeExercises.getFrequency(head2, -13));
		assertEquals(0, LinkedNodeExercises.getFrequency(head2, 9));
		
		assertEquals(0, LinkedNodeExercises.getFrequency(null, 9));
	}

	@Test
	void testAppend() {
		int[] data1 = { 5, 6, 7 };
		int[] data2 = { 4, 3 };
		int[] data3 = { 6 };
		int[] data4 = { 15, 22, 11, 86 };
		int[] data5 = { 15, 22, 11, 86 };
		int[] data6 = { 6 };

		Node head1 = LinkedNodeExercises.buildChain(data1);
		Node head2 = LinkedNodeExercises.buildChain(data2);
		Node head3 = LinkedNodeExercises.buildChain(data3);
		Node head4 = LinkedNodeExercises.buildChain(data4);
		Node head5 = LinkedNodeExercises.buildChain(data5);
		Node head6 = LinkedNodeExercises.buildChain(data6);

		LinkedNodeExercises.append(head1, head2);
		LinkedNodeExercises.append(head3, head4);
		LinkedNodeExercises.append(head5, head6);

		assertEquals("5 6 7 4 3", LinkedNodeExercises.dataToString(head1));
		assertEquals("6 15 22 11 86", LinkedNodeExercises.dataToString(head3));
		assertEquals("15 22 11 86 6", LinkedNodeExercises.dataToString(head5));

	}

	@Test
	void testGetLast() {
		int[] data = { 5 };
		Node head = LinkedNodeExercises.buildChain(data);
		assertEquals(5, LinkedNodeExercises.getLast(head));

		data = new int[] { 8, 6, 7, 5, 3, 0, 9 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(9, LinkedNodeExercises.getLast(head));
	}

	@Test
	void testRemoveLast() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeExercises.buildChain(data);
		LinkedNodeExercises.removeLast(head);
		assertEquals("8 6 7 5 3 0", LinkedNodeExercises.dataToString(head));
		LinkedNodeExercises.removeLast(head);
		assertEquals("8 6 7 5 3", LinkedNodeExercises.dataToString(head));
		LinkedNodeExercises.removeLast(head);
		assertEquals("8 6 7 5", LinkedNodeExercises.dataToString(head));
		LinkedNodeExercises.removeLast(head);
		assertEquals("8 6 7", LinkedNodeExercises.dataToString(head));
		LinkedNodeExercises.removeLast(head);
		assertEquals("8 6", LinkedNodeExercises.dataToString(head));
		LinkedNodeExercises.removeLast(head);
		assertEquals("8", LinkedNodeExercises.dataToString(head));
	}

	@Test
	void testRemoveFirst() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirst(head);
		assertEquals("6 7 5 3 0 9", LinkedNodeExercises.dataToString(head));
		head = LinkedNodeExercises.removeFirst(head);
		assertEquals("7 5 3 0 9", LinkedNodeExercises.dataToString(head));
		head = LinkedNodeExercises.removeFirst(head);
		assertEquals("5 3 0 9", LinkedNodeExercises.dataToString(head));
		head = LinkedNodeExercises.removeFirst(head);
		assertEquals("3 0 9", LinkedNodeExercises.dataToString(head));
		head = LinkedNodeExercises.removeFirst(head);
		assertEquals("0 9", LinkedNodeExercises.dataToString(head));
		head = LinkedNodeExercises.removeFirst(head);
		assertEquals("9", LinkedNodeExercises.dataToString(head));
		head = LinkedNodeExercises.removeFirst(head);
		assertEquals("EMPTY", LinkedNodeExercises.dataToString(head));
		assertNull(head);
	}

	@Test
	void testHas10SumAdjacent() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAdjacent(head));
		
		data = new int[] { 8, 2, 7, 5, 3, 0, 9 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAdjacent(head));
		
		data = new int[] { 8, 6, 7, 3, 5, 0, 9 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAdjacent(head));
		
		data = new int[] { 8, 6, 7, 5, 3, 1, 9 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAdjacent(head));
		
		data = new int[] { 11, -1 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAdjacent(head));
		
		data = new int[] { 11, 1 };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAdjacent(head));
		
		data = new int[] { 8 };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAdjacent(head));
		
		data = new int[] { 10 };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAdjacent(head));
		
		data = new int[] { };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAdjacent(head));	
	}
	
	@Test
	void testHas10SumAnywhere() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 8, 2, 1, 1, 1, 1};
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 3, 4, 5, 0, 10 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 2, 7, 6, 7, 6, 8 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 7, 2, 6, 7, 6, 8 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 11, -1 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 11, 0, -1 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 11, 0, 1 };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 3, 5, 8 };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 5, 3, 5 };
		head = LinkedNodeExercises.buildChain(data);
		assertTrue(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 11, 1 };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 8 };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { 10 };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAnywhere(head));
		
		data = new int[] { };
		head = LinkedNodeExercises.buildChain(data);
		assertFalse(LinkedNodeExercises.has10SumAnywhere(head));	
	}

	@Test
	void testSumBefore10() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9 };
		Node head = LinkedNodeExercises.buildChain(data);
		assertEquals(0, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] { 10 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(0, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] { 1, 10, 2, 10, 3, 10 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(6, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] { 10, 2, 10, 3, 10, 4 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(5, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] { 10, 10, 10, 10, 10 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(40, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] { 8, 10 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(8, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] { 10, 8 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(0, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] { 8 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(0, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] { 10 };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(0, LinkedNodeExercises.sumBefore10(head));
		
		data = new int[] {  };
		head = LinkedNodeExercises.buildChain(data);
		assertEquals(0, LinkedNodeExercises.sumBefore10(head));
	}
	
	@Test
	void testToArray() {
		int[] data = { 5, 17, 11, 10 };
		Node head = LinkedNodeExercises.buildChain(data);
		assertArrayEquals(data, LinkedNodeExercises.toArray(head));

		data = new int[] { -9 };
		head = LinkedNodeExercises.buildChain(data);
		assertArrayEquals(data, LinkedNodeExercises.toArray(head));
		
		data = new int[] {  };
		head = LinkedNodeExercises.buildChain(data);
		assertArrayEquals(data, LinkedNodeExercises.toArray(head));
	}

	@Test
	void testRemoveFirstOccurrence() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9, 7, 99 };
		Node head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirstOccurrence(head, 7);		
		assertEquals("8 6 5 3 0 9 7 99", LinkedNodeExercises.dataToString(head));
		
		head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirstOccurrence(head, 4);		
		assertEquals("8 6 7 5 3 0 9 7 99", LinkedNodeExercises.dataToString(head));
		
		head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirstOccurrence(head, 8);		
		assertEquals("6 7 5 3 0 9 7 99", LinkedNodeExercises.dataToString(head));
		
		head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirstOccurrence(head, 99);		
		assertEquals("8 6 7 5 3 0 9 7", LinkedNodeExercises.dataToString(head));
		
		data = new int[] { 7, 11 };
		head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirstOccurrence(head, 7);		
		assertEquals("11", LinkedNodeExercises.dataToString(head));
		
		head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirstOccurrence(head, 11);		
		assertEquals("7", LinkedNodeExercises.dataToString(head));
		
		head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirstOccurrence(head, 8);		
		assertEquals("7 11", LinkedNodeExercises.dataToString(head));
		
		data = new int[] { 8 };
		head = LinkedNodeExercises.buildChain(data);
		head = LinkedNodeExercises.removeFirstOccurrence(head, 8);		
		assertEquals("EMPTY", LinkedNodeExercises.dataToString(head));
		
		head = LinkedNodeExercises.removeFirstOccurrence(head, 8);		
		assertEquals("EMPTY", LinkedNodeExercises.dataToString(head));
	}
	
	@Test
	void testJustTheEvens() {
		int[] data = { 8, 6, 7, 5, 3, 0, 9, 7, 99, 4 };
		Node head = LinkedNodeExercises.buildChain(data);
		Node result = LinkedNodeExercises.justTheEvens(head);	
		assertEquals("8 6 7 5 3 0 9 7 99 4", LinkedNodeExercises.dataToString(head));
		assertEquals("8 6 0 4", LinkedNodeExercises.dataToString(result));	
		
		data = new int[] { 42 };
		head = LinkedNodeExercises.buildChain(data);
		result = LinkedNodeExercises.justTheEvens(head);	
		assertEquals("42", LinkedNodeExercises.dataToString(head));
		assertEquals("42", LinkedNodeExercises.dataToString(result));	
		
		data = new int[] { 43 };
		head = LinkedNodeExercises.buildChain(data);
		result = LinkedNodeExercises.justTheEvens(head);	
		assertEquals("43", LinkedNodeExercises.dataToString(head));
		assertEquals("EMPTY", LinkedNodeExercises.dataToString(result));	
		
		
		data = new int[] {  };
		head = LinkedNodeExercises.buildChain(data);
		result = LinkedNodeExercises.justTheEvens(head);	
		assertEquals("EMPTY", LinkedNodeExercises.dataToString(head));
		assertEquals("EMPTY", LinkedNodeExercises.dataToString(result));	
		
		
		data = new int[] {1, 3, 5, 7, 9};
		head = LinkedNodeExercises.buildChain(data);
		result = LinkedNodeExercises.justTheEvens(head);	
		assertEquals("1 3 5 7 9", LinkedNodeExercises.dataToString(head));
		assertEquals("EMPTY", LinkedNodeExercises.dataToString(result));	
	}

}
