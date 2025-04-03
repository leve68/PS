import java.io.*;
import java.util.*;

public class Solution {
	static class Point {
		int row;
		int col;
		
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	static int[][] d = {{0, 1},{1, 0},{0, -1},{-1, 0}};
	static int N;
	static int K;
	static int[][] matrix;
	static int[][] v;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			matrix = new int[N][N];
			int max = 0;
			List<Point> s = new ArrayList<Point>();
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
					if(matrix[i][j] > max) {
						max = matrix[i][j];
						s.clear();
						s.add(new Point(i, j));
					} else if(matrix[i][j] == max) {
						s.add(new Point(i, j));
					}
				}
			}
			for(Point cs : s) {
				v = new int[N][N];
				v[cs.row][cs.col] = 1;
				dfs(false, cs);
			}
			
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(boolean used, Point cur) {
		boolean isEnd = true;
		for(int i = 0 ; i < 4 ; i++) {
			int nr = cur.row + d[i][0];
			int nc = cur.col + d[i][1];
			
	        if(nr>=0 && nr<N && nc>=0 && nc<N && v[nr][nc] == 0) {
	            if(matrix[nr][nc] < matrix[cur.row][cur.col]) {
	                isEnd = false;
	                
	                v[nr][nc] = v[cur.row][cur.col] + 1;
	                dfs(used, new Point(nr, nc));
	                v[nr][nc] = 0;
	            } else if(!used && matrix[nr][nc] - K < matrix[cur.row][cur.col]) {
	                isEnd = false;
	                
	                int temp = matrix[nr][nc];
	                matrix[nr][nc] = matrix[cur.row][cur.col] - 1;
	                
	                v[nr][nc] = v[cur.row][cur.col] + 1;
	                dfs(true, new Point(nr, nc));
	                v[nr][nc] = 0;
	                matrix[nr][nc] = temp;
	            }
	        }
	    }
		if(isEnd) answer = Math.max(answer, v[cur.row][cur.col]);
	}
}
