import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class QueueJUnitTester {

	private Queue<Integer> createQueue() {
		//return new CircularLinkedQueue<>();
		return new CircularArrayQueue<>();
	}
	
	@Test
	public void testAddBasic() {
		Queue<Integer> q = createQueue();

		for (int i = 0; i < 10; i++) {
			q.add(i);
			assertEquals(0, (int) q.peek());
			assertEquals(0, (int) q.peek());
		}

		assertEquals("front [0 1 2 3 4 5 6 7 8 9] back", q.toString());
	}

	@Test
	public void testAddWithResize() {
		// If q is an array-queue, then this is enough to trigger a resize().
		// But if it's a linked-queue, these are just normal adds.
		Queue<Integer> q = createQueue();
		
		for (int i = 0; i < 13; i++) {
			q.add(i);
			assertEquals(0, (int) q.peek());
		}

		assertEquals("front [0 1 2 3 4 5 6 7 8 9 10 11 12] back", q.toString());
	}

	@Test
	public void testpeek() {
		Queue<Integer> q = createQueue();
		for (int i = 0; i < 9; i++) {
			q.add(i);
			assertEquals(0, (int) q.peek());
		}
	}

	@Test
	public void testPeekFrontThrowsProperException() {
		Queue<Integer> q = createQueue();
		assertThrows(NoSuchElementException.class, () -> {
			q.peek();
		});
	}

	@Test
	public void testRemoveWithNoWraparound() {
		Queue<Integer> q = createQueue();
		
		for (int i = 0; i < 10; i++) {
			q.add(i);
		}

		for (int i = 0; i < 10; i++) {
			assertEquals(i, (int) q.remove());
		}

		assertTrue(q.isEmpty());
	}

	@Test
	public void testRemoveThrowsProperException() {
		Queue<Integer> q = createQueue();
		assertThrows(NoSuchElementException.class, () -> {
			q.remove();
		});
	}

	@Test
	public void testIsEmpty() {
		Queue<Integer> q = createQueue();
		assertTrue(q.isEmpty());
		q.add(99);
		assertFalse(q.isEmpty());
	}

	@Test
	public void testClear() {
		Queue<Integer> q = createQueue();
		for (int i = 0; i < 9; i++) {
			q.add(i);
		}

		assertFalse(q.isEmpty());
		q.clear();
		assertTrue(q.isEmpty());
	}

	@Test
	public void testToStringBasic() {
		Queue<Integer> q = createQueue();
		assertEquals("front [] back", q.toString());

		for (int i = 0; i < 3; i++) {
			q.add(i);
		}

		assertEquals("front [0 1 2] back", q.toString());

		q.remove();
		assertEquals("front [1 2] back", q.toString());
	}

	// This test performs a lot of random adds and removes. It compares the
	// performance of one of Java's queues with your queue
	// and makes sure that the results always match.
	@Test
	public void testResizeAndWrap() {
		java.util.Queue<Integer> qExpected = new java.util.LinkedList<>();
		Queue<Integer> q = createQueue();
		
		// Add enough elements to trigger 1 resize
		for (int i = 0; i < 20; i++) {
			Integer random = (int) (500 * Math.random());
			qExpected.add(random);
			q.add(random);
		}

		// Remove a few elements
		for (int i = 0; i < 4; i++) {
			qExpected.remove();
			q.remove();
		}

		// Add a couple elements to trigger wraparound
		for (int i = 0; i < 2; i++) {
			Integer random = (int) (500 * Math.random());
			qExpected.add(random);
			q.add(random);
		}

		
		// Now just do a lot
		for (int count = 0; count < 1000; count++) {
			if (Math.random() < 0.6 && qExpected.size() > 0) { // remove
				qExpected.remove();
				q.remove();
			} else {
				Integer random = (int) (500 * Math.random());
				qExpected.add(random);
				q.add(random);
			}
		}

		// Now, check queue contents
		while (!qExpected.isEmpty()) {
			assertEquals(qExpected.remove(), q.remove());
		}

		assertTrue(q.isEmpty());
	}

}
