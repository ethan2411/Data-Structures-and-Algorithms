package Assignment_1;

public class A1 {
	private static long a=0;
	private static long b=1;
	private static long next=a+b;
	
	
	public static void main(String[] args) {
		
		//Testing speeds
		int sum =0;
		long time;
		System.out.println("20 times");
		for(int i=0; i<10; i++) {
			time = System.nanoTime();
			fibMem(20);
			time = System.nanoTime()-time;	
			sum+=time;
		}	
		System.out.println(sum/10);
		
		//speed of 50
		System.out.println("50 times");
		for(int i=0; i<10; i++) {
			time = System.nanoTime();
			fibMem(50);
			time = System.nanoTime()-time;	
			sum+=time;
		}	
		System.out.println(sum/10);
		
		//Speed of 100
		System.out.println("100 times");
		for(int i=0; i<10; i++) {
			time = System.nanoTime();
			fibMem(100);
			time = System.nanoTime()-time;	
			sum+=time;
		}	
		System.out.println(sum/10);	
		
		//Speed of 1000
		System.out.println("1000 times");
		for(int i=0; i<10; i++) {
			time = System.nanoTime();
			fibMem(1000);
			time = System.nanoTime()-time;	
			sum+=time;
		}	
		System.out.println(sum/10);
		
		//Speed of 5000
		System.out.println("5000 times");
		for(int i=0; i<10; i++) {
			time = System.nanoTime();
			fibMem(5000);
			time = System.nanoTime()-time;	
			sum+=time;
		}	
		System.out.println(sum/10);
		
		//Speed of 10000
		System.out.println("10000 times");
		for(int i=0; i<10; i++) {
			time = System.nanoTime();
			fibMem(10000);
			time = System.nanoTime()-time;	
			sum+=time;
		}	
		System.out.println(sum/10);
	}		
	//Algorithms
	public static long fibRec(int n) {		
		
		if(n==0) {
			return  0;
		}
		if(n==1) {
			return next;
		}
		if(n>1) {
			next=a+b;
			a=b;
			b=next;
			fibRec(n-1);
		}
		a= 0;
		b= 1;
		return next;
	}
		
	public static long[] fibMem(int n) {
		if(n<=1) {
			long[] answer = {n,0};
			return answer;
		}
		else {
			long[] temp = fibMem(n-1);
			long[] answer = {temp[0]+temp[1], temp[0]};
			return answer;
 		}
	}
		

	public static long fibIter(int n) {
		long first= 0;
		long second = 1;
		long term=first+second;
		
		if(n<=1) {
			return n;
		}
		for(int i=0; i<(n-2);i++) {
			first=second;
			second=term;
			term=first+second;
		}
		return term;
	}
}
