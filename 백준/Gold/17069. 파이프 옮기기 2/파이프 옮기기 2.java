import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        long answer = 0;
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0 ; j < N ; j++) {
        		matrix[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        //0 가로 1 세로 2 대각선
        long[][][] dp = new long[N][N][3];
        for(int i = 1 ; i < N ; i++) {
        	if(matrix[0][i] == 1) break;
        	dp[0][i][0] = 1;
        }
        
        for(int i = 1 ; i < N ; i++) {
        	for(int j = 2; j < N ; j++) {
        		if(matrix[i][j] == 1) continue;
        		//가로
        		dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
        		//세로
        		dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
        		//대각선
        		if(matrix[i-1][j] == 1 || matrix[i][j-1] == 1) continue;
        		dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
        	}
        }
        
        answer = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
        System.out.println(answer);
    }
}