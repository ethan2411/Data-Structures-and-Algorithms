package Assignment_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class A3 {

	static BufferedReader br;
	static BufferedWriter bw;
	static String[] input;
	static LinkedList[] sorted;
	static char[] characters;
	static String[] temp;
	
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
		try (BufferedReader temp = new BufferedReader(new FileReader(
				"C:\\Users\\ethan\\Downloads\\Cpsc 319\\Assignment #3\\example_1--8_words.txt"))) {
			String line = temp.readLine();
			int count =0;
			while(line != null) {
				count ++;
				line = temp.readLine();
			}
			return count;
		}
	}
	
	//---------------------------------------------------------------------------------------
	
	
	//MAN IDK WHATS GOING ON
//	public static void temp() throws IOException {
//		char[] temp1;
//		char[] temp2;
//		int i=0;
//		int j=1;
//		int swap;
//		temp = new String[getLines()];
//		while(i<input.length && j<input.length) {
//			temp1 = input[i].toCharArray();
//			temp2 = input[j].toCharArray();
//			Arrays.sort(temp1);
//			Arrays.sort(temp2);
//			if(Arrays.equals(temp1, temp2)) {
//				temp[i] = input[i];
//				temp[j]= input[j];
//				j++;
//			}
//			else if(!Arrays.equals(temp1, temp2)){
//				j++;
//			}
//			else {
//				swap = i;
//				i=j+1;
//				j=swap+1;
//			}
//		}
//	}
//	
	
	
	
	
	
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
//	private static void print(char[] nums) {
//		for (int i = 0; i < nums.length; i++) {
//			System.out.print(nums[i] + " ");
//		}
//	}
//	
//	private static void print(String[] nums) {
//		for (int i = 0; i < nums.length; i++) {
//			System.out.print(nums[i] + " ");
//		}
//	}
	
	public static void writeAnagrams() throws IOException{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("A3.txt"))) {
			PrintWriter pw = new PrintWriter(writer);
			for(int i =0; i<input.length;i++) {
				pw.print(input[i]+", ");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		readFile();
//		temp();
		checkWords();
	}
	
}