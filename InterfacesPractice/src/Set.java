/*
 * The Set interface is just a list of methods that
 * any class must have if it wants to call itself a set
 * Just headers.
 * No constructors.
 * No instance variables.
 */
public interface Set {
    //Normally these would have javadoc descriptions
    //and then wouldn't put javadoc descriptions in the implementations
    
    public boolean add(String s);
    
    public boolean remove(String s);
    
    public String remove();
    
    public boolean contains(String s);
    
    public String[] toArray();
    
    public int size();
    
    public boolean isEmpty();
    
    public void clear();
}
