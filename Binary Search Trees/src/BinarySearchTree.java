
public class BinarySearchTree {

    private Node root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public void add(int n) {
         Node temp = new Node(n);
         
         if ( root == null ) {
             root = temp;
             return;
         }
         
         Node curr = root;
         while ( curr.left != temp && curr.right != temp ) {
             if ( n < curr.data ) {
                 if ( curr.left == null ) {
                     curr.left = temp;
                 }
                 else {
                     curr = curr.left;
                 }
             }
             else {
                 if ( curr.right == null ) {
                     curr.right = temp;
                 }
                 else {
                     curr = curr.right;
                 }
             }
         }
         
         return;
    }
    
    public boolean contains(int n) {
        Node curr = root;
        
        while ( curr != null ) {
            if ( curr.data == n ) {
                return true;
            }
            else if ( n < curr.data ) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }
        
        return false;
    }
    
    //This is a method for the CLIENT, but is
    //not useful for the recursion.
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }
    
    //This is a private helper method that allows
    //recursion
    private void inOrderTraversal(Node start) {
        if (start == null) return;
        
        inOrderTraversal(start.left);
        System.out.print(start.data + " ");
        inOrderTraversal(start.right);
    }
    
    private class Node {
        private int data;
        private Node left;
        private Node right;
        
        private Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
