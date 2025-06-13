import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        int answer = 0;
        //펠린드롬 -1
        if(input[0] != input[input.length - 1]) {
            answer = input.length;
        } else {
            answer = -1;

            boolean isAllSame = true;
            boolean isPalindrome = true;
            for(int i = 0 ; i < input.length/2 + 1  ; i++) {
                if(input[i] != input[input.length-i-1]) {
                    isPalindrome = false;
                    answer = input.length;
                    break;
                }
            }

            for(int i = 0 ; i < input.length  ; i++) {
                if(input[0] != input[i]) {
                    isAllSame = false;
                }
            }

            if(isPalindrome && !isAllSame) {
                answer = input.length - 1;
            }
        }

        System.out.println(answer);
    }
}