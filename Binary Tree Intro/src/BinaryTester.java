public class BinaryTester {

    public static void main(String[] args) {
        
        BinaryNode sample = sampleTree();
        
        System.out.println(sample.right.left.data);
        
        printRightSide(sample);
        
        //Testing printRightSide
        BinaryNode rootOnly = new BinaryNode(99);
        printRightSide(rootOnly);
        
        BinaryNode blank = null;
        printRightSide(blank);
        
        System.out.println(addTopThree(sample));
        System.out.println(addTopThree(rootOnly));
        System.out.println(addTopThree(blank));
    }
    
    public static int addTopThree(BinaryNode root) {
        int sum = 0;
        
        if ( root == null ) {
            return sum;
        }
        sum += root.data;
        
        if ( root.right != null ) {
            sum += root.right.data;
        }
        
        if ( root.left != null ) {
            sum += root.left.data;
        }
        
        return sum;
    }
    
    //Prints a binaryTree starting at the root and traveling only right
    public static void printRightSide(BinaryNode root) {
        
        BinaryNode curr = root;
        System.out.print("[");
        while ( curr != null ) {
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
        System.out.print("]\n");
    }
    
    //Builds and returns the root of the sample tree
    public static BinaryNode sampleTree() {
        BinaryNode root = new BinaryNode(10);
        root.left = new BinaryNode(11);
        root.left.left = new BinaryNode(7);
        
        BinaryNode subtreeR = new BinaryNode(9);
        subtreeR.left = new BinaryNode(15);
        subtreeR.right = new BinaryNode(8);
        root.right = subtreeR;
        
        return root;
    }
}
