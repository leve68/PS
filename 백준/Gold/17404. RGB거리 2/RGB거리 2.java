import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] costMatrix = new int[n][3];
		for(int i = 0 ; i<n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<3 ; j++) {
            	costMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
		}
		
		//1번은 2, n과 다른색
		//n번은 n-1, 1과 다른색
		//i번은 i-1, i+1과 다른색
		//costMatirx[i번째 집][0, 1, 2] 에서 0,1,2 중 선택
		//1번째와 n번째에 뭘 선택했는지 추적해야함
		int min = Integer.MAX_VALUE;
		int[][] dp = new int[n][3];
		dp[0] = costMatrix[0].clone();
		
		for(int start = 0 ; start<3 ; start++) {
			dp[0] = new int[] {costMatrix[0][start], costMatrix[0][start], costMatrix[0][start]};
			dp[1][0] = Math.min(dp[0][1] + costMatrix[1][0], dp[0][2] + costMatrix[1][0]);
			dp[1][1] = Math.min(dp[0][0] + costMatrix[1][1], dp[0][2] + costMatrix[1][1]);
			dp[1][2] = Math.min(dp[0][0] + costMatrix[1][2], dp[0][1] + costMatrix[1][2]);
			dp[1][start] = 1000000;
			
			for(int i = 2 ; i < n ; i++) {
				dp[i][0] = Math.min(dp[i-1][1]+costMatrix[i][0], dp[i-1][2]+costMatrix[i][0]);
				dp[i][1] = Math.min(dp[i-1][0]+costMatrix[i][1], dp[i-1][2]+costMatrix[i][1]);
				dp[i][2] = Math.min(dp[i-1][0]+costMatrix[i][2], dp[i-1][1]+costMatrix[i][2]);
			}
						
			int index = 0;
			int[] temp = new int[2];
			for(int i = 0 ; i<3 ; i++) {
				if(i == start) continue;
				temp[index++] = i;
			}
			
			int answer = Math.min(dp[n-1][temp[0]], dp[n-1][temp[1]]);
			min = Math.min(answer, min);
		}
		
		System.out.println(min);
	}
}