// Node.java
/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Eric Su
 * es25725
 * 16475
 * Brian Madina
 * bjm3348
 * 16460
 * Slip days used: <1>
 * Git URL: https://github.com/er1csu/ee422c-fall-2016-pair-programming
 * Fall 2016
 */

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
	int isMarked = 0;//0 = white, 1 = gray, 2 = black
	ArrayList<Node> relatedNodes;	// List of 1-letter away nodes
	Node parent;

	public Node(String word) {
		this.word = word;
		relatedNodes = new ArrayList<Node>();
	}
	
	/**
	 * Convert a set of Strings to a set of Nodes
	 * @param dict
	 * @return Set of Nodes
	 */
	public static Set<Node> convertToNodes(Set<String> dict) {
		String[] stringArray = new String[]{};
		stringArray = dict.toArray(stringArray);
		Set<Node> setOfNodes = new HashSet<Node>();
		
		for (int i = 0; i < dict.size(); i++) {
			Node node = new Node(stringArray[i]);
			//testing if the node was created
			setOfNodes.add(node);
		}
		
		return setOfNodes;
	}
	
}
