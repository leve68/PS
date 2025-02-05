import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int INF = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = 1;
		int n = Integer.parseInt(br.readLine());
		while(n != 0) {			
			if(n == 0) break;
			
			int[][] matrix = new int[n][n];
			for(int i = 0 ; i < n ; i++) {
				matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(element -> Integer.parseInt(element)).toArray();
			}
			int[][] visited = new int[n][n];
			for(int i = 0 ; i < n ; i++) {
				Arrays.fill(visited[i], INF);
			}
			
			PriorityQueue<int[]> pQ = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[2], b[2]));
			int[] start = {0,0};
			int[] end = {n-1, n-1};
			pQ.add(new int[] {0, 0, matrix[0][0]});
			visited[0][0] = matrix[0][0];
			
			while(!pQ.isEmpty()) {
				int[] cur = pQ.poll();
				if(visited[cur[0]][cur[1]] < matrix[cur[0]][cur[1]]) continue;
				
				for(int i = 0 ; i < 4 ; i++) {
					int nextRow = cur[0] + d[i][0];
					int nextCol = cur[1] + d[i][1];
					if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
						if(matrix[nextRow][nextCol] + visited[cur[0]][cur[1]] < visited[nextRow][nextCol]) {
							int cost = matrix[nextRow][nextCol] + cur[2];
							pQ.add(new int[] {nextRow, nextCol, cost});
							visited[nextRow][nextCol] = cost;
						}
					}
				}
			}
			System.out.println("Problem " + count + ": "+ visited[end[0]][end[1]]);
			n = Integer.parseInt(br.readLine());
			count++;
		}
	}
}
