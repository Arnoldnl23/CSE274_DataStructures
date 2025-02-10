import java.util.*;

/*
 * Implements a social network that has users who can be connected to each other.
 * 
 * @author Norm Krumpe
 *
 */
public class SocialNetwork {

	// The key is the name of a user
	// The value is a set of names to whom that user is connected
	private Map<String, Set<String>> map;

	/*
	 * Constructs a new graph by creating an empty map
	 */
	public SocialNetwork() {
		map = new TreeMap<String, Set<String>>();
	}

	/*
	 * addUser("Mary") should put a key-value pair in the map with no friends for
	 * Mary yet. returns true if and only if the name was added by this method call.
	 */
	public boolean addUser(String name) {
	    if (map.containsKey(name)) {
	        return false;
	    }
	    
		map.put(name, new TreeSet<String>());
		
		return true;
	}

	/*
	 * removes the specified user from the social network.
	 * We won't implement this in class, but the idea is:
	 * 1. remove the user key from the map
	 * 2. loop through the other users and remove that person from their friends list
	 * returns true if and only if the name was removed by this method call.
	 */
	public boolean removeUser(String name) {
		return false;
	}

	/*
	 * returns true if and only if the specified user is in the social network
	 */
	public boolean hasUser(String name) {
		return map.containsKey(name);
	}

	/*
	 * connects two friends, but only if both friends are users already
	 * returns true if and only if a connection between users was made by this method call
	 */
	public boolean connect(String name1, String name2) {
		if ( !hasUser(name1) || !hasUser(name2)) {
		    return false;
		}
		
		if ( areConnected(name1, name2) ) {
		    return false;
		}    
		
		map.get(name1).add(name2);
		map.get(name2).add(name1);
		return true;
	}

	/*
	 * we won't implement this in class, but the idea is: if both users exist,
	 * and if one is already connected to the other, then remove each person
	 * from the other person's friend collection.
	 * returns true if and only if a connection was deleted. 
	 */
	public boolean disconnect(String name1, String name2) {
		return false;
	}

	/*
	 * returns true if and only if the two specified users exist and are friends
	 */
	public boolean areConnected(String name1, String name2) {
		if ( !hasUser(name1) || !hasUser(name2) ) {
		    return false;
		}
		
		if ( !map.get(name1).contains(name2) || !map.get(name2).contains(name1) ) {
		    return false;
		}
		
		return true;
	}

	/*
	 * returns true if and only if the social network has no users
	 */
	public boolean isEmpty() {
		return map.size() == 0;
	}

	/*
	 * returns the number of users in the social network. This is the same
	 * as returning the number of nodes in a graph
	 */
	public int userCount() {
		return map.size();
	}

	/*
	 * returns the number of friendships. This is the same as counting the number
	 * of edges in an undirected graph
	 */
	public int connectionCount() {
		int result = 0;
	    for ( String s : map.keySet() ) {
		    result += map.get(s).size();
		}
	    
	    return result/2;
	}
	
	/*
	 * Uses a queue to help produce a breadth first traversal of the social network,
	 * beginning at the specified name. Note that if the graph is not connected, it's possible
	 * that some names won't be reachable and so they won't appear in the traversal.
	 */
	public List<String> breadthFirstTraversal(String name) {
		ArrayList<String> result = new ArrayList<>();
		if (!hasUser(name)) return result;
		
		Queue<String> q = new LinkedList<>();
		q.add(name);
		result.add(name);
		
		while ( !q.isEmpty() ) {
		    String front = q.remove();
		    for ( String friend : map.get(front) ) {
		        if ( !result.contains(friend)) {
		            q.add(friend);
		            result.add(friend);
		        }
		    }
		}
				
		return result;
	}

	/*
	 * Uses a stack to help produce a depth first traversal of the social network,
	 * beginning at the specified name. Note that if the graph is not connected, it's possible
	 * that some names won't be reachable and so they won't appear in the traversal.
	 */
	public List<String> depthFirstTraversal(String name) {
		ArrayList<String> result = new ArrayList<>();
		if (!hasUser(name)) return result;
		
				
		return result;
	}

	// Returns a string representation of the graph
	// Each line is a user followed by the user's friends
	public String toString() {
		if (map.size() == 0) return "EMPTY";
		
		String result = "";

		// loop through the map
		for (String v : map.keySet()) {
			result += v + " --> ";
			result += map.get(v); // grabs the set of friends
			result += "\n";
		}

		return result;
	}
}
