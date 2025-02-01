import java.io.*;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        int target = (1 << 10) - 1;
        for(int test_case = 1 ; test_case<=T ; test_case++) {
        	int n = Integer.parseInt(br.readLine());
        	int current = 0;
        	int count = 1;
        	
        	 while (current != target) {
                 int curN = n * count;
                 int temp = curN;

                 while (temp > 0) {
                     int num = temp % 10;
                     current |= (1 << num);
                     temp /= 10;
                 }
                 count++;
             }
        	 
        	 System.out.println("#" + test_case + " " + n * (count - 1));
        }
        br.close();
    }
}
