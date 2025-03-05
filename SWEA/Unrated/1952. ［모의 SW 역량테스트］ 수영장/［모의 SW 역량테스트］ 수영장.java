import java.io.*;
import java.util.*;
    
class Solution {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test = 1 ; test <= T ; test++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int[] price = new int[4];
            for(int i = 0 ; i < 4 ; i++) {
                price[i] = Integer.parseInt(st1.nextToken());
            }
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int[] plan = new int[12];
            for(int i = 0 ; i < 12 ; i++) {
                plan[i] = Integer.parseInt(st2.nextToken());
            }
             
            //마지막 선택을 1일권, 1달권, 3달권
            int[][] dp = new int[3][12];
            //초기값
            dp[0][0] = price[0] * plan[0];
            dp[1][0] = plan[0] != 0 ? price[1] : 0;
            dp[2][0] = plan[0] != 0 ? price[2] : 0;
            for(int i = 1 ; i < 3 ; i++) {
                dp[0][i] = Math.min(Math.min(dp[0][i-1], dp[1][i-1]), dp[2][i-1]) + price[0] * plan[i];
                dp[1][i] = Math.min(Math.min(dp[0][i-1], dp[1][i-1]), dp[2][i-1]) + price[1];
                if(plan[i] != 0 ||  dp[2][i - 1] != 0) {
                    dp[2][i] = price[2];
                }
            }
             
            for(int i = 3 ; i < 12 ; i++) {
                dp[0][i] = Math.min(Math.min(dp[0][i-1], dp[1][i-1]), dp[2][i-1]) + price[0] * plan[i];
                dp[1][i] = Math.min(Math.min(dp[0][i-1], dp[1][i-1]), dp[2][i-1]) + price[1];
                dp[2][i] = Math.min(Math.min(dp[0][i-3], dp[1][i-3]), dp[2][i-3]) + price[2];
            }
            int answer = Math.min(Math.min(dp[0][11], dp[1][11]), Math.min(dp[2][11], price[3]));
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
         
        System.out.println(sb);
    }
}