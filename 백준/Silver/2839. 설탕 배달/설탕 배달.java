import java.io.*;
import java.util.*;
 
class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //3 or 5
        int[] dp = new int[N+1];
        dp[0] = -1;
        dp[1] = -1;
        dp[2] = -1;
        
        if(N >= 3) dp[3] = 1;
        if(N >= 4) dp[4] = -1;
        if(N >= 5) dp[5] = 1;
        
        for(int i = 6 ; i <= N ; i++) {
        	int temp1 = dp[i-3] + 1;
        	int temp2 = dp[i-5] + 1;
        	if(temp1 != 0 && temp2 != 0) {
        		dp[i] = Math.min(temp1, temp2);
        	} else if(temp1 == 0 && temp2 == 0) {
        		dp[i] = -1;
        	} else if(temp1 == 0) {
        		dp[i] = temp2;
        	} else {
        		dp[i] = temp1;
        	}
        }
        
        System.out.println(dp[N]);
    }
}