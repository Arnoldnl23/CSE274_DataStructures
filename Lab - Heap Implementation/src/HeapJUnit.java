import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HeapJUnit {

	Integer[] data = { 47, 15, 12, 6, 90, 9, 85, 44, 25, 201, 3, 65, 64, 66, 1, 87 };
	Integer[] smallArray = { 2, 20, 16, 4, 1 };

	@Test
	public void testHeap() {
		Heap queue = new Heap();
		assertEquals("[null, null, null, null, null, null, null, null, null, null], size=0", queue.toString());
	}

	@Test
	public void testHeapInt() {
		Heap queue = new Heap(3);
		assertEquals("[null, null, null], size=0", queue.toString());
	}

	@Test
	public void testHeapArray() {

		Heap queue = new Heap(smallArray);
		assertEquals("[null, 1, 2, 16, 4, 20], size=5", queue.toString());
	}

	@Test
	public void testAdd() {
		Heap queue = new Heap();
		queue.add(2);

		assertEquals("[null, 2, null, null, null, null, null, null, null, null], size=1", queue.toString());
		queue.add(20);

		assertEquals("[null, 2, 20, null, null, null, null, null, null, null], size=2", queue.toString());
		queue.add(16);

		assertEquals("[null, 2, 20, 16, null, null, null, null, null, null], size=3", queue.toString());
		queue.add(1);

		assertEquals("[null, 1, 2, 16, 20, null, null, null, null, null], size=4", queue.toString());
		queue.add(4);
		assertEquals("[null, 1, 2, 16, 20, 4, null, null, null, null], size=5", queue.toString());
	}

	@Test
	public void testRemove() {
		Heap queue = new Heap();
		assertNull(queue.remove());

		List<Integer> sorted = new ArrayList<>();

		for (Integer n : data) {
			queue.add(n);
			sorted.add(n);
		}
		
		Collections.sort(sorted);

		for (Integer n : sorted) {
			assertEquals(n, queue.remove());
		}
	}

	@Test
	public void testAddRemoveOneItem() {
		Heap queue = new Heap(4);
		assertNull(queue.peek());
		assertNull(queue.remove());

		queue.add(16);
		assertEquals("[null, 16, null, null], size=1", queue.toString());
		assertFalse(queue.isEmpty());
		assertEquals(1, queue.size());
		assertEquals(16, (int) queue.peek());

		assertEquals(16, (int) queue.remove());
		assertEquals("[null, null, null, null], size=0", queue.toString());
		assertNull(queue.remove());
		assertNull(queue.peek());
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
	}

	@Test
	public void testPeek() {
		Heap queue = new Heap();
		assertNull(queue.peek());

		Integer[] intsSorted = { 10, 8, 6, 4, 2, 1 };

		for (Integer n : intsSorted) {
			queue.add(n);
			assertEquals(n, queue.peek());
		}

		queue.add(1000);

		assertEquals(1, (int) queue.peek());
	}

	@Test
	public void testSize() {
		Heap queue = new Heap();
		for (int i = 0; i < data.length; i++) {
			assertEquals(i, queue.size());
			queue.add(data[i]);
		}

		for (int i = 0; i < data.length; i++) {
			assertEquals(data.length - i, queue.size());
			queue.remove();
		}

	}

}
