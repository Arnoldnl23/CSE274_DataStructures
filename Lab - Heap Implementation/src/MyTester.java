
public class MyTester {

	public static void main(String[] args) {
		
		
		Heap h = new Heap(5);
		System.out.println(h);
		
		h.add(10);
		System.out.println(h);
		h.add(5);
		h.add(3);
		System.out.println(h);
		h.add(99);
		System.out.println(h);
		
		
		
		
		
		// This is the same data from Thursday's heap handout.
		Integer[] data = { 47, 15, 12, 6, 90, 9, 85, 44, 25, 201, 3, 65, 64, 66, 1, 87 };
		
		// If you create an empty heap and then loop through the data calling
		// add(), you should get: 2 4 8 7 11 14 9 63 25 27
		// We called this Option 1 in the handout, 
		// which has time complexity O(n log n)
		Heap h2 = new Heap();
		for ( int n : data ) {
		    h2.add(n);
		}
		System.out.println(h2);
		for ( int i = 0; i < data.length; i++ ) {
		h2.remove();
		System.out.println(h2);
		}
		
		// If you create a heap using the array constructor,
		// then you should get: 2 4 8 7 11 25 9 14 63 27
		// We called this Option 2 in the handout, 
		// which has a better time complexity O(n)
		
	}

}
