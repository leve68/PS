import java.io.*;
import java.util.*;
public class Solution {
	static class Point {
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	};
	public static int N;
	public static boolean[][] matrix;
	public static List<Point> points;
	public static int min;
	public static int maxConnected;
	public static int[][] d = {{0, 1},{1, 0},{0, -1},{-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int test = 1 ; test <= T ; test++) {
        	min = Integer.MAX_VALUE;
        	maxConnected = 0;
            N = Integer.parseInt(br.readLine());
            matrix = new boolean[N][N];
            points = new ArrayList<Point>();
            for(int i = 0 ; i < N ; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j = 0 ; j < N ; j++) {
            		matrix[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
            		if(matrix[i][j]) points.add(new Point(i, j));
            	}
            }
            
            dfs(0, 0, 0);
            
            sb.append("#").append(test).append(" ").append(min).append("\n");
        }
        
        System.out.println(sb);
    }
    
    public static void dfs(int index, int connected, int length) {
    	if(index == points.size()) {
    		if(connected > maxConnected) {
    			maxConnected = connected;
    			min = length;
    		}
    		else if(connected == maxConnected) {
    			min = Math.min(length, min);
    		}
    		return;
    	}
    	
    	int cr = points.get(index).row;
    	int cc = points.get(index).col;
    	if(cr == 0 || cr == N-1 || cc == 0 || cc == N-1) {
    		dfs(index+1, connected+1, length);
    		return;
    	}
    	
    	for(int i = 0 ; i < 4 ; i++) {
    		int nr = cr + d[i][0];
    		int nc = cc + d[i][1];
    		int currentWireLength = 0;
    		boolean canConnect = true;
    		
    		while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
    			if(!matrix[nr][nc]) {
    				matrix[nr][nc] = true;
    				currentWireLength++;
    				nr += d[i][0];
    				nc += d[i][1];
    			} else {
    				canConnect = false;
    				break;
    			}
    		}
    		
    		nr -= d[i][0];
    		nc -= d[i][1];
    		
    		if(canConnect && nr >= 0 && nr < N && nc >= 0 && nc < N) {
    			dfs(index+1, connected+1, length + currentWireLength);
    		}
    		
    		while(nr != cr || nc != cc) {
    			matrix[nr][nc] = false;
    			nr -= d[i][0];
    			nc -= d[i][1];
    		}
    	}
    	
    	dfs(index+1, connected, length);
    }
}