/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Eric Su
 * es25725
 * 16475
 * Brian Madina
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL: 
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	static ArrayList<String> wordLadder;
	
	// static variables and constants only here.
	static ArrayList<String> words;
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout			
		}
		initialize();
		words = Main.parse(kb);
		//getWordLadderBFS(words.get(0), words.get(1));
		getWordLadderDFS(words.get(0), words.get(1));

		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		Main.wordLadder = new ArrayList<String>();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String words = keyboard.nextLine();
		String [] splitWords = words.split(" ");
		String word1 = splitWords[0];		
		String word2 = splitWords[1];
		ArrayList<String> wordsArray = new ArrayList<String>();
		wordsArray.add(0, word1);
		wordsArray.add(1, word2);
		
		return wordsArray;
	}
	
	/**
	 * 
	 * @param start Starting word
	 * @param end Ending word
	 * @return Word ladder 
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		Set<String> dict = makeDictionary();
		Set<Node> nodeSet = Node.convertToNodes(dict);
		NodeMap.createNodeMap(nodeSet);
		Node[] nodeArray = new Node[] {};
		nodeArray = nodeSet.toArray(nodeArray);
		int startIndex = 0; 
		
		for (int i = 0; i < nodeArray.length; i++) {
			if (nodeArray[i].word.equals(start)) {
				startIndex = i; 
				break; 
			}
		}
		
		Node startNode = nodeArray[startIndex];
		
		boolean ladderExists = doDFS(startNode, end, nodeArray);
		
		if (ladderExists == true) {
			return Main.wordLadder;
		}
		else {
			Main.wordLadder.clear();
			return Main.wordLadder;
		}		
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	ArrayList<String> path = new ArrayList<String>();
		int startIndex = 0;
		// TODO some code
		Set<String> dict = makeDictionary();
		Set<Node> nodeSet = Node.convertToNodes(makeDictionary());
		NodeMap.createNodeMap(nodeSet);
		Node[] nodeArray = new Node[]{};
		nodeArray = nodeSet.toArray(nodeArray);
		for(int i = 0; i < nodeArray.length ; i++)
		{
			if(nodeArray[i].word.equals(start))
			{
				startIndex = i;
				break;
			}
		}
		Queue<Node> queue = new LinkedList<>();
		ArrayList<Node> nodeArrayList = new ArrayList<Node>(Arrays.asList(nodeArray));
		queue.add(nodeArrayList.get(startIndex));
		
		

		while(queue.size() != 0)
		{
			Node head = queue.peek();
			int size = head.relatedNodes.size();
			if (head.word.equals(end))
			{
				path.add(head.word);
				while(head.parent != null)
				{
					path.add(head.parent.word);
					head = head.parent;
				}
				return path;
			}
			if(head.isMarked == 2)
			{
				queue.poll();
			}
			else
			{
				head.isMarked = 2;
				for(int i = 0; i < size; i++)
				{
					if(head.relatedNodes.get(i).isMarked != 1 && head.relatedNodes.get(i).isMarked != 2)
					{
							head.relatedNodes.get(i).parent = head;
							head.relatedNodes.get(i).isMarked = 1;
							queue.add(head.relatedNodes.get(i));
						
					}
				}

			}
		}

		
		// TODO more code
		path.clear();
		return path;
	}
    
    /**
     * Clean up the path for BFS elements not connected.
     * @param path Path to be cleaned
     */
    public static void cleanPath(ArrayList<String> path) {
    	for (int i = 1; i < path.size(); i++) {
    		if (NodeMap.isStringRelated(path.get(i), path.get(i-1))==false){
    			path.remove(i-1);
    			i--;
    		}
    	}
    }
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		
	}
	// TODO
	// Other private static methods here
	
	/**
	 * 
	 * @param startNode The starting node
	 * @param end The string we wish to create a ladder
	 * @param nodeArray Array of nodes to traverse
	 * @return Boolean whether a ladder could be made
	 */
	private static boolean doDFS(Node startNode, String end, Node[] nodeArray) {
		if (startNode == null) {
			return false;
		}
		// Mark visited
		startNode.isMarked = 1;
		
		if (startNode.word.equals(end)) {
			Main.wordLadder.add(startNode.word);
			return true;
		} 
		else {
			// Add the word to the ladder
			// Main.wordLadder.add(startNode.word);
			int i;
			for (i = 0; i < startNode.relatedNodes.size(); i++) {
				Node neighbor = startNode.relatedNodes.get(i);
				if (neighbor.isMarked == 0) {
					boolean found = doDFS(neighbor, end, nodeArray);
					if (found == true) {
						Main.wordLadder.add(startNode.word);
						return true;
					}
				}				
			}
			//Main.wordLadder.remove(Main.wordLadder.size() - 1);
			return false;
		}
	}
}
