//Standard Node class with data and a
//reference to the next node
class Node {

    int data;
    Node next;
    
    //constructs a new node by wrapping an int
    //inside a node
    Node(int data) {
        this.data = data;
        this.next = null;
    }

}
