import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] request = Arrays.stream(br.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
		int T = input[0]; //티셔츠 묶음
		int P = input[1]; //팬 묶음
		
		int tAnswer = 0;
		
		for(int i = 0 ; i < request.length ; i++) {
			tAnswer += (request[i]/T + 1);
			if(request[i]%T == 0) tAnswer--;
		}
		
		int pAnswer1 = N/P;
		int pAnswer2 = N%P;
		
		System.out.println(tAnswer);
		System.out.println(pAnswer1 + " " + pAnswer2);
	}
}
