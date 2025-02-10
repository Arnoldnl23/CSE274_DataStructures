import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBasics {

	/*
	 * For the lab: THIS_TEST_MUST_PASS must pass
	 */
	
	/*
	 * For the homework: 
	 * 1. THIS_TEST_MUST_PASS must pass (if it doesn't, your
	 *    highest score on the homework would be 10 points out of 50 points).
	 * 2. All other tests in this JUnit test file must pass.
	 * 3. All other methods in HashSet must pass. Some will require that 
	 *    you do your own testing: addAll(), intersection(), union(), and
	 *    equalSets(). You can do your testing in the main() method or by
	 *    writing additional JUnit tests. You are not submitting your tester. 
	 */
	
	@Test
	public void THIS_TEST_MUST_PASS() {
		testHashSetConstructor();
		testAdd();
		testToString();
		testContains();
		testGetCurrentLoad();
		testSize();
		testIsEmpty();
		testHashSetInt();
		testHashSetIntThrowsProperException();
	}

	// makes sure your hash set is correctly created
	@Test
	public void testHashSetConstructor() {
		HashSet s = new HashSet();
		assertEquals(0, s.size());
		s.add("a");
	}

	// Tests adding but does not check whether resizing
	// happens.
	@Test
	public void testAdd() {
		HashSet s = new HashSet();

		assertTrue(s.add("A"));
		assertEquals(1, s.size());
		// duplicate
		assertFalse(s.add("A"));
		assertEquals(1, s.size());
		// these should both work
		assertTrue(s.add("C"));
		assertTrue(s.add("D"));
		assertEquals(3, s.size());
	}

	@Test
	public void testToString() {
		HashSet s = new HashSet();
		assertEquals("", s.toString(), "toString() should return empty string");

		String[] toBeAdded = { "1", "2", "3" };

		for (String str : toBeAdded) {
			s.add(str);
		}

		assertEquals("1 2 3", s.toString());
	}

	@Test
	public void testContains() {
		HashSet s = new HashSet();
		assertFalse(s.contains(new String("A")));
		s.add("A");
		assertTrue(s.contains(new String("A")));
		s.add("H");
		assertTrue(s.contains(new String("A")));
		assertTrue(s.contains(new String("H")));
		assertFalse(s.contains(new String("B")));
	}

	@Test
	public void testGetCurrentLoad() {
		// When comparing doubles for equality, we need
		// to allow for a certain amount of rounding error.
		double epsilon = 0.0001;

		HashSet s = new HashSet();
		assertEquals(0.0, s.getCurrentLoad(), epsilon);

		s.add("A");
		assertEquals(1.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("B");
		assertEquals(2.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("C");
		assertEquals(3.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("D");
		assertEquals(4.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("E");
		assertEquals(5.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("F");
		assertEquals(6.0 / 11.0, s.getCurrentLoad(), epsilon);
		s.add("G");
		assertEquals(7.0 / 11.0, s.getCurrentLoad(), epsilon);
	}

	@Test
	public void testSize() {
		HashSet s = new HashSet();
		// size of an empty set
		assertEquals(0, s.size());
		// add 4 items
		for (int i = 1; i <= 4; i++) {
			s.add("" + i);
			assertEquals(i, s.size());
		}
	}

	// tests both an empty and non-empty set
	@Test
	public void testIsEmpty() {
		HashSet s = new HashSet();
		assertTrue(s.isEmpty());
		s.add("A");
		assertFalse(s.isEmpty());
	}

	@Test
	public void testHashSetInt() {
		HashSet s = new HashSet(5);
		assertEquals(0, s.size());
		s.add("a");
		assertEquals(1.0 / 5.0, s.getCurrentLoad(), 0.0001);
	}

	/*
	 * JUnit lets us test whether code throws exceptions correctly. In HashSet, we
	 * want the number of buckets to be a prime number. Creating a HashSet with a
	 * non- prime should throw an IllegalArgumentException. This test below will
	 * PASS if the correct exception is thrown.
	 */
	@Test
	public void testHashSetIntThrowsProperException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new HashSet(10);
		});
	}

	
	/*************************************************************
	 These remaining tests must pass for the homework (but not the
	 lab). 
	 And you will have to do your own testing of addAll(), union(), 
	 intersection(), and equalSets().
	 ************************************************************/
	
	@Test
	public void testAddWithResize() {
		HashSet s = new HashSet();

		// These adds won't trigger resizing
		for (int i = 1; i <= 7; i++) {
			assertTrue(s.add("" + i));
			assertFalse(s.add("" + i));
			assertEquals(i, s.size());
			assertTrue(s.contains(new String("" + i)));
		}

		assertEquals("7 1 2 3 4 5 6", s.toString());
		assertEquals(7.0 / 11.0, s.getCurrentLoad(), 0.0001);

		// the load now exceeds the max load factor, and so
		// before we perform the next add, a resizing should
		// happen and then the item should be added.

		// This will trigger one resizing. Array length
		// should now be 23.
		String word = "pencil";
		assertTrue(s.add(word));
		assertFalse(s.add(word));
		assertEquals(8, s.size());
		assertTrue(s.contains(new String(word)));

		assertEquals("1 2 3 4 5 6 7 pencil", s.toString());
		assertEquals(8.0 / 23.0, s.getCurrentLoad(), 0.0001);

		// This will trigger two more resizings
		for (int i = 8; i <= 50; i++) {
			assertTrue(s.add("" + i));
			assertFalse(s.add("" + i));
			assertEquals(i + 1, s.size());
			assertTrue(s.contains(new String("" + i)));
		}

	}

	/*
	 * Tests whether remove() is removing the first string it finds by traversing
	 * the array until it finds the first non-empty bucket
	 */
	@Test
	public void testRemove() {
		HashSet s = new HashSet();
		String[] toBeAdded = { "cat", "dog", "house", "mouse", "tree", "ant", "book" };

		for (String str : toBeAdded) {
			s.add(str);
		}

		String[] result = { "house", "dog", "book", "mouse", "ant", "tree", "cat" };
		for (int i = 0; i < result.length; i++) {
			assertEquals(result[i], s.remove());
			assertEquals(6 - i, s.size());
		}

		// Now set is empty. remove() should return null:
		assertNull(s.remove());
		assertEquals(0, s.size());
	}

	@Test
	public void testRemoveString() {
		HashSet s = new HashSet();

		for (int i = 1; i <= 8; i++) {
			s.add("" + i);
			assertEquals(i, s.size());
		}

		int size = s.size();

		// Remove the odds
		for (int i = 1; i <= 7; i += 2) {
			assertTrue(s.remove(new String("" + i)));
			size--;
			assertFalse(s.contains(new String("" + i)));
			assertEquals(size, s.size());
			assertFalse(s.remove(new String("" + i)));
			assertFalse(s.contains(new String("" + i)));
			assertEquals(size, s.size());
		}

		// Remove the evens, backwards
		for (int i = 8; i >= 2; i -= 2) {
			assertTrue(s.remove(new String("" + i)));
			size--;
			assertFalse(s.contains(new String("" + i)));
			assertEquals(size, s.size());
			assertFalse(s.remove(new String("" + i)));
			assertFalse(s.contains(new String("" + i)));
			assertEquals(size, s.size());
		}

		assertTrue(s.isEmpty());
		assertEquals(0, s.size());
		assertFalse(s.remove("2"));

		assertTrue(s.isEmpty());
		assertEquals(0, s.size());

		assertTrue(s.add("2"));
		assertTrue(s.remove("2"));

		assertTrue(s.isEmpty());
		assertEquals(0, s.size());
	}

	@Test
	public void testClear() {
		HashSet s = new HashSet();

		s.add("A");
		s.add("B");
		s.add("C");

		assertEquals(3, s.size());
		assertFalse(s.isEmpty());
		assertTrue(s.contains("A"));
		assertTrue(s.contains("B"));
		assertTrue(s.contains("C"));

		s.clear();
		assertEquals(0, s.size());
		assertTrue(s.isEmpty());
		assertFalse(s.contains("A"));
		assertFalse(s.contains("B"));
		assertFalse(s.contains("C"));
	}

	@Test
	public void testToArray() {
		HashSet s = new HashSet();
		assertArrayEquals(new String[0], s.toArray(), "empty set should give empty array");

		String[] toBeAdded = { "cat", "dog", "house", "mouse", "tree", "ant", "book" };
		String[] result1 = { "house", "dog", "book", "mouse", "ant", "tree", "cat" };

		for (String str : toBeAdded) {
			s.add(str);
		}

		assertArrayEquals(result1, s.toArray());
	}

	@Test
	public void testResizing() {
		HashSet s = new HashSet();
		String[] toBeAdded = { "cat", "dog", "house", "mouse", "tree", "ant", "book", "spaghetti" };
		String[] result2 = { "spaghetti", "tree", "ant", "cat", "dog", "mouse", "book", "house" };

		for (String str : toBeAdded) {
			s.add(str);
		}

		assertArrayEquals(result2, s.toArray());
	}
}
