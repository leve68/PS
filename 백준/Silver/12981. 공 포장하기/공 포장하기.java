import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;
        int[] input = new int[3];
        for(int i = 0 ; i<3 ; i++) {
        	input[i] = sc.nextInt();
        }
        Arrays.sort(input);
        answer += input[0];
        input[1] -= input[0];
        input[2] -= input[0];
        
        answer += input[1]/3;
        answer += input[2]/3;
        input[1] %= 3;
        input[2] %= 3;
        
        int[] t = {input[1], input[2]};
        Arrays.sort(t);

        if(t[1] != 0) {
        	if(t[0] == 0 || t[1] == 1) {
        		answer += 1;
        	}else{
        		answer += 2;
        	}
        }
        System.out.println(answer);
    }
}