import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] dist = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					dist[i][j] = Integer.parseInt(st.nextToken());
					if(dist[i][j] == 0 && i != j) dist[i][j] = 1_000;
				}
			}
			
			for(int k = 0 ; k < N ; k++) {
				for(int i = 0 ; i < N ; i++) {
					for(int j = 0 ; j < N ; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
			
			int minSum = 1_000_000;
			for(int i = 0 ; i < N ; i++) {
				int curSum = 0;
				for(int j = 0 ; j < N ; j++) {
					curSum += dist[i][j];
				}
				minSum = Math.min(curSum, minSum);
			}
			
			sb.append("#").append(test).append(" ").append(minSum).append("\n");
		}
		System.out.println(sb);
	}
}