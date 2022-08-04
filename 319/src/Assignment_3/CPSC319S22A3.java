// CPSC319 - Assignment 3
/* This skeleton code contains:
 * 		class CPSC319S22A3:
 * 			TODO You *must* write your solution in findAnagrams(). Treat it like your main() method.
 *			Write new methods as needed.
 *		class Node & LinkedList:
 *			Implement a singly linked list using these classes.
 *			TODO You need to implement insertTail() & display()
 *			You may write new methods and use a different insertion method if desired.
 *
 *
 *		The return value from findAnagrams() is your solution.
 *		You should return LIST B as per the assignment specifications.
 *
 *		If you have any questions, please ask on discord!
 */

package Assignment_3; // Update Me

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//This class creates Nodes for the Linked List and has methods to manipulate the Nodes
class Node {
	String word; 	// DO NOT MODIFY
	Node next; 		// DO NOT MODIFY
	
	//this method is a constructor, it creates the Node
	public Node(String word)
	{
		this.word = word;
		this.next = null;
	}
	
	//this method gets the next node
	public Node getNext() {		
		return this.next;
	}
	
	//this method sets the next node
	public void setNext(Node n) {
		this.next = n;
	}
	
	//this method gets the word stored in the node
	public String getWord() {
		return word;
	}
	
	//this method prints the word in the node
	public void print() {
		System.out.print(this.word +" ");
	}
}

//This class creates Linked Lists and has methods to modify the Linked Lists
class LinkedList {
	Node head; 		// DO NOT MODIFY
	Node tail;
	
	//this method is a constructor, it creates an empty linked list
	public LinkedList()
	{
		this.head = null;
		this.tail = null;
	}
	
	//this method inserts a node at the tail of the linked list
	public void insertTail(String word)
	{
		Node n = new Node(word);
		if(head == null) {
			this.head = n;
		}
		else if(tail == null){
			this.tail=n;
			this.head.next = n;
		}
		else {
			tail.setNext(n);
		}
		this.tail = n;
	}
	
	//This method prints all the words in the linked list
	public void display()
	{
		Node curr = head;		
		while(curr != null) {
			curr.print();
			curr= curr.getNext();
		}
	}
}

//This class contains the methods to get a list of words and sort them into anagrams
public class CPSC319S22A3 {

	static ArrayList<String> input = new ArrayList<String>();
	static ArrayList<LinkedList> output = new ArrayList<LinkedList>();
	static int index =0;
	
	//This method calls the methods necessary to sort the words into anagrams
	//and then returns the sorted ArrayList
	public static ArrayList<LinkedList> findAnagrams(String filename) throws IOException	
	{		
		readFiles(filename);
		createLL();
		compareWords();
		showOutput();
		return output;
	}
	
	//This method prints the ArrayList of Linked Lists of anagrams
	public static void showOutput() {
		for(int i=0; i<output.size(); i++) {
			output.get(i).display();
			System.out.println(" ");
		}
	}
	
	//This method compares two words to see if they are anagrams if will take the
	//first word in the Linked List in the output Array List and compare it to the
	//first word in the input Array List. If they are not anagrams it will loop through
	//the output Array List until there are no more Linked Lists to check
	public static void compareWords() {		
		char[] second = input.get(0).toCharArray();
		//Check is False if the word isn't an anagram and True if the word is an anagram
		Boolean check=false;
		//Index is used to figure out which Linked List to add a word to if it is an anagram
		index=0;
		for(int i=0; i<output.size(); i++) {
			//if the comparison is not an anagram compare the input word to the next word
			//in the Linked List, if it is an anagram we can skip the comparison
			if(check == false) {
				index++;
				char[] first = output.get(i).head.getWord().toCharArray();
				//if the lengths are the same check if they're anagrams,
				//if they're not there's no point in checking
				if(first.length == second.length) {
					Arrays.sort(first);
					Arrays.sort(second);
					//pass the sorted words to check if they are anagrams
					check = areAnagrams(first,second);
				}
				else {
					check = false;
				}
			}
		}
		//Add word to an existing linked list or create a new linked list
		addOrCreate(check);
	}
	
	//This method loops through to character arrays and compares each character if the
	//words are anagrams if will return true, if they are not anagrams it will return false
	public static Boolean areAnagrams(char[] first, char[] second) {
		for(int i=0; i<first.length ;i++) {
			if(first[i] != second[i]) {
				return false;				
				}
		}
		return true;
	}
	
	// this method is used to create a new Linked List for an anagram group,
	// if one does not already exist or add a Node to a Linked List if a word belongs in
	//an existing group of anagrams. Boolean check is used to check if the word is an anagram
	//if check == true, it is an anagram and a node needs to be added, if check == false
	//the word is not an anagram and a Linked List must be created
	public static void addOrCreate(Boolean check)
	{
		//if check is true the word belongs to an anagram group, so add a node
		if(check == true) {
			addNode();
		}
		//otherwise create a new Linked List
		else {
			createLL();
		}
		//Then continue to compare words until there are no more words
		//in the input array to compare
		while(input.isEmpty()==false) {
			compareWords();
		}
	}
	
	//This method creates a new Linked List inside the output Array List
	//It adds the word taken from the input Array List and creates a node with that word
	//then adds it to the Linked List inside the output Array List, then removes
	//the word from the input Array List
	public static void createLL() {
		LinkedList temp = new LinkedList();
		Node n = new Node(input.get(0));
		temp.head = n;
		output.add(temp);
		input.remove(0);
	}
	
	//This method adds a word to an existing Linked List in the output Array List
	//Then it removes the word from the input Array List
	public static void addNode() {
		output.get(index-1).insertTail(input.get(0));
		input.remove(0);
	}
	
	//This method takes a filename and takes each line in the file and adds it
	//to an input Array List, then it sorts the ArrayList in alphabetical order
	public static void readFiles(String filename) throws IOException, FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		while(line!= null) {
			input.add(line);
			line = br.readLine();
		}
		Collections.sort(input);
	}
	
	// !!! DO *NOT* WRITE YOUR SOLUTION IN THIS METHOD !!!
	public static void main(String[] args) throws IOException {

		// You can modify this section for testing purposes.
		// =======================================================
		String filename;
		filename = "C:\\Users\\ethan\\Downloads\\Cpsc 319\\Assignment #3\\example_3--19_words.txt";
		
		// ======================================================
		ArrayList<LinkedList> result = findAnagrams(filename);
	}
}