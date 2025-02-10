
public class Tester {
	public static void main(String[] args) {
		Queue<Integer> q = new CircularArrayQueue<>();
		q.add(4);
		q.add(8);
		q.add(15);
		
		q.add(99);
		
		System.out.println(q);
	}
}
