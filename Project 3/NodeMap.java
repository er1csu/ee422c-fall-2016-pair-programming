// NodeMap.java


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
