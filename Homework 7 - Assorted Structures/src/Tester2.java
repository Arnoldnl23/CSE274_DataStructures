import org.junit.*;
import org.junit.runner.RunWith;

import mu.grading.*;
import static mu.grading.Assert.*;

import java.awt.Point;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

@MUTests
@Reporting(lowDetails = true, medDetails = false, highDetails = true)
@TestCategory(name = "listAddAtIndex", points = 6.0)
@TestCategory(name = "listAddAtEnd", points = 2.0)

@TestCategory(name = "listRemoveAtIndex", points = 7.0)
@TestCategory(name = "listRemoveItem", points = 3.0)
@TestCategory(name = "listGet", points = 3.0)
@TestCategory(name = "listSet", points = 3.0)
@TestCategory(name = "listIndexOf", points = 7.0)
@TestCategory(name = "listContains", points = 3.0)
@TestCategory(name = "listToArray", points = 2.0)
@TestCategory(name = "listClear", points = 2.0)

@TestCategory(name = "dequeFrontOps", points = 1.0)
@TestCategory(name = "dequeBackOps", points = 1.0)
@TestCategory(name = "dequeMixedOps", points = 2.0)

@TestCategory(name = "queueOps", points = 4.0)

@TestCategory(name = "stackOps", points = 4.0)

@RunWith(GradingRunner.class)

public class Tester2 {
    @GradingTest(category = "listAddAtIndex", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testAddAtIndex() {
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

    @GradingTest(category = "listAddAtIndex", percentage = 0.25, gradingScheme = "[0-0.4)=0.0,[0.4-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testAddAtIndexThrowsCorrectException() {
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

    @GradingTest(category = "listAddAtEnd", percentage = 1.0, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
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

    @GradingTest(category = "listRemoveAtIndex", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testRemoveAtIndex() {
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

    @GradingTest(category = "listRemoveAtIndex", percentage = 0.25, gradingScheme = "[0-0.4)=0.0,[0.4-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testRemoveAtIndexThrowsCorrectExceptions() {
        // Assumes add() is working
        LinkedList<String> list = new LinkedList<>();

        boolean correctException = false;
        try {
            list.remove(-1);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            list.remove(0);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);
        list.add("cat");
        list.add("dog");

        correctException = false;
        try {
            list.remove(-1);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            list.remove(2);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

    }

    @GradingTest(category = "listToArray", percentage = 1.0, gradingScheme = "[0-0.6)=0.0,[0.6-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testToArray() {
        // Assumes add() is working
        LinkedList<String> list = new LinkedList<>();
        assertArrayEquals(new String[0], list.toArray());

        list.add("a");
        assertArrayEquals(new String[] { "a" }, list.toArray());
        list.add("b");
        assertArrayEquals(new String[] { "a", "b" }, list.toArray());
    }

    @GradingTest(category = "listClear", percentage = 1.00, gradingScheme = "allornothing")
    @Test(timeout = 1000)
    public void testClear() {
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals(" (size=0)", list.toStringNext());
        assertEquals(" (size=0)", list.toStringPrev());

        list.add("c");
        assertEquals("c (size=1)", list.toStringNext());
        assertEquals("c (size=1)", list.toStringPrev());
    }

    @GradingTest(category = "listContains", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testContains() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            assertFalse(list.contains(i));
            list.add(i);
            assertTrue(list.contains(i));
        }

        for (int i = 1; i <= 10; i++) {
            assertTrue(list.contains(i));
            assertFalse(list.contains(-i));
        }
    }

    @GradingTest(category = "listContains", percentage = 0.25, gradingScheme = "allornothing")
    @Test(timeout = 1000)
    public void testContainsUsesDotEquals() {
        LinkedList<Point> list = new LinkedList<>();

        assertFalse(list.contains(new Point(5, 7)));
        list.add(new Point(5, 7));
        assertTrue(list.contains(new Point(5, 7)));
    }

    @GradingTest(category = "listGet", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testGet() {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(10 * i);
            assertEquals((Integer) (10 * i), list.get(i));
        }

        for (int i = 0; i < 10; i++) {
            assertEquals((Integer) (10 * i), list.get(i));
        }
    }

    @GradingTest(category = "listGet", percentage = 0.25, gradingScheme = "[0-0.4)=0.0,[0.4-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testGetThrowsProperException() {
        LinkedList<Integer> list = new LinkedList<>();

        boolean correctException = false;
        try {
            list.get(-1);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            list.get(1);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        correctException = false;
        try {
            list.get(-1);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

    }

    @GradingTest(category = "listIndexOf", percentage = 0.4, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testIndexOf() {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
            list.add(i);
            assertEquals(2 * i, list.indexOf(i));
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(2 * i, list.indexOf(i));
        }

        assertEquals(-1, list.indexOf(-1));
    }

    @GradingTest(category = "listIndexOf", percentage = 0.4, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testLastIndexOf() {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
            list.add(i);
            assertEquals(2 * i + 1, list.lastIndexOf(i));
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(2 * i + 1, list.lastIndexOf(i));
        }

        assertEquals(-1, list.lastIndexOf(-1));
    }

    @GradingTest(category = "listIndexOf", percentage = 0.2, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testIndexMethodsUseDotEquals() {
        LinkedList<Point> list = new LinkedList<>();
        list.add(new Point(3, 4));
        list.add(new Point(3, 4));

        assertEquals(0, list.indexOf(new Point(3, 4)));
        assertEquals(1, list.lastIndexOf(new Point(3, 4)));
    }

    @GradingTest(category = "listRemoveItem", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testRemoveItem() {
        LinkedList<String> list = new LinkedList<>();

        assertFalse(list.remove("-1"));
        assertEquals(" (size=0)", list.toStringNext());
        assertEquals(" (size=0)", list.toStringPrev());

        for (int i = 0; i < 6; i++) {
            list.add(i + "");
        }

        assertFalse(list.remove("-1"));
        assertEquals("0 1 2 3 4 5 (size=6)", list.toStringNext());
        assertEquals("0 1 2 3 4 5 (size=6)", list.toStringPrev());

        assertTrue(list.remove("0"));
        assertEquals("1 2 3 4 5 (size=5)", list.toStringNext());
        assertEquals("1 2 3 4 5 (size=5)", list.toStringPrev());

        assertTrue(list.remove("5"));
        assertEquals("1 2 3 4 (size=4)", list.toStringNext());
        assertEquals("1 2 3 4 (size=4)", list.toStringPrev());

        assertFalse(list.remove("99"));
        assertEquals("1 2 3 4 (size=4)", list.toStringNext());
        assertEquals("1 2 3 4 (size=4)", list.toStringPrev());

        assertTrue(list.remove("2"));
        assertEquals("1 3 4 (size=3)", list.toStringNext());
        assertEquals("1 3 4 (size=3)", list.toStringPrev());

        assertTrue(list.remove("3"));
        assertEquals("1 4 (size=2)", list.toStringNext());
        assertEquals("1 4 (size=2)", list.toStringPrev());

        assertTrue(list.remove("4"));
        assertEquals("1 (size=1)", list.toStringNext());
        assertEquals("1 (size=1)", list.toStringPrev());

        assertTrue(list.remove("1"));
        assertEquals(" (size=0)", list.toStringNext());
        assertEquals(" (size=0)", list.toStringPrev());

        list.add("99");
        assertEquals("99 (size=1)", list.toStringNext());
        assertEquals("99 (size=1)", list.toStringPrev());
    }

    @GradingTest(category = "listRemoveItem", percentage = 0.25, gradingScheme = "allornothing")
    @Test(timeout = 1000)
    public void testRemoveItemUsesDotEquals() {
        LinkedList<Point> list = new LinkedList<>();
        list.add(new Point(3, 4));

        assertTrue(list.remove(new Point(3, 4)));
        assertEquals(" (size=0)", list.toStringNext());
        assertEquals(" (size=0)", list.toStringPrev());
    }

    @GradingTest(category = "listSet", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testSet() {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 6; i++) {
            list.add(i);
        }

        Integer ind = 0;
        assertEquals(ind, list.set(0, 7));
        assertEquals("7 1 2 3 4 5 (size=6)", list.toStringNext());
        assertEquals("7 1 2 3 4 5 (size=6)", list.toStringPrev());

        ind = 5;
        assertEquals(ind, list.set(5, 8));
        assertEquals("7 1 2 3 4 8 (size=6)", list.toStringNext());
        assertEquals("7 1 2 3 4 8 (size=6)", list.toStringPrev());

        for (Integer i = 1; i < 5; i++) {
            assertEquals(i, list.set(i, 10 * i));
        }
        assertEquals("7 10 20 30 40 8 (size=6)", list.toStringNext());
        assertEquals("7 10 20 30 40 8 (size=6)", list.toStringPrev());

    }

    @GradingTest(category = "listSet", percentage = 0.25, gradingScheme = "[0-0.4)=0.0,[0.4-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testSetThrowsProperException() {
        LinkedList<Integer> list = new LinkedList<>();

        boolean correctException = false;
        try {
            list.set(0, 5);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            list.set(-1, 5);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            list.set(1, 5);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            list.set(-1, 5);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            list.set(1, 5);
        } catch (Exception e) {
            correctException = (e instanceof IndexOutOfBoundsException);
        }
        assertTrue(correctException);
    }

    //// Deque tests
    @GradingTest(category = "dequeFrontOps", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testDequeFrontOps() {
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= 5; i++) {
            deque.addFront(i);
            assertEquals((Integer) i, deque.peekFront());
            assertEquals((Integer) 1, deque.peekBack());
        }

        for (int i = 5; i >= 1; i--) {
            assertEquals((Integer) i, deque.removeFront());
        }

        assertTrue(deque.isEmpty());
    }

    @GradingTest(category = "dequeFrontOps", percentage = 0.25, gradingScheme = "allornothing")
    @Test(timeout = 1000)
    public void testDequeFrontOpsThrowProperException() {
        Deque<Integer> deque = new LinkedList<>();

        boolean correctException = false;
        try {
            deque.peekFront();
        } catch (Exception e) {
            correctException = (e instanceof NoSuchElementException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            deque.removeFront();
        } catch (Exception e) {
            correctException = (e instanceof NoSuchElementException);
        }
        assertTrue(correctException);

    }

    @GradingTest(category = "dequeBackOps", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testDequeBackOps() {
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= 5; i++) {
            deque.addBack(i);
            assertEquals((Integer) i, deque.peekBack());
            assertEquals((Integer) 1, deque.peekFront());
        }

        for (int i = 5; i >= 1; i--) {
            assertEquals((Integer) i, deque.removeBack());
        }

        assertTrue(deque.isEmpty());
    }

    @GradingTest(category = "dequeBackOps", percentage = 0.25, gradingScheme = "allornothing")
    @Test(timeout = 1000)
    public void testDequeBackOpsThrowProperException() {
        Deque<Integer> deque = new LinkedList<>();

        boolean correctException = false;
        try {
            deque.peekBack();
        } catch (Exception e) {
            correctException = (e instanceof NoSuchElementException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            deque.removeBack();
        } catch (Exception e) {
            correctException = (e instanceof NoSuchElementException);
        }
        assertTrue(correctException);
    }

    @GradingTest(category = "dequeMixedOps", percentage = 1.0, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testDequeMixedOps() {
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= 5; i++) {
            deque.addFront(i);
            deque.addBack(2 * i);
            assertEquals((Integer) i, deque.peekFront());
            assertEquals((Integer) (2 * i), deque.peekBack());
        }

        assertEquals("5 4 3 2 1 2 4 6 8 10 (size=10)", deque.toString());

        for (int i = 5; i >= 1; i--) {
            assertEquals((Integer) i, deque.removeFront());
        }
        for (int i = 1; i <= 4; i++) {
            assertEquals((Integer) (2 * i), deque.removeFront());
        }

        assertFalse(deque.isEmpty());
        assertEquals((Integer) 10, deque.removeBack());
        assertTrue(deque.isEmpty());
    }

    //// Queue tests
    @GradingTest(category = "queueOps", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testQueueOps() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i <= 5; i += 2) {
            q.add(i);
            q.add(i + 1);
            assertEquals((Integer) (i / 2), q.peek());
            assertEquals((Integer) (i / 2), q.remove());
            assertEquals((Integer) (i / 2 + 1), q.peek());
            assertFalse(q.isEmpty());
        }
    }

    @GradingTest(category = "queueOps", percentage = 0.25, gradingScheme = "[0-0.4)=0.0,[0.4-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testQueueOpsThrowProperException() {
        Queue<Integer> q = new LinkedList<>();

        boolean correctException = false;
        try {
            q.remove();
        } catch (Exception e) {
            correctException = (e instanceof NoSuchElementException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            q.peek();
        } catch (Exception e) {
            correctException = (e instanceof NoSuchElementException) || (e instanceof EmptyStackException);
        }
        assertTrue(correctException);
    }

    //// Stack tests
    @GradingTest(category = "stackOps", percentage = 0.75, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testStackOps() {
        Stack<Integer> stk = new LinkedList<>();

        for (int i = 0; i <= 5; i += 2) {
            stk.push(i);
            stk.push(i + 1);
            assertEquals((Integer) (i + 1), stk.peek());
            assertEquals((Integer) (i + 1), stk.pop());
            assertEquals((Integer) i, stk.peek());
            assertFalse(stk.isEmpty());
        }
    }

    @GradingTest(category = "stackOps", percentage = 0.25, gradingScheme = "[0-0.8)=0.0,[0.8-1.0)=0.5,[1-1]=1.0")
    @Test(timeout = 1000)
    public void testStackOpsThrowProperException() {
        Stack<Integer> stk = new LinkedList<>();

        boolean correctException = false;
        try {
            stk.peek();
        } catch (Exception e) {
            correctException = (e instanceof NoSuchElementException) || (e instanceof EmptyStackException);
        }
        assertTrue(correctException);

        correctException = false;
        try {
            stk.pop();
        } catch (Exception e) {
            correctException = (e instanceof EmptyStackException);
        }

        assertTrue(correctException);
    }

    public static void main(String[] args) {
        GradingRunner.run();
    }
}
