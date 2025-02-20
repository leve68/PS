import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int T = Integer.parseInt(br.readLine());
        for(int test = 1 ; test <= T ; test++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = input[0];
            int l = input[1];
             
            int[] score = new int[n];
            int[] kal = new int[n];
            for(int i = 0 ; i < n ; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                kal[i] = Integer.parseInt(st.nextToken());
            }
             
            //dp[진행한 개수][칼로리 제한]
            int[][] dp = new int[n][l+1];
            for(int i = 0 ; i < n ; i++) {
                dp[i][0] = 0;
            }
            for(int i = 0 ; i <= l ; i++) {
                if(i < kal[0]) dp[0][i] = 0;
                else dp[0][i] = score[0];
            }
             
            for(int i = 1 ; i < n ; i++) {
                for(int j = 1 ; j <= l ; j++) {
                    //j는 칼로리, i는 재료 번호
                    if(kal[i] > j) dp[i][j] = dp[i-1][j];
                    else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-kal[i]]+score[i]);
                }
            }
             
            System.out.println("#" + test + " " + dp[n-1][l]);
        }        
    }
}