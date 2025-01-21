
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        
        int answer = 0;
        int k = sc.nextInt();
        int[] input = new int[k];
        for(int i = 0 ; i<k ; i++) {
        	input[i] = sc.nextInt();
        }
        Arrays.sort(input);
        answer += input[0];
        for(int i = 1 ; i<k ; i++) {
        	input[i] -= input[0];
        }
        if(k == 1) {
            System.out.println(answer);
            return;
        }
        
        //rest 0~k-2 //length: k-1
        int[] rest = new int[k-1];
        for(int i = 1 ; i<k ; i++) {
        	rest[i-1] = input[i] % k;
        	answer += input[i]/k;
        }
        
        Arrays.sort(rest);
        int min = k-1;
        int count = 0;
        while(count < k - 1) {
        	//rest[count] 내 공 개수
        	//(k-2) - count: 남은 색 개수
        	min = Math.min(rest[count] + k-2 - count, min);
        	count++;
        }
        System.out.println(answer + min);
    }
}