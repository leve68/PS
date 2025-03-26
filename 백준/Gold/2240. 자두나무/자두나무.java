import java.io.*;
import java.util.*;

public class Main {
	public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        //현재 시간, 이동한 횟수, 현재 위치1 or 2
        int[][][] dp = new int[T+1][W+1][3];
        for(int i = 0 ; i <= W ; i++) {
        	dp[0][i][1] = 0;
        	dp[0][i][2] = Integer.MIN_VALUE;
        }
        
        for(int t = 1 ; t <= T ; t++) {
        	int num = Integer.parseInt(br.readLine());
        	if(num == 1) {
        		dp[t][0][1] = dp[t-1][0][1] + 1;
        		dp[t][0][2] = dp[t-1][0][2];
        	}
        	else {
        		dp[t][0][1] = dp[t-1][0][1];
        		dp[t][0][2] = dp[t-1][0][2] + 1;
        	}
        	for(int i = 1 ; i <= W ; i++) {
        		if(num == 1) {
        			dp[t][i][1] = Math.max(dp[t-1][i][1] + 1, dp[t-1][i-1][2] + 1);
        			dp[t][i][2] = Math.max(dp[t-1][i][2], dp[t-1][i-1][1]);
        		} else {
        			dp[t][i][1] = Math.max(dp[t-1][i][1], dp[t-1][i-1][2]);
        			dp[t][i][2] = Math.max(dp[t-1][i][2] + 1, dp[t-1][i-1][1] + 1);
        		}
        	}
        }
        
        int max = 0;
        for(int i = 0 ; i <= W ; i++) {
        	max = Math.max(max, Math.max(dp[T][i][1], dp[T][i][2]));
        }
        System.out.println(max);
    }
}