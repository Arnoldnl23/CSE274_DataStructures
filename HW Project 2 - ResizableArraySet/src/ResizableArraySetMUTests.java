import org.junit.*;
import org.junit.runner.RunWith;

import mu.grading.*;
import static mu.grading.Assert.*;

import java.awt.Point;
import java.util.*;

@Reporting(lowDetails=false, medDetails=false, highDetails=true)

@TestCategory(name = "constructors", points = 10.0)
@TestCategory(name = "add", points = 6.0)
@TestCategory(name = "removePt", points = 5.0)
@TestCategory(name = "removeNoParam", points = 5.0)
@TestCategory(name = "contains", points = 3.0)
@TestCategory(name = "clear", points = 3.0)
@TestCategory(name = "toString", points = 3.0)
@TestCategory(name = "toArray", points = 3.0)

@TestCategory(name = "intersection", points = 3.0)
@TestCategory(name = "union", points = 3.0)
@TestCategory(name = "allSums", points = 3.0)
@TestCategory(name = "withinRadius", points = 3.0)

@RunWith(GradingRunner.class)
public class ResizableArraySetMUTests {

	@GradingTest(category = "constructors", percentage = 0.1, gradingScheme = "allornothing")
	@Test
	public void testNoParamConstructor() {
		ResizableArraySet s = new ResizableArraySet();
		assertEquals(5, s.getCapacity());
		assertEquals(0, s.size());
	}

	@GradingTest(category = "constructors", percentage = 0.5, gradingScheme = "allornothing")
	@Test
	public void testIntParamConstructor() {
		ResizableArraySet s = new ResizableArraySet(17);
		assertEquals(17, s.getCapacity());
		assertEquals(0, s.size());
	}

	@GradingTest(category = "constructors", percentage = 0.1, gradingScheme = "allornothing")
	@Test
	public void testArrayConstructorNoDuplicates() {
		Point[] noDupes = { new Point(1, 2), new Point(3, 4), new Point(5, 6), new Point(7, 8), new Point(9, 10),
				new Point(11, 12) };

		ResizableArraySet s = new ResizableArraySet(noDupes);
		assertEquals(6, s.size());

		for (int i = 1; i <= 11; i += 2) {
			assertTrue(s.contains(new Point(i, i + 1)));
		}
	}

	@GradingTest(category = "constructors", percentage = 0.2, gradingScheme = "allornothing")
	@Test
	public void testArrayConstructorWithDuplicates() {
		Point[] dupes = { new Point(1, 2), new Point(3, 4), new Point(5, 6), new Point(7, 8), new Point(9, 10),
				new Point(11, 12), new Point(1, 2), new Point(3, 4), new Point(5, 6), new Point(7, 8), new Point(9, 10),
				new Point(11, 12) };

		ResizableArraySet s = new ResizableArraySet(dupes);
		assertEquals(6, s.size());

		for (int i = 1; i <= 11; i += 2) {
			assertTrue(s.contains(new Point(i, i + 1)));
		}
	}

	@GradingTest(category = "constructors", percentage = 0.1, gradingScheme = "allornothing")
	@Test
	public void testArrayConstructorCorrectCapacity() {
		Point[] noDupes = { new Point(1, 2), new Point(3, 4), new Point(5, 6), new Point(7, 8), new Point(9, 10),
				new Point(11, 12) };

		Point[] dupes = { new Point(1, 2), new Point(3, 4), new Point(5, 6), new Point(7, 8), new Point(9, 10),
				new Point(11, 12), new Point(1, 2), new Point(3, 4), new Point(5, 6), new Point(7, 8), new Point(9, 10),
				new Point(11, 12) };

		ResizableArraySet s = new ResizableArraySet(noDupes);
		assertEquals(6, s.getCapacity());

		s = new ResizableArraySet(dupes);
		assertEquals(12, s.getCapacity());
	}

	@GradingTest(category = "add", percentage = 0.5, gradingScheme = "allornothing")
	@Test
	public void testAddWithoutResize() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 19; i++) {
			assertTrue(s.add(new Point(i, 2 * i)));
		}

		assertEquals(19, s.size());
		assertFalse(s.isEmpty());

		// these are all duplicates. set shouldn't change
		for (int i = 0; i < 19; i++) {
			assertFalse(s.add(new Point(i, 2 * i)));
		}

		assertEquals(19, s.size());
	}

	@GradingTest(category = "add", percentage = 0.3, gradingScheme = "allornothing")
	@Test
	public void testAddWithResize() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 30; i++) {
			assertTrue(s.add(new Point(i, 2 * i)));
		}

		assertEquals(30, s.size());
		assertFalse(s.isEmpty());

		// these are all duplicates. set shouldn't change
		for (int i = 0; i < 30; i++) {
			assertFalse(s.add(new Point(i, 2 * i)));
		}

		assertEquals(30, s.size());
	}

	@GradingTest(category = "add", percentage = 0.2, gradingScheme = "allornothing")
	@Test
	public void testAddEdgeCases() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 20; i++) {
			assertTrue(s.add(new Point(i, 2 * i)));
		}

		assertEquals(20, s.size());
		assertEquals(20, s.getCapacity());

		// trying to add a duplicate should not change anything:
		s.add(new Point(0, 0));
		s.add(new Point(19, 38));
		assertEquals(20, s.size());
		assertEquals(20, s.getCapacity());

		// adding a non-duplicate should trigger resize
		s.add(new Point(5, 5));
		assertEquals(21, s.size());
		assertEquals(40, s.getCapacity());
	}

	@GradingTest(category = "contains", percentage = 0.5, gradingScheme = "allornothing")
	@Test
	public void testContainsBeforeResize() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 19; i++) {
			s.add(new Point(i, 2 * i));
			assertTrue(s.contains(new Point(i, 2 * i)));
			assertFalse(s.contains(new Point(i, 2 * i + 1)));
		}

		for (int i = 0; i < 19; i++) {
			assertTrue(s.contains(new Point(i, 2 * i)));
			assertFalse(s.contains(new Point(i, 2 * i + 1)));
		}
	}

	@GradingTest(category = "contains", percentage = 0.5, gradingScheme = "allornothing")
	@Test
	public void testContainsAfterResize() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 21; i++) {
			s.add(new Point(i, 2 * i));
			assertTrue(s.contains(new Point(i, 2 * i)));
			assertFalse(s.contains(new Point(i, 2 * i + 1)));
		}

		for (int i = 0; i < 21; i++) {
			assertTrue(s.contains(new Point(i, 2 * i)));
			assertFalse(s.contains(new Point(i, 2 * i + 1)));
		}
	}

	@GradingTest(category = "removePt", percentage = 0.25, gradingScheme = "allornothing")
	@Test
	public void testRemovePointBeforeResize() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 19; i++) {
			assertFalse(s.remove(new Point(i, 2 * i)));
			assertEquals(i, s.size());
			s.add(new Point(i, 2 * i));
			assertFalse(s.remove(new Point(i, 2 * i + 1)));
			assertEquals(i + 1, s.size());
			assertTrue(s.remove(new Point(i, 2 * i)));
			assertEquals(i, s.size());
			s.add(new Point(i, 2 * i));
			assertEquals(i + 1, s.size());
		}
	}

	@GradingTest(category = "removePt", percentage = 0.25, gradingScheme = "allornothing")
	@Test
	public void testRemovePointAfterResize() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 21; i++) {
			s.add(new Point(i, 2 * i));
			assertFalse(s.remove(new Point(i, 2 * i + 1)));
			assertEquals(i + 1, s.size());
			assertTrue(s.remove(new Point(i, 2 * i)));
			assertEquals(i, s.size());
			s.add(new Point(i, 2 * i));
			assertEquals(i + 1, s.size());
		}
	}

	// assumes toString works (even though formatting might be off)
	@GradingTest(category = "removePt", percentage = 0.5, gradingScheme = "allornothing")
	@Test
	public void testRemovePointFollowsExpectedAlgorithm() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 6; i++) {
			s.add(new Point(i, i));
		}

		s.remove(new Point(2, 2));
		assertEquals("0011553344", extractDigits(s.toString()));

		s.remove(new Point(0, 0));
		assertEquals("44115533", extractDigits(s.toString()));

		s.remove(new Point(3, 3));
		assertEquals("441155", extractDigits(s.toString()));

		s.remove(new Point(4, 4));
		assertEquals("5511", extractDigits(s.toString()));

		s.remove(new Point(5, 5));
		assertEquals("11", extractDigits(s.toString()));

		s.remove(new Point(1, 1));
		assertEquals("", extractDigits(s.toString()));

	}

	@GradingTest(category = "removeNoParam", percentage = 0.5, gradingScheme = "allornothing")
	@Test
	public void testRemoveNoParamBasic() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 6; i++) {
			s.add(new Point(i, i));
		}

		// Does remove() correctly return SOME point?
		Point p = s.remove();
		assertNotNull(p);
		assertFalse(s.contains(p));
		assertEquals(5, s.size());

		// Does set still work correctly?
		assertTrue(s.add(new Point(99, 99)));
		assertTrue(s.contains(new Point(99, 99)));
		assertEquals(6, s.size());
	}

	@GradingTest(category = "removeNoParam", percentage = 0.5, gradingScheme = "allornothing")
	@Test
	public void testRemoveNoParamReturnsCorrectPoint() {
		ResizableArraySet s = new ResizableArraySet(20);

		for (int i = 0; i < 6; i++) {
			s.add(new Point(i, i));
		}

		// Does remove() correctly return SOME point?
		for (int i = 5; i >= 0; i--) {
			assertEquals(new Point(i, i), s.remove());
			assertEquals(i, s.size());
		}
	}

	@GradingTest(category = "clear", percentage = 1.0, gradingScheme = "allornothing")
	@Test
	public void testClear() {
		ResizableArraySet s = new ResizableArraySet(20);

		s.add(new Point(0, 0));
		s.clear();
		assertEquals(0, s.size());

		assertTrue(s.add(new Point(0, 0)));
		assertEquals(1, s.size());

		for (int i = 0; i < 30; i++) {
			s.add(new Point(i, 2 * i));
		}

		s.clear();
		assertEquals(0, s.size());

		assertTrue(s.add(new Point(0, 0)));
		assertTrue(s.add(new Point(29, 58)));
		assertEquals(2, s.size());
	}

	@GradingTest(category = "toArray", percentage = 0.75, gradingScheme = "allornothing")
	@Test
	public void testToArrayBasic() {
		Point[] pts = new Point[10];
		ResizableArraySet s = new ResizableArraySet(5);

		for (int i = 0; i < 10; i++) {
			pts[i] = new Point(i, i);
			s.add(new Point(i, i));
			assertEquals(i + 1, s.toArray().length);
		}

		assertArrayEquals(pts, s.toArray());
	}

	@GradingTest(category = "toArray", percentage = 0.25, gradingScheme = "allornothing")
	@Test
	public void testToArrayReturnsEmptyArray() {
		ResizableArraySet s = new ResizableArraySet(5);
		Point[] arr = s.toArray();
		assertNotNull(arr);
		assertEquals(0, arr.length);
	}

	@GradingTest(category = "toString", percentage = 0.4, gradingScheme = "allornothing")
	@Test
	public void testToStringHasCorrectPoints() {
		ResizableArraySet s = new ResizableArraySet(5);
		String result = "";

		for (int i = 0; i < 5; i++) {
			s.add(new Point(i, i));
			result += String.format("%d%d", i, i);
			assertEquals(result, extractDigits(s.toString()));
		}
	}

	@GradingTest(category = "toString", percentage = 0.25, gradingScheme = "allornothing")
	@Test
	public void testToStringCorrectFormat() {
		ResizableArraySet s = new ResizableArraySet(5);
		String result = "";

		for (int i = 0; i < 5; i++) {
			s.add(new Point(i, i));
			result += String.format("(%d, %d) ", i, i);
			assertEquals(result.trim(), s.toString().trim());
		}
	}
	
	@GradingTest(category = "toString", percentage = 0.1, gradingScheme = "allornothing")
	@Test
	public void testToStringTrimsResult() {
		ResizableArraySet s = new ResizableArraySet(5);
		String result = "";

		for (int i = 0; i < 5; i++) {
			s.add(new Point(i, i));
			result += String.format("(%d, %d) ", i, i);
			assertEquals(result.trim(), s.toString());
		}
	}

	@GradingTest(category = "toString", percentage = 0.25, gradingScheme = "allornothing")
	@Test
	public void testToStringReturnsEmpty() {
		ResizableArraySet s = new ResizableArraySet(5);
		assertEquals("EMPTY", s.toString());
	}

	@GradingTest(category = "intersection", percentage = 0.7, gradingScheme = "allornothing")
	@Test
	public void testIntersectionBasic() {
		Point[] pts1 = { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5) };
		Point[] pts2 = { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5) };
		Point[] pts3 = { new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(6, 6), };

		ResizableArraySet s1 = buildSetFromArray(pts1);
		ResizableArraySet s2 = buildSetFromArray(pts2);
		ResizableArraySet s3 = buildSetFromArray(pts3);

		HashSet<Point> hs1 = buildHashSetFromArray(pts1);
		HashSet<Point> hs2 = buildHashSetFromArray(pts2);
		HashSet<Point> hs3 = buildHashSetFromArray(pts3);

		Point[] result, expected;
		result = s1.intersection(s2).toArray();
		expected = (intersection(hs1, hs2)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);

		result = s1.intersection(s3).toArray();
		expected = (intersection(hs1, hs3)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);

		result = s3.intersection(s1).toArray();
		expected = (intersection(hs3, hs1)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);

		result = s3.intersection(s3).toArray();
		expected = (intersection(hs3, hs3)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
	}

	@GradingTest(category = "intersection", percentage = 0.3, gradingScheme = "allornothing")
	@Test
	public void testIntersectionEdgeCases() {
		Point[] pts1 = { new Point(1, 1), new Point(2, 2), new Point(3, 3) };
		Point[] pts2 = { new Point(4, 4) };
		Point[] pts3 = {};

		ResizableArraySet s1 = buildSetFromArray(pts1);
		ResizableArraySet s2 = buildSetFromArray(pts2);
		ResizableArraySet s3 = buildSetFromArray(pts3);

		HashSet<Point> hs1 = buildHashSetFromArray(pts1);
		HashSet<Point> hs2 = buildHashSetFromArray(pts2);
		HashSet<Point> hs3 = buildHashSetFromArray(pts3);

		Point[] result, expected;
		result = s1.intersection(s2).toArray();
		expected = (intersection(hs1, hs2)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
		assertTrue(s1.intersection(s2).getCapacity() > 0);

		result = s1.intersection(s3).toArray();
		expected = (intersection(hs1, hs3)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
		assertTrue(s1.intersection(s3).getCapacity() > 0);

		result = s3.intersection(s1).toArray();
		expected = (intersection(hs3, hs1)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
		assertTrue(s3.intersection(s1).getCapacity() > 0);

		result = s3.intersection(s3).toArray();
		expected = (intersection(hs3, hs3)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
		assertTrue(s3.intersection(s3).getCapacity() > 0);
	}

	@GradingTest(category = "union", percentage = 0.7, gradingScheme = "allornothing")
	@Test
	public void testUnion() {
		Point[] pts1 = { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5) };
		Point[] pts2 = { new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5) };
		Point[] pts3 = { new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(6, 6), };

		ResizableArraySet s1 = buildSetFromArray(pts1);
		ResizableArraySet s2 = buildSetFromArray(pts2);
		ResizableArraySet s3 = buildSetFromArray(pts3);

		HashSet<Point> hs1 = buildHashSetFromArray(pts1);
		HashSet<Point> hs2 = buildHashSetFromArray(pts2);
		HashSet<Point> hs3 = buildHashSetFromArray(pts3);

		Point[] result, expected;
		result = s1.union(s2).toArray();
		expected = (union(hs1, hs2)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);

		result = s1.union(s3).toArray();
		expected = (union(hs1, hs3)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);

		result = s3.union(s1).toArray();
		expected = (union(hs3, hs1)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);

		result = s3.union(s3).toArray();
		expected = (union(hs3, hs3)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
	}

	@GradingTest(category = "union", percentage = 0.3, gradingScheme = "allornothing")
	@Test
	public void testUnionEdgeCases() {
		Point[] pts1 = { new Point(1, 1), new Point(2, 2), new Point(3, 3) };
		Point[] pts2 = { new Point(4, 4) };
		Point[] pts3 = {};

		ResizableArraySet s1 = buildSetFromArray(pts1);
		ResizableArraySet s2 = buildSetFromArray(pts2);
		ResizableArraySet s3 = buildSetFromArray(pts3);

		HashSet<Point> hs1 = buildHashSetFromArray(pts1);
		HashSet<Point> hs2 = buildHashSetFromArray(pts2);
		HashSet<Point> hs3 = buildHashSetFromArray(pts3);

		Point[] result, expected;
		result = s1.union(s2).toArray();
		expected = (union(hs1, hs2)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
		assertTrue(s1.union(s2).getCapacity() > 0);

		result = s1.union(s3).toArray();
		expected = (union(hs1, hs3)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
		assertTrue(s1.union(s3).getCapacity() > 0);

		result = s3.union(s1).toArray();
		expected = (union(hs3, hs1)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
		assertTrue(s3.union(s1).getCapacity() > 0);

		result = s3.union(s3).toArray();
		expected = (union(hs3, hs3)).toArray(new Point[0]);
		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
		assertTrue(s3.union(s3).getCapacity() > 0);
	}

	@GradingTest(category = "allSums", percentage = 0.7, gradingScheme = "allornothing")
	@Test
	public void testAllSumsBasic() {
		Point[] pts = { new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3) };
		ResizableArraySet s = buildSetFromArray(pts);
		HashSet<Point> hs = buildHashSetFromArray(pts);

		Point[] result, expected;

		result = s.allSums().toArray();
		expected = (allSums(hs)).toArray(new Point[0]);

		sort(result);
		sort(expected);
		assertArrayEquals(result, expected);
	}
	
	@GradingTest(category = "allSums", percentage = 0.3, gradingScheme = "allornothing")
	@Test
	public void testAllSumsEdgeCases() {
		// allSums() when set is empty
		ResizableArraySet s = new ResizableArraySet();
		ResizableArraySet result = s.allSums();
		assertEquals(0, result.size());
		assertTrue(result.getCapacity() > 0);
		
		// allSums() when set has one element still should return empty set
		s.add(new Point(5, 5));
		result = s.allSums();
		assertEquals(0, result.size());
		assertTrue(result.getCapacity() > 0);
	}

	@GradingTest(category = "withinRadius", percentage = 0.7, gradingScheme = "allornothing")
	@Test
	public void testWithinRadiusBasic() {
		Point[] pts = { new Point(0, 0), new Point(-1, -1), new Point(2, 2), 
				new Point(3, 3), new Point(3, 4), new Point(4, 4)};
		ResizableArraySet s = buildSetFromArray(pts);
		
		assertEquals(6, s.withinRadius(100));
		assertEquals(5, s.withinRadius(5.1));
		assertEquals(2, s.withinRadius(2));
	}
	
	@GradingTest(category = "withinRadius", percentage = 0.3, gradingScheme = "allornothing")
	@Test
	public void testWithinRadiusEdgeCases() {
		Point[] pts = { new Point(0, 0), new Point(0, 1), new Point(3, 3), new Point(3, 4)};
		ResizableArraySet s = buildSetFromArray(pts);
		
		assertEquals(4, s.withinRadius(5));
		assertEquals(2, s.withinRadius(1));
	}

	private void sort(Point[] pts) {
		Arrays.sort(pts, (p1, p2) -> p1.x - p2.x);
	}

	private ResizableArraySet buildSetFromArray(Point[] pts) {
		ResizableArraySet s = new ResizableArraySet(2 * pts.length);

		for (Point p : pts) {
			s.add(p);
		}

		return s;
	}

	private HashSet<Point> buildHashSetFromArray(Point[] pts) {
		HashSet<Point> s = new HashSet<>();

		for (Point p : pts) {
			s.add(p);
		}

		return s;
	}

	// Compute intersection of two hash sets
	private HashSet<Point> intersection(HashSet<Point> s1, HashSet<Point> s2) {
		HashSet<Point> result = new HashSet<>();

		for (Point p : s1) {
			if (s2.contains(p)) {
				result.add(p);
			}
		}

		return result;
	}

	// Compute union of two hash sets
	private HashSet<Point> union(HashSet<Point> s1, HashSet<Point> s2) {
		HashSet<Point> result = new HashSet<>();

		for (Point p : s1) {
			result.add(p);
		}
		for (Point p : s2) {
			result.add(p);
		}

		return result;
	}

	// Compute all possible sums in a hash set of points
	private HashSet<Point> allSums(HashSet<Point> s) {
		HashSet<Point> result = new HashSet<>();
		Point[] pts = s.toArray(new Point[0]);

		for (int i = 0; i < pts.length - 1; i++) {	
			for (int j = i + 1; j < pts.length; j++) {
				Point p = new Point(pts[i].x + pts[j].x, pts[i].y + pts[j].y);
				result.add(p);
			}
		}

		return result;
	}
	
	// Reduce a string to only the digits in that string
	private String extractDigits(String str) {
		String clean = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				clean += c;
			}
		}
		return clean;
	}
	
	public static void main(String[] args) {
		GradingRunner.run();
	}

}
