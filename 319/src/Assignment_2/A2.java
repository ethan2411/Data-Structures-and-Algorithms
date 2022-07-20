package Assignment_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

public class A2 {

	private static BufferedReader br;
	private static int size;
	private static int[] myList;
	private static long time;
	private static double seconds;
	
	public static void main(String[] args) {
		br = new BufferedReader(new InputStreamReader(System.in));
		Boolean valid = false;
		while (valid == false) {
			try {
				System.out.print("Please select the order of integers: \n ascending or 1,"
						+ " \n descending or 2, \n random or 0 \n Enter here:");
				 String order = br.readLine();
				if(order.equalsIgnoreCase("ascending") || order.equals("1")) {
					System.out.println("The array will be ascending");
					ascNums(size());
					algorithms();
					valid = true;
				}
				else if(order.equalsIgnoreCase("descending") || order.equals("2")) {
					System.out.println("the array will be descending");
					dscNums(size());
					algorithms();
					valid = true;
				}
				else if( order.equalsIgnoreCase("random") || order.equals("0")) {
					System.out.println("the array will be random");
					randNums(size());
					algorithms();
					valid = true;
				}
				else {
					System.out.println("Please enter: \n ascending or 1, \n descending or 2, \n"
							+ " random or 0");
				}
			} catch (Exception e) {
				System.out.println(e);
				continue;
			}
		}
	}
	
	//Getting the size of the array ----------------------
	public static int size() {
		br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			try {
				System.out.println("Please enter desired size of array: ");
				size = Integer.parseInt(br.readLine());
				return size;
			} catch (Exception e) {
				System.out.println(e);
				continue;
			}
		}
	}
	
	//Checking which algorithm to use -----------------
	public static void algorithms() {
		br = new BufferedReader(new InputStreamReader(System.in));
		Boolean valid = false;
		while(valid == false) {
			try {
				System.out.println("Which algorithm would you like to use?");
				System.out.println("1) Bubble Sort \n2) Insertion Sort \n3) Selection Sort "
						+ "\n4) Merge Sort");
				String algo = br.readLine();
				if(algo.equalsIgnoreCase("Bubble") || algo.equals("1")) {
					time = System.nanoTime();
					bubbleSort(myList);
					time = System.nanoTime()-time;
					seconds = (double) time / 1_000_000_000;
					System.out.println(seconds +" seconds");
					writeNums();
					valid = true;
				}
				else if(algo.equalsIgnoreCase("Insertion") || algo.equals("2")) {
					time = System.nanoTime();
					insertionSort(myList, 0, myList.length-1);
					time = System.nanoTime()-time;
					seconds = (double) time / 1_000_000_000;
					System.out.println(seconds +" seconds");
					writeNums();
					valid = true;
				}
				else if(algo.equalsIgnoreCase("Selection") || algo.equals("3")) {
					time = System.nanoTime();
					selectionSort(myList);
					time = System.nanoTime()-time;
					seconds = (double) time / 1_000_000_000;
					System.out.println(seconds +" seconds");
					writeNums();
					valid = true;
				}
				else if(algo.equalsIgnoreCase("Merge") || algo.equals("4")) {
					time = System.nanoTime();
					mergeSort(myList, 0, myList.length-1);
					time = System.nanoTime()-time;
					seconds = (double) time / 1_000_000_000;
					System.out.println(seconds +" seconds");
					writeNums();
					valid = true;
				}
				else {
					System.out.println("Please select a valid option");
				}
			}catch(Exception e) {
				System.out.println(e);
				continue;
			}
		}
	}
	
	//Writing the numbers in the array to txt file ----------------------
	public static void writeNums() throws IOException{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("A2.txt"))) {
			PrintWriter pw = new PrintWriter(writer);
			for(int i =0; i<myList.length;i++) {
				pw.print(myList[i]+", ");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//order or numbers -----------------------------------------------
	//this method was taken from tutorial 01
	//https://github.com/stephanedorotich/319-S2022
	public static void randNums(int n) {
		Random rand = new Random();
		myList = new int[n];
		for (int i = 0; i < n; i++) myList[i] = rand.nextInt(65536);
	}
	//this method was taken from tutorial 01
	//https://github.com/stephanedorotich/319-S2022
	public static void ascNums(int n) {
		myList = new int[n];
		for (int i = 0; i < n; i++) myList[i] = i;
	}
	//this method was taken from tutorial 01
	//https://github.com/stephanedorotich/319-S2022
	public static void dscNums(int n) {
		myList = new int[n];
		int val = n;
		for (int i = 0; i < n; i++) {
		    myList[i] = val;
		    val--;
		}
	}
	
	//Sorting Algorithms --------------------------------------------------
	//bubbleSort algorithm from in class notes
	public static void bubbleSort(int[] arr) {
		for(int i=0; i<arr.length-1; i++) {
			for(int j=arr.length-1; j>i; j-- ) {
				if(arr[j]<arr[j-1]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j]=temp;
				}
			}
		}	
	}
	//insertionSort algorithm from in class notes
	public static void insertionSort(int[] arr, int lower, int upper) {
		for(int i = lower+1; i<= upper; i++) {
			int t = arr[i];
			int j =i-1;
			for(j = i-1;j>=lower && arr[j]>t; j--) {
				arr[j+1]=arr[j];
			}
			arr[j+1]=t;
		}
	}
	//selectionSort algorithm from in class notes
	public static void selectionSort(int arr[]) {
		for(int i=0; i<arr.length-1; i++) {
			int min =i;
			for(int j =i+1; j<arr.length; j++) {
				if(arr[j]<arr[min]) {
					min =j;
				}
			}
			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
	}
	
	//Implementation from Tutorials
	public static void merge(int[] arr, int begin, int mid, int end) {		
		// create temp arrays
		int lSize = mid-begin+1;
		int rSize = end-mid;
		int[] left = new int[lSize];
		int[] right = new int[rSize];
		// copy data to temp arrays
		for(int i=0; i<lSize; i++) {
			left[i] = arr[begin+i];
		}
		for(int j=0; j<rSize; j++) {
			right[j] = arr[mid+1+j];
		}
		// create initial indices for the Left, Right, and original arrays (0, 0, and begin)\
		int i=0;
		int j=0;
		// compare values in arrays and add the smaller one to the original arr
		int temp=begin;
		while(i<lSize && j<rSize) {
			if(left[i]<=right[j]) {
				arr[temp]=left[i];
				i++;
			}
			else {
				arr[temp] = right[j];
				j++;
			}
			temp++;
		}	
		// take remaining elements and place them in final array
		while(i<lSize) {
			arr[temp]=left[i];
			i++;
			temp++;
		}
		while(j<rSize) {
			arr[temp] = right[j];
			j++;
			temp++;
		}
		return;
	}
	
	public static void mergeSort(int[] arr, int begin, int end) {
		if(begin>=end) {return;}
		int middle =(end+begin)/2;
		mergeSort(arr, begin, middle);
		mergeSort(arr, middle+1, end);
		merge(arr, begin, middle, end);
	}
}