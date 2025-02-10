
public class Tester {

    public static void main(String[] args) {
        
    	SocialNetwork fb = new SocialNetwork();
    	fb.addUser("Amy");
    	fb.addUser("Beth");
    	fb.addUser("Cathy");
    	fb.addUser("Daniela");
    	fb.addUser("Ellen");
    	fb.addUser("Fran");
    	fb.addUser("Gina");
    	fb.addUser("Helen");
   	    	
    	System.out.println(fb);
    	
    	fb.connect("Amy", "Beth");
    	fb.connect("Amy", "Cathy");
    	fb.connect("Amy", "Daniela");
    	fb.connect("Amy", "Ellen");

    	fb.connect("Beth", "Ellen");
    	fb.connect("Cathy", "Daniela");
    	fb.connect("Cathy", "Ellen");
    	fb.connect("Fran", "Gina");

    	System.out.println(fb);
    	System.out.println(fb.connectionCount());
        System.out.println(fb.breadthFirstTraversal("Amy"));
        
    }

   
    
}
