import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = new String[3];
		for(int i = 0 ; i < 3 ; i++) {
			input[i] = br.readLine();
		}
		int num = 0;
		
		if(isInteger(input[0])) {
			num = Integer.parseInt(input[0]) + 3;
		} else if(isInteger(input[1])) {
			num = Integer.parseInt(input[1]) + 2;
		} else {
			num = Integer.parseInt(input[2]) + 1;
		}
		
		if(num % 3 == 0 && num % 5 == 0) {
			System.out.println("FizzBuzz");
		} else if (num % 3 == 0) {
			System.out.println("Fizz");
		} else if (num % 5 == 0) {
			System.out.println("Buzz");
		} else {
			System.out.println(num);
		}
		
	}
	
	public static boolean isInteger(String strValue) {
	    try {
	      Integer.parseInt(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	  }
}
