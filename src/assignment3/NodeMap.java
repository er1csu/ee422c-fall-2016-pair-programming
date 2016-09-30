// NodeMap.java
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

import java.util.Set;


/**
 * Map linking words that are only 1 letter apart.
 * @author ericsu
 * @author brianmadina
 *
 */
public class NodeMap {
	static Node[] nodeArray = new Node[]{};
	
	/**
	 * Create a node map that can be used to traverse.
	 * @param setOfNodes The map of nodes to traverse.
	 */
	public static void createNodeMap(Set<Node> setOfNodes) {
		
		NodeMap.nodeArray = setOfNodes.toArray(nodeArray);
		for (int i = 0; i < setOfNodes.size() - 1; i++)
		{
			for (int j = i + 1; j < setOfNodes.size();j++)
			{
				if(isRelated(nodeArray[i],nodeArray[j]) == true)
				{
					nodeArray[i].relatedNodes.add(nodeArray[j]);
					nodeArray[j].relatedNodes.add(nodeArray[i]);
				}
			}
		}
		
	}
	
	
	/**
	 * Compare two nodes for 1 letter diff
	 * @param i First node to compare
	 * @param j Second node to compare
	 * @return True or false if the two nodes are related
	 */
	public static boolean isRelated(Node i, Node j){
		int differentLetters = 0;
		String[] tempI = i.word.split("");
		String[] tempJ = j.word.split("");
		for(int x = 0; x < tempI.length; x++)
		{
			if(!tempI[x].equals(tempJ[x]))
			{
				differentLetters++;
			}
		}
		if(differentLetters == 1)
		{return true;}
		else{return false;}
	}
	
	/**
	 * Check if two strings are related.
	 * @param i First word
	 * @param j Second word
	 * @return True or false whether the strings are related.
	 */
	public static boolean isStringRelated(String i, String j) {
		int differentLetters = 0;
		String[] tempI = i.split("");
		String[] tempJ = j.split("");
		for(int x = 0; x < tempI.length; x++)
		{
			if(!tempI[x].equals(tempJ[x]))
			{
				differentLetters++;
			}
		}
		if(differentLetters == 1)
		{return true;}
		else{return false;}
	}	
}
