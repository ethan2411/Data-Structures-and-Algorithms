package Exercises;

public class HelloWorld {
	
	public static void myFunc() {
		for(int i=1; i<=10; i++) {
			System.out.println(i);
		}
		
		int n=1;
		while(n<=10) {
			System.out.println(n);
			n++;
		}	
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		myFunc();
	}
	
}
