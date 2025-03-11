import java.io.*;
import java.util.*;

public class Main {
	public static int[][] d = {{0, 1},{1, 0},{0, -1},{-1, 0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] matrix = new int[N][M];
		int[][] ids = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(e -> -Integer.parseInt(e)).toArray();
		}
		
		int id = 0;
		ArrayList<int[]> history;
		ArrayDeque<int[]> q;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(matrix[i][j] == 0) {
					id++;
					history = new ArrayList<int[]>();
					q = new ArrayDeque<int[]>();
					history.add(new int[] {i, j});
					q.add(new int[] {i, j});
					matrix[i][j] = 1;
					
					while(!q.isEmpty()) {
						int[] c = q.pollFirst();
						
						for(int[] cd : d) {
							int ni = c[0] + cd[0];
							int nj = c[1] + cd[1];
							if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
							if(matrix[ni][nj] == 0) {
								q.add(new int[] {ni, nj});
								history.add(new int[] {ni, nj});
								matrix[ni][nj] = 1;
							}
						}
					}
					
					for(int[] h : history) {
						matrix[h[0]][h[1]] = history.size();
						ids[h[0]][h[1]] = id;
					}
				}
			}
		}
		ArrayList<Integer> usedIds;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(matrix[i][j] == -1) {
					usedIds = new ArrayList<Integer>();
					int sum = 1;
					for(int[] cd : d) {
						int ni = i + cd[0];
						int nj = j + cd[1];
						if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
						if(matrix[ni][nj] != -1) {
							if(!usedIds.contains(ids[ni][nj])) {
								sum += matrix[ni][nj];
								usedIds.add(ids[ni][nj]);
							}
						}
					}
					sb.append(sum%10);
				} else {
					sb.append(0);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
