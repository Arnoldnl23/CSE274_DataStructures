
public class MyTester {

    public static void main(String[] args) {

        //assuming an array of size 11, where would certain words go?
//        System.out.println("cat".hashCode() % 11); //10
//        System.out.println("dog".hashCode() % 11); //6
//        System.out.println("foreign".hashCode() % 11 + 11); //4
//        System.out.println("coding".hashCode() % 11 + 11); //10
//        System.out.println("grep".hashCode() % 11); //4
        
        //Basic Tests
        HashSet s  = new HashSet(11);
        s.add("cat");
        s.add("dog");
        s.add("foreign");
        s.add("coding");
        s.add("grep");
        s.add("cat");
        
        //System.out.println(s.contains("bat"));
        //System.out.println(s.toStringDeluxe());
        System.out.println("Normal String: " + s.toString() + "  and Normal Size: " + s.size());
        
        
        HashSet s2 = new HashSet();
        s2.add("cat");
        s2.add("grep");
        s2.add("meme");
        s2.add("foreigns");
        //Add All
        System.out.println("\nAddAll Tests");
        System.out.println("True? " + s.addAll(s2.toArray()));
        System.out.println("New String: " + s.toString() + "  and New Size: " + s.size());
        
        s = new HashSet(11);
        s.add("cat");
        s.add("dog");
        s.add("foreign");
        s.add("coding");
        s.add("grep");

        HashSet s3 = new HashSet();
        System.out.println("False? " + s.addAll(s3.toArray()));
        System.out.println("New String: " + s.toString() + "  and New Size: " + s.size() + " Shouldn't Change");
        HashSet s4 = new HashSet();
        s4.add("cat");
        s4.add("foreign");
        s4.add("grep");
        System.out.println("False? " + s.addAll(s4.toArray()));
        System.out.println("New String: " + s.toString() + "  and New Size: " + s.size() + " Shouldn't Change");
        
        //Intersection
        System.out.println("\nIntersection Tests");
        System.out.println("Cat and Grep? " + s.intersection(s2).toString());
        System.out.println("Blank? " + s.intersection(s3).toString());
        System.out.println("Cat, Foreign, and Grep? " + s.intersection(s4).toString());
        
        //Union
        System.out.println("\nUnion Tests");
        System.out.println("Add meme and foreigns? " + s.union(s2).toString());
        System.out.println("Doesn't change? " + s.union(s3).toString());
        System.out.println("Doesn't Change? " + s.union(s4).toString());
        
        //Equal Sets
        System.out.println("\nEqualSets Tests");
        s = new HashSet(11);
        s.add("cat");
        s.add("dog");
        s.add("foreign");
        s.add("coding");
        s.add("grep");
        s2 = new HashSet(11);
        s2.add("grep");
        s2.add("cat");
        s2.add("dog");
        s2.add("foreign");
        s2.add("coding");
        System.out.println("True? " + s.equalSets(s2));
        HashSet s5 = new HashSet(11);
        s5.add("grep");
        s5.add("car");
        s5.add("meme");
        s5.add("foreigns");
        s5.add("coding");
        System.out.println("False? " + s.equalSets(s5));
        HashSet s6 = new HashSet();
        System.out.println("False? " + s.equalSets(s6));
        System.out.println("False? " + s6.equals(s));
    }

}
