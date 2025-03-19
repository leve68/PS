import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n][m];
        for(int i = 0 ; i < n ; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0 ; j < m ; j++) {
        		matrix[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        for(int i = 1 ; i < m ; i++) {
        	dp[0][i] = dp[0][i-1] + matrix[0][i];
        }
        
        for(int i = 1 ; i < n ; i++) {
        	int[] temp1 = new int[m];
        	temp1[0] = dp[i-1][0] + matrix[i][0];
        	for(int j = 1 ; j < m ; j++) {
        		//왼쪽으로 이동해서 들어온 경우
        		temp1[j] = Math.max(temp1[j-1], dp[i-1][j]) + matrix[i][j];
        	}
        	
        	int[] temp2 = new int[m];
        	temp2[m-1] = dp[i-1][m-1] + matrix[i][m-1];
        	for(int j = m-2 ; j >= 0 ; j--) {
        		//오른쪽으로 이동해서 들어온 경우
        		temp2[j] = Math.max(temp2[j+1], dp[i-1][j]) + matrix[i][j];
        	}
        	
        	for(int j = 0 ; j < m ; j++) {
        		dp[i][j] = Math.max(temp1[j], temp2[j]);
        	}
        }
        
        System.out.println(dp[n-1][m-1]);
    }
}