package Assignment_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.sound.sampled.Line;

//This class creates the nodes for the Binary Search Tree
class Node {
	int count;
	String word;
	Node left;
	Node right;
	
	//This is a constructor to create the Nodes
	Node (int i, String w) {
		word = w;
		count = i;
		left = null;
		right = null;
	}
}

//This class creates out Binary Search Tree and gets all of the information from it
class BinaryTree {
	private Node root;
	private int maxFreq=0;
	
	//This is  a constructor to create our BST
	BinaryTree() {
		root = null;
	}
	
	//This method returns the root of the BST
	public Node getRoot() {
		return root;
	}

	//This method method inserts a node into the BST
	public void insert(int i, String w) {
		if (root == null) root = new Node(i, w);
		else insert_node(i, w, root);
	}
	
	//This method finds where in the BST a node should be placed
	private void insert_node(int i, String w, Node parent) {
		//this line checks to see if the node should go to the left of the parent
		Boolean goLeft = checkLeft(w, parent);
		//if the node doesn't go to the left then find where it goes to the right
		if (goLeft != true) {
			if (parent.right == null) parent.right = new Node(i,w);
			else insert_node(i, w, parent.right);
		} 
		//if the node goes to the left find where it goes
		else {
			if (parent.left == null) parent.left = new Node(i,w);
			else insert_node(i, w, parent.left);
		}
	}

	//This method checks if the node should go to the left of the BST
	private Boolean checkLeft(String w, Node parent) {
		char[] temp1 = w.toCharArray();
		char[] temp2 = parent.word.toCharArray();
		//check to see which word comes first in alphabetical order
		for(int i=0; i<min(temp1.length,temp2.length); i++) {
			//if the node comes before the parent, it needs to go to the left so return true
			if(temp1[i]<temp2[i]) {
				return true;
			}
			//if the parent node comes first then return false and the node will go to the right
			else if(temp1[i]>temp2[i]) {
				return false;
			}
		}
		return false;
	}

	//This method finds the minimum length between two words
	private int min(int length, int length2) {
		if(length<length2) return length;
		return length2;
	}
	
	//This method finds the total number of nodes in the tree
	public int totalNumbOfWords(Node parent) {
		int total =1;
		//if theres no node then return 0
		if(parent == null) {
			return 0;
		}
		else {
			//adding each node to the total
			total += totalNumbOfWords(parent.left);
			total += totalNumbOfWords(parent.right);
		}
		//returning the total
		return total;
	}

	//This method finds the number of unique words in the BST
	public int uniqueWords(Node parent) {
		int total = 0;
		//if the node is not there then do nothing
		if(parent == null) {
			return 0;
		}
		//if there is a node then check to see if it is unique and add it to the total
		else {
			total+=uniqueWords(parent.left);
			total+=uniqueWords(parent.right);
			if(parent.count ==1) {
				total ++;
			}
		}
		//return the total
		return total;
	}
	
	//This method will find the nodes that have the highest frequency and print them out
	public void mostOften(Node parent) {
		if(parent != null) {
			//If a node exists check to see if the node is unique
			if(parent.count == maxFreq) {
				//print out the unique words
				System.out.println(parent.word + " = " + parent.count + " times");
			}
			//keep checking for unique words
			mostOften(parent.left);
			mostOften(parent.right);
		}
	}
	
	//This method finds the highest frequency in the BST
	public int maxFrequency(Node parent) {
		//If the node isn't there then do nothing
		if(parent == null) {
			return 0;
		}
		//check each node to find the one that has the highest frequency
		else {
			maxFrequency(parent.left);
			maxFrequency(parent.right);
			//if the frequency of the current node is higher than the current max
			//then change the max to the current frequency
			if(parent.count >maxFreq) {
				maxFreq = parent.count;
			}
		}
		//return the max frequency
		return maxFreq;
	}

	//This method finds the Node that contains a specific word
	public Node findWord(String w, Node n){
		//if the node exists then check to see if it contains the word 
		if(n != null){
        	if(n.word.equals(w)){
        		//if the node contains the word return it
        	return n;
        	//if the node does't contain the node then keep looking for the word
        	} else {
        		Node nextNode = findWord(w, n.left);
            	if( nextNode == null) {
                	nextNode = findWord(w, n.right);
            	}
            	//return the node
            	return nextNode;
        	}
    	} 
		//if the node is empty return null
		else {
    		return null;
    	}
	}

	//This method prints the BST by in-order traversal
	public void inOrder(Node parent) {
		if(parent!=null) {
			//left node
			inOrder(parent.left);
			//current node
			System.out.print(parent.word + " ");
			//right node
			inOrder(parent.right);
		}
	}

	//This method prints the BST by post-order traversal
	public void postOrder(Node parent) {
		if(parent !=null) {
			//left node
			postOrder(parent.left);
			//right node
			postOrder(parent.right);
			//current node
			System.out.print(parent.word + " ");
		}
	}

	//This method prints the BST by pre-order traversal
	public void preOrder(Node parent) {
		if(parent != null) {
			//current node
			System.out.print(parent.word + " ");
			//left node
			preOrder(parent.left);
			//right node
			preOrder(parent.right);
		}
	}

	//This method finds the maximum depth of the BST
	int maxDepth(Node node) {
		if(node == null) {
			return 0;
		}
		else {
			//Computing depth for each subtree
			int lDepth = maxDepth(node.left);
			int rDepth = maxDepth(node.right);
			//returning the larger value
			if(lDepth > rDepth) return (lDepth +1);
			else return (rDepth +1);
		}
	}
}

//This class gets user input to get the file needed to create the BST 
//and takes user input to see what information needs to be retrieved
public class CPSC319S22A4 {
	
	static ArrayList<String> data = new ArrayList<String>();
	static ArrayList<Integer> count = new ArrayList<Integer>();
	static BinaryTree myTree = new BinaryTree();
	static String filename;
	
	//This method creates a BST with the words in the text file and their frequency
	public static void makeTree() {
		while(data.size()>0) {
			myTree.insert(count.get(0), data.get(0));
			data.remove(0);
			count.remove(0);
		}
		//this prints out a summary of the tree
		summary();
	}

	//This method prints out a summary of the BST
	public static void summary() {
		//printing out total # of words, the # of unique words, word(s) that appear the most
		//and the max height of the BST
		System.out.println("Total number of words in " + filename + " = " + myTree.totalNumbOfWords(myTree.getRoot()));
		System.out.println("Number of unique words in " + filename + " = " + myTree.uniqueWords(myTree.getRoot()));
		myTree.maxFrequency(myTree.getRoot());
		System.out.println("The word(s) which ocurr(s) most often and the number of times that it/they occur = ");
		myTree.mostOften(myTree.getRoot());
		System.out.println("The maximum height of the tree = " + myTree.maxDepth(myTree.getRoot()));
		//Seeing what info the user wants
		takeInput();
	}
	
	//Finding the frequency of each word in the text file
	public static void findCount() {
		//looping through each word to see how many times it appears removing
		//duplicate words from our data and incrementing the frequencies appropriately
		for(int i=0; i<data.size(); i++) {
			count.add(1);
			for(int j=i+1; j<data.size(); j++) {
				//if two words match, increment the frequency and remove one of the duplicates
				if(data.get(i).equals(data.get(j))) {
					count.set(i, count.get(i)+1);	
					//removing repeated words
					data.remove(j);
					//making sure the index is still checked
					j--;
				}
			}
		}
	}
	
	//This method reads the chosen file and then stores each word into an arrayList
	public static void readFile() throws IOException, FileNotFoundException {
		try {
			BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("What file would you like to read?");
			//getting the file to read
			filename = file.readLine();
			BufferedReader br = new BufferedReader(new FileReader(filename));
			//reading the file
			String line = br.readLine();
			String[] words;
			while(line != null) {
				//breaking each line down into individual words
				words = line.replace("[^0-9a-zA-Z]", "").toLowerCase().split("\\W+");
				for(int i=0; i<words.length; i++) {
					//adding the words into an arrayList
					data.add(words[i]);
				}
				line = br.readLine();
			}
			//finding the frequency of each word
			findCount();
			//creating the BST
			makeTree();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//This method gets user input to see what information the user wants displayed
	public static void takeInput() {
		Boolean valid = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(valid == true) {
			try {
				System.out.println("Would you like to: \n 1) Look for a specific word \n"
						+ " 2) Display the entire Tree \n 3) Exit");
				//Seeing what the user wants
				String response = br.readLine();
				//Going to find a word
				if(response.equals("1") || response.equalsIgnoreCase("Look for a specific word")) {
					findWord();
				}
				//Going to display the tree
				else if(response.equals("2") || response.equalsIgnoreCase("Display the entire Tree")) {
					displayTree();
				}
				//exiting the program
				else if(response.equals("3") || response.equalsIgnoreCase("Exit")) {
					valid=false;
				}
				else {
					System.out.println("Please enter a valid response: Either a number 1-3 or the phrase");
				}
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	//This method finds a word at the users request and shows how many
	//times that word is in the chosen text file
	public static void findWord() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter the word you are looking for in " + filename);
			String word = br.readLine();
			//finding the node that contains the word requested
			Node n = myTree.findWord(word.toLowerCase(), myTree.getRoot());
			//if no node exists containing that word let the user know
			if(n ==null) {
				System.out.println("Word not found!");
			}
			//if there is a node that contains that word let the user know how many times it appears
			else {
				System.out.println("Found! It appears " + n.count + " times in the input text file");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//This method displays the tree by in-order, pre-order or post-order
	//traversal depending on what is selected by the user
	public static void displayTree() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER) for " + filename);
			//getting user input
			String answer = br.readLine();
			//displaying the tree by in-order traversal if thats what the user requested
			if(answer.equals("1") || answer.equalsIgnoreCase("in-order")) {
				myTree.inOrder(myTree.getRoot());
				System.out.println("");
			}
			//displaying the tree by pre-order traversal if thats what the user requested
			else if(answer.equals("2")|| answer.equalsIgnoreCase("pre-order")) {
				myTree.preOrder(myTree.getRoot());
				System.out.println("");
			}
			//displaying the tree by post-order traversal if thats what the user requested
			else if(answer.equals("3")|| answer.equalsIgnoreCase("post-order")) {
				myTree.postOrder(myTree.getRoot());
				System.out.println("");
			}
			//if they don't select one of the options let them know
			else {
				System.out.println("No valid option was selected, please try again");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//This method starts the program
	public static void main(String[] args) throws FileNotFoundException, IOException {
		readFile();
	}
}
