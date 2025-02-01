import java.io.*;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1 ; test_case<=T ; test_case++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
        	int m = Integer.parseInt(st.nextToken());
        	
        	//n개 모두 1이면 true
        	boolean ans = true;
        	int tempN = (1 << n) - 1;
        	if((m | tempN) != m) ans = false;
        	
        	
        	String answer = ans ? "ON" : "OFF";
        	System.out.println("#" + test_case + " " + answer);
        }
        br.close();
    }
}