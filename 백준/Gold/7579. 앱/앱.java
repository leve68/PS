import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] appMap = new int[n][2];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            appMap[i][0] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            appMap[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int sumWeight = 0;
        for (int i = 0; i < n; i++) {
            sumWeight += appMap[i][1];
        }
        
        int[][] dp = new int[n + 1][sumWeight + 1];
        
        if (appMap[0][1] == 0) {
            for (int i = 0; i < dp[1].length; i++) {
                dp[1][i] = appMap[0][0];
            }
        } else {
            dp[1][appMap[0][1]] = appMap[0][0];
        }
        
        int sum = appMap[0][1];
        for (int i = 2; i < n + 1; i++) {
            int[] current = appMap[i - 1];
            sum += current[1];
            for (int j = 0; j < sumWeight + 1; j++) {
                if (current[1] == 0) {
                    dp[i][j] = dp[i - 1][j] + current[0];
                } else if (current[1] <= j && j <= sum) {
                    dp[i][j] = Math.max(dp[i - 1][j - current[1]] + current[0], dp[i - 1][j]);
                } else if (current[1] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < sumWeight + 1; j++) {
                if (dp[i][j] >= m && j < answer) {
                    answer = j;
                }
            }
        }
        
        System.out.println(answer);
    }
}