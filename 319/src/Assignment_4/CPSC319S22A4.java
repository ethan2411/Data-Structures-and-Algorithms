package Assignment_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.sound.sampled.Line;

//This class is from tutorials
class Node {
	int count;
	String word;
	Node left;
	Node right;
	
	Node (int i, String w) {
		word = w;
		count = i;
		left = null;
		right = null;
	}
}

//This class is from tutorials
class BinaryTree {
	private Node root;
	
	BinaryTree() {
		root = null;
	}
	
	public Node getRoot() {
		return root;
	}

	public void insert(int i, String w) {
		if (root == null) root = new Node(i, w);
		else insert_node(i, w, root);
	}
	
	private void insert_node(int i, String w, Node parent) {
		Boolean goLeft = checkLeft(w, parent);
		if (goLeft != true) {
			if (parent.right == null) parent.right = new Node(i,w);
			else insert_node(i, w, parent.right);
		} else {
			if (parent.left == null) parent.left = new Node(i,w);
			else insert_node(i, w, parent.left);
		}
	}
	
	private Boolean checkLeft(String w, Node parent) {
		char[] temp1 = w.toCharArray();
		char[] temp2 = parent.word.toCharArray();
		
		for(int i=0; i<min(temp1.length,temp2.length); i++) {
			if(temp1[i]<temp2[i]) {
				return true;
			}
			else if(temp1[i]>temp2[i]) {
				return false;
			}
		}
		return false;
	}

	private int min(int length, int length2) {
		if(length<length2) return length;
		return length2;
	}

	public void display() {
		display_node(0, root);
	}
	
	private void display_node(int depth, Node n)
	// In-order display of BinaryTree
	{
		// Recursively print out left side of tree
		if (n.left != null) display_node(depth+1, n.left);
		
		// Print 'depth' tab characters + node data
		for (int i = 0; i < depth; i++)	System.out.print("\t");
		System.out.println(n.word+ ": " + n.count);

		// Recursively print out right side of tree
		if (n.right != null) display_node(depth+1, n.right);
	}
	
	public int totalNumbOfWords(Node parent) {
		int total =1;
		if(parent == null) {
			return 0;
		}
		else {
			total += totalNumbOfWords(parent.left);
			total += totalNumbOfWords(parent.right);
		}
		return total;
	}
	
	public int uniqueWords(Node parent) {
		int total = 0;
		if(parent == null) {
			return 0;
		}
		else {
			totalNumbOfWords(parent.left);
			totalNumbOfWords(parent.right);
			if(parent.count <2) {
				total += totalNumbOfWords(parent.left);
				total += totalNumbOfWords(parent.right);
			}
			else {
				totalNumbOfWords(parent.left);
				totalNumbOfWords(parent.right);
			}
		}
		return total;
	}
	
	public void mostOften(Node parent) {
		
		
		
	}
	
	public int findWord(String w, Node parent) {
		if(parent != null) {
			if(parent.word.equals(w)) {
				return parent.count;
			}
			findWord(w, parent.left);
			findWord(w, parent.right);
		}
		return -1;
	}
	
	public void inOrder(Node parent) {
		if(parent!=null) {
			inOrder(parent.left);
			System.out.print(parent.word + " ");
			inOrder(parent.right);
		}
	}
	public void postOrder(Node parent) {
		if(parent !=null) {
			postOrder(parent.left);
			postOrder(parent.right);
			System.out.print(parent.word + " ");
		}
	}
	public void preOrder(Node parent) {
		if(parent != null) {
			System.out.print(parent.word + " ");
			preOrder(parent.left);
			preOrder(parent.right);
		}
	}
	
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


public class CPSC319S22A4 {
	
	static ArrayList<String> data = new ArrayList<String>();
	static ArrayList<Integer> count = new ArrayList<Integer>();
	static BinaryTree myTree = new BinaryTree();
	
	public static void makeTree() {
		while(data.size()>0) {
			myTree.insert(count.get(0), data.get(0));
			data.remove(0);
			count.remove(0);
		}
		System.out.println("Tree has been made");
		myTree.display();
		System.out.println("TOTAL NUMBER OF WORDS");
		System.out.println(myTree.totalNumbOfWords(myTree.getRoot()));
		System.out.println("UNIQUE WORDS");
		System.out.println(myTree.uniqueWords(myTree.getRoot()));
		System.out.println("MAX HEIGHT");
		System.out.println(myTree.maxDepth(myTree.getRoot()));
		
		
	}
	
	public static void findCount() {
		
		for(int i=0; i<data.size(); i++) {
			count.add(1);
			for(int j=i+1; j<data.size(); j++) {
				if(data.get(i).equals(data.get(j))) {
					count.set(i, count.get(i)+1);						
					data.remove(j);
					j--;
				}
			}
		}
//		System.out.println(data);
//		System.out.println(count);
	}
	
	
	public static void readFile(String filename) throws IOException, FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		String[] words;
		while(line != null) {
			words = line.replace("[^0-9a-zA-Z]", "").toLowerCase().split("\\W+");
			for(int i=0; i<words.length; i++) {
				data.add(words[i]);
			}
			line = br.readLine();
		}
	}
	public static void takeInput(String file) {
		Boolean valid = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(valid == true) {
			try {
				System.out.println("Would you like to: \n 1) Look for a specific word \n"
						+ " 2) Display the entire Tree \n 3) Exit");
				String response = br.readLine();
				if(response.equals("1") || response.equalsIgnoreCase("Look for a specific word")) {
					findWord(file);
				}
				else if(response.equals("2") || response.equalsIgnoreCase("Display the entire Tree")) {
					displayTree(file);
				}
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
	
	public static void findWord(String file) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter the word you are looking for in " + file);
			String word = br.readLine();
			int count = myTree.findWord(word, myTree.getRoot());
			if(count ==-1) {
				System.out.println("Word not found!");
			}
			else {
				System.out.println("Found! It appears " + count + " times in the input text file");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void displayTree(String file) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER) for " + file);
			String answer = br.readLine();
			if(answer.equals("1") || answer.equalsIgnoreCase("in-order")) {
				myTree.inOrder(myTree.getRoot());
				System.out.println("");
			}
			else if(answer.equals("2")|| answer.equalsIgnoreCase("pre-order")) {
				myTree.preOrder(myTree.getRoot());
				System.out.println("");
			}
			else if(answer.equals("3")|| answer.equalsIgnoreCase("post-order")) {
				myTree.postOrder(myTree.getRoot());
				System.out.println("");
			}
			else {
				System.out.println("No valid option was selected, please try again");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filename;
		filename = "C:\\Users\\ethan\\Downloads\\Cpsc 319\\Assignment #4\\example02.txt";
		readFile(filename);
		findCount();
		makeTree();
		takeInput(filename);
	}
}
