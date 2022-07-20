package Assignment_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

public class CPSC319S22A3 {

	static BufferedReader br;
	static BufferedWriter bw;
	static String[] input;
	static LinkedList[] sorted;
	static char[] characters;
	
	//Getting strings from files and putting them into a sorted array
	public static void readFile() throws IOException{
		br = new BufferedReader(new FileReader(
				"C:\\Users\\ethan\\Downloads\\Cpsc 319\\Assignment #3\\example_1--8_words.txt"));
		input = new String[getLines()];
		String line = br.readLine();
		int index =0;
		//putting the strings into the array
		while(line != null) {
			input[index] = line;
			index++;
			line=br.readLine();
		}
		//this sorts them into alphabetical order
		Arrays.sort(input);
	}
	
	//finding out how many lines are in the file to size the array properly 
	public static int getLines() throws IOException{
		BufferedReader temp = new BufferedReader(new FileReader(
				"C:\\Users\\ethan\\Downloads\\Cpsc 319\\Assignment #3\\example_1--8_words.txt"));
		String line = temp.readLine();
		int count =0;
		while(line != null) {
			count ++;
			line = temp.readLine();
		}
		return count;
	}
	
	//---------------------------------------------------------------------------------------
	//These methods are definitely not complete
	public static void checkWords() {
		char[] temp1;
		char[] temp2;
		for(int i=0; i<input.length; i++) {
			temp1 = input[i].toCharArray();
			temp2 = input[i].toCharArray();
			if(temp1.length==temp2.length) {
				checkAnagrams(temp1, temp2);
			}
		}
	}
	
	public static void checkAnagrams(char[] temp1, char[] temp2) {
		Arrays.sort(temp1);
		Arrays.sort(temp2);
		Boolean yes=true;
		for(int i=0; i<temp1.length;i++) {
			if(temp1[i]!=temp2[i]) {
				yes=false;
			}
		}
		if(yes==true) {
			//add to linked list
			
		}
	}
	//-----------------------------------------------------------------------------------
	
	//Helping me print out the arrays
	private static void print(char[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}
	
//	public static void writeAnagrams() throws IOException{
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter("A3.txt"))) {
//			PrintWriter pw = new PrintWriter(writer);
//			for(int i =0; i<input.length;i++) {
//				pw.print(input[i]+", ");
//			}
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		readFile();
		checkWords();
	}
	
}

//-------------------------------------------------------------------------------------------
//This class was implemented during Tutorial 01
class Node{
	private int data;
	private Node next;
	
	public Node(int data)
	// Constructor
	{
		this.data=data;
	}
	
	public int getData() {
		return data;
	}
	
	public Node getNextNode()
	// Returns the next node in the linked list
	{
		return this.next;
	}
	
	public void setNextNode(Node next)
	// Sets the next node in the linked list
	{
		this.next = next;
	}
	
	public void print()
	// Prints this nodes data value
	{
		System.out.printf("%d  -->  ", this.data);
	}
	
	
}

//-----------------------------------------------------------------------------------------------
//This class was implemented during tutorial 01
class LinkedList {
	private Node head;
	
	public LinkedList()
	// Constructor
	{
		this.head = null;
	}
	
	public void insertHead(int val)
	// Inserts a new node at the beginning of the Linked List
	{
		Node newNode = new Node(val);
		newNode.setNextNode(head);
		this.head = newNode;
	}
	
	public void delete(int val)
	// Delete the first node whose data is equal to val
	{
		// case 1: list is empty
		
		// case 2: delete head
		
		// case 3: check other nodes
		// case 3a: value not found
		// case 3b: value found, was tail
		// case 3c: value found
		return;
	}
	
	public void display()
	//	Prints out the data of all Nodes in the Linked List
	{
		Node curr = head;
		while(curr != null) {
			curr.print();
			curr= curr.getNextNode();
		}
	}
}


