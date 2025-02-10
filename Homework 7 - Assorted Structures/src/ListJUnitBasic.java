import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ListJUnitBasic {
	// This does not test everything.
	// It tests SOME of your methods in your List implementation: both add() methods,
	// both remove() methods, and toArray().
	// It tests NONE of the methods in your Deque, Queue, or Stack implementations

	@Test
	public void testAddString() {
		LinkedList<String> list = new LinkedList<>();

		String[] data = { "a", "b", "c", "d" };
		String result = "";
		for (int i = 0; i < data.length; i++) {
			result += data[i] + " ";
			list.add(data[i]);

			assertEquals(result + "(size=" + (i + 1) + ")", list.toStringNext());
			assertEquals(result + "(size=" + (i + 1) + ")", list.toStringPrev());
		}
	}

	@Test
	public void testAddIntString() {
		// empty list case, adding to position 0 both times
		LinkedList<String> list = new LinkedList<>();
		list.add(0, "a");
		assertEquals("a (size=1)", list.toStringNext());
		assertEquals("a (size=1)", list.toStringPrev());
		list.add(0, "b");
		assertEquals("b a (size=2)", list.toStringNext());
		assertEquals("b a (size=2)", list.toStringPrev());

		// empty list, followed by adding two items to end:
		list = new LinkedList<>();
		list.add(0, "a");
		assertEquals("a (size=1)", list.toStringNext());
		assertEquals("a (size=1)", list.toStringPrev());
		list.add(1, "b");
		assertEquals("a b (size=2)", list.toStringNext());
		assertEquals("a b (size=2)", list.toStringPrev());

		// add to beginning and then to end and then to middle
		list.add(0, "c");
		assertEquals("c a b (size=3)", list.toStringNext());
		assertEquals("c a b (size=3)", list.toStringPrev());
		list.add(3, "d");
		assertEquals("c a b d (size=4)", list.toStringNext());
		assertEquals("c a b d (size=4)", list.toStringPrev());
		list.add(2, "z");
		assertEquals("c a z b d (size=5)", list.toStringNext());
		assertEquals("c a z b d (size=5)", list.toStringPrev());
	}

	@Test
	public void testAddIntStringThrowsCorrectException() {
		// assumes add() is working
		LinkedList<String> list = new LinkedList<>();
		list.add("a");
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(-1, "a");
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(2, "a");
		});

	}

	@Test
	public void testRemove() {
		// Assumes add() is working
		LinkedList<String> list = new LinkedList<>();
		list.add("a");

		assertEquals("a", list.remove(0));
		assertEquals(" (size=0)", list.toStringNext());
		assertEquals(" (size=0)", list.toStringPrev());

		list.add("a");
		list.add("b");
		assertEquals("b", list.remove(1));
		assertEquals("a (size=1)", list.toStringNext());
		assertEquals("a (size=1)", list.toStringPrev());

		assertEquals("a", list.remove(0));
		assertEquals(" (size=0)", list.toStringNext());
		assertEquals(" (size=0)", list.toStringPrev());

		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");

		assertEquals("c", list.remove(2));
		assertEquals("a b d e (size=4)", list.toStringNext());
		assertEquals("a b d e (size=4)", list.toStringPrev());

		assertEquals("d", list.remove(2));
		assertEquals("a b e (size=3)", list.toStringNext());
		assertEquals("a b e (size=3)", list.toStringPrev());

		assertEquals("a", list.remove(0));
		assertEquals("b e (size=2)", list.toStringNext());
		assertEquals("b e (size=2)", list.toStringPrev());

		assertEquals("e", list.remove(1));
		assertEquals("b (size=1)", list.toStringNext());
		assertEquals("b (size=1)", list.toStringPrev());

		assertEquals("b", list.remove(0));
		assertEquals(" (size=0)", list.toStringNext());
		assertEquals(" (size=0)", list.toStringPrev());
	}

	@Test
	public void testRemoveIntThrowsCorrectExceptions() {
		// Assumes add() is working
		LinkedList<String> list = new LinkedList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.remove(-1);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.remove(0);
		});
		list.add("cat");
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.remove(1);
		});
	}

	@Test
	public void testToArray() {
		// Assumes add() is working
		LinkedList<String> list = new LinkedList<>();
		assertArrayEquals(new String[0], list.toArray());

		list.add("a");
		assertArrayEquals(new String[] { "a" }, list.toArray());
		list.add("b");
		assertArrayEquals(new String[] { "a", "b" }, list.toArray());
	}

	

}
