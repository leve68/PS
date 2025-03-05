import java.io.*;
import java.util.*;
 
public class Solution {
    public static int N;
    public static int[][] distance;
    public static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test = 1 ; test <= T ; test++) {
            N = Integer.parseInt(br.readLine());
            int[] X = new int[N+2];
            int[] Y = new int[N+2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N+2 ; i++) {
                X[i] = Integer.parseInt(st.nextToken());
                Y[i] = Integer.parseInt(st.nextToken());
            }
             
             
            //0 -> 2~N+1 -> 1
            //i -> j 까지의 거리
            distance = new int[N+2][N+2];
            for(int i = 0 ; i < N+2 ; i++) {
                for(int j = 0 ; j < N+2 ; j++) {
                    distance[i][j] = Math.abs(X[i] - X[j])+Math.abs(Y[i] - Y[j]);
                }
            }
            dp = new int[N+2][1 << N+2];
            for(int i = 0 ; i < N+2 ; i++) {
                for(int j = 0 ; j < (1<<N+2) ; j++) {
                    dp[i][j] = -1;
                }
            }
             
            int answer = tsp(0,1);
             
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
     
    static int tsp(int current, int visited) {
        //전부 방문했다면
        if(visited == ((1 << N+2)-1) - (1 << 1)) {
            return distance[current][1];
        }
         
        if(dp[current][visited] != -1) return dp[current][visited];
         
        dp[current][visited] = Integer.MAX_VALUE;
         
        for(int next = 2 ; next < N+2 ; next++) {
            if(((visited) & (1<<next)) == 0) {
                dp[current][visited] = Math.min(dp[current][visited], distance[current][next] + tsp(next, visited | (1 << next)));
            }
        }
        return dp[current][visited];
    }
}