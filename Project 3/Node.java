// Node.java


package assignment3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author ericsu
 * @author brianmadina
 *
 */
public class Node {
	String word; 
	boolean isMarked = false;
	int numOfChildren = 0;
	ArrayList<Node> relatedNodes;	// List of 1-letter away nodes

	public Node(String word) {
		this.word = word;
	}
	
	/**
	 * Convert a set of Strings to a set of Nodes
	 * @param dict
	 * @return
	 */
	public static Set<Node> convertToNodes(Set<String> dict) {
		String[] stringArray = (String[]) dict.toArray();
		Set<Node> setOfNodes = new HashSet<Node>();
		
		for (int i = 0; i < dict.size(); i++) {
			Node node = new Node(stringArray[i]);
			setOfNodes.add(node);
		}
		
		return setOfNodes;
	}
	
}
