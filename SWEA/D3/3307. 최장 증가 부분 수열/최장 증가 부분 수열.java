import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			int[] dp = new int[N];
			int max = 0;
			for(int i = 0 ; i < N ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				dp[i] = 1;
				for(int j = 0 ; j < i ; j++) {
					if(arr[i] > arr[j] && dp[j]+1 > dp[i]) {
						dp[i] = dp[j] + 1;
						max = Math.max(dp[i], max);
					}
				}
			}
			
			
			sb.append("#").append(test).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}
}
