import java.io.*;
import java.util.*;
public class Main {
	static class Point {
		int h;
		int w;
		int count;
		
		Point (int h, int w, int count){
			this.h = h;
			this.w = w;
			this.count = count;
		}
	}
	public static int[][] d = {{0,1},{1,0},{0,-1},{-1,0}};
	public static int[][] du = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
	public static int K;
	public static int H;
	public static int W;
	public static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[H][W];
        for(int i = 0 ; i < H ; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0 ; j < W ; j++) {
        		matrix[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int[][][] v = new int[H][W][K+1];
        Queue<Point> q = new ArrayDeque<Point>();
        q.add(new Point(0,0,0));
        
        while(!q.isEmpty()) {
        	Point cp = q.poll();
        	if(cp.count < K) {
        		for(int i = 0 ; i < 8 ; i++) {
        			int nh = cp.h + du[i][0];
        			int nw = cp.w + du[i][1];
        			
        			if(nh>=0 && nh<H && nw>=0 && nw<W) {
        				if(matrix[nh][nw] == 1) continue;
        				if(v[nh][nw][cp.count+1] != 0) continue;
        				
        				q.add(new Point(nh, nw, cp.count+1));
        				v[nh][nw][cp.count+1] = v[cp.h][cp.w][cp.count] + 1;
        			}
        		}
        	}
        	
        	for(int i = 0 ; i < 4 ; i++) {
    			int nh = cp.h + d[i][0];
    			int nw = cp.w + d[i][1];
    			
    			if(nh>=0 && nh<H && nw>=0 && nw<W) {
    				if(matrix[nh][nw] == 1) continue;
    				if(v[nh][nw][cp.count] != 0) continue;
    				
    				q.add(new Point(nh, nw, cp.count));
    				v[nh][nw][cp.count] = v[cp.h][cp.w][cp.count] + 1;
    			}
        	}
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < v[H-1][W-1].length ; i++) {
        	if(v[H-1][W-1][i] == 0) continue;
        	min = Math.min(min, v[H-1][W-1][i]);
        }
        if(H == 1 && W == 1 && matrix[0][0] == 0) min = 0;
        
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}