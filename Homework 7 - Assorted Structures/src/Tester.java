
public class Tester {

    public static void main(String[] args) {
//
//        List<Integer> list = new LinkedList<>();
//        list.add(4);
//        list.add(7);
//        list.add(19);
//        System.out.println(list);
        
        //Testing Deque
//        Deque<Integer> listD = new LinkedList<>();
//        listD.addBack(4);
//        listD.addFront(1);
//        listD.addFront(2);
//        listD.addBack(3);
//        
//        System.out.println(listD);
//        System.out.println(listD.peekFront());
//        System.out.println(listD.peekBack());
//        System.out.println(listD.removeFront());
//        System.out.println(listD.removeBack());
//        listD.removeBack();
//        listD.removeFront();
//        listD.removeFront();
        
        //Testing LinkedList implementation 
        LinkedList<String> list = new LinkedList<>();
//        list.add("0");
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
        for (int i = 0; i < 6; i++) {
            list.add(i + "");
        }
//        list.remove(3);
//        list.add(3 , "d");

//        list.set(2, "e");
        System.out.println(list.remove("1"));
        System.out.println(list.toString());
        System.out.println(list.toStringPrev());
 //       list.add("a");
    //    System.out.println(list.lastIndexOf("a"));
        
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.get(3));
        
//        System.out.println(list.toStringNext());
//        System.out.println(list.toStringPrev());
        
//        Object[] result = list.toArray();
//        for ( Object s : result ) {
//            System.out.println(s);
//        }

        //Testing Queue implementation
//        Queue<String> listQ = new LinkedList<>();
//        listQ.add("x");
//        listQ.add("y");
//        listQ.add("z");
//        System.out.println(listQ.peek());
//        listQ.remove();
//        System.out.println(listQ);
//        listQ.remove();
//        listQ.remove();
//        listQ.peek();
    }

}
