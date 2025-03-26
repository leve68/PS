import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static int[][] origin;
    public static int[][] matrix;
    public static ArrayList<Cam> cams;
    public static int min = Integer.MAX_VALUE;
    public static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    static class Cam {
    	int type;
    	int row;
    	int col;
    	
    	Cam(int type, int row, int col){
    		this.type = type;
    		this.row = row;
    		this.col = col;
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        origin = new int[N][M];
        matrix = new int[N][M];
        
        cams = new ArrayList<Cam>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
            	origin[i][j] = Integer.parseInt(st.nextToken());
            	matrix[i][j] = origin[i][j];
                if(origin[i][j] != 6 && origin[i][j] > 0) {
                	cams.add(new Cam(origin[i][j], i, j));
                }
            }
        }
        dfs(0);
        
        System.out.println(min);
    }
    
    public static void dfs(int index) {
    	if(index == cams.size()) {
    		for(Cam c : cams) {
    			matrix[c.row][c.col] = c.type;
    		}

    		int count = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                	if(matrix[i][j] == 0) count++;
                }
            }
            min = Math.min(count, min);
            return;
    	}
    	
    	Cam cur = cams.get(index);
    	
    	switch(cur.type) {
    	case 1:
    		for(int i = 0 ; i < 4 ; i++) {
    			int nr = cur.row + d[i][0];
    			int nc = cur.col + d[i][1];
    			ArrayList<Cam> changed = new ArrayList<Cam>();
    			while(nr>=0 && nr<N && nc>=0 && nc<M) {
    				if(matrix[nr][nc] == 6) break;
    				if(matrix[nr][nc] != -1) {
    					matrix[nr][nc] = -1;
    					changed.add(new Cam(0, nr, nc));        					
    				}
        			nr += d[i][0];
        			nc += d[i][1];
    			}
    			
    			dfs(index+1);
    			
    			for(Cam c : changed) {
    				matrix[c.row][c.col] = 0;
    			}
    		}
    		break;
    	case 2:
    		for(int k = 0 ; k < 2 ; k++) {
    			ArrayList<Cam> changed = new ArrayList<Cam>();
    			for(int i = k ; i < 4 ; i += 2) {
    				int nr = cur.row + d[i][0];
    				int nc = cur.col + d[i][1];
    				while(nr>=0 && nr<N && nc>=0 && nc<M) {
        				if(matrix[nr][nc] == 6) break;
        				if(matrix[nr][nc] != -1) {
        					matrix[nr][nc] = -1;
        					changed.add(new Cam(0, nr, nc));        					
        				}
        				nr += d[i][0];
    					nc += d[i][1];
    				}
    			}
    			
    			dfs(index+1);
    			
    			for(Cam c : changed) {
    				matrix[c.row][c.col] = 0;
    			}
    		}
    		break;
    	case 3:
    		for(int i = 0 ; i < 4 ; i++) {
    			ArrayList<Cam> changed = new ArrayList<Cam>();
    			int nr = cur.row + d[i][0];
    			int nc = cur.col + d[i][1];
    			while(nr>=0 && nr<N && nc>=0 && nc<M) {
    				if(matrix[nr][nc] == 6) break;
    				if(matrix[nr][nc] != -1) {
    					matrix[nr][nc] = -1;
    					changed.add(new Cam(0, nr, nc));        					
    				}
    				nr += d[i][0];
    				nc += d[i][1];
    			}
    			int j = (i+1)%4;
    			int nnr = cur.row + d[j][0];
    			int nnc = cur.col + d[j][1];
    			while(nnr>=0 && nnr<N && nnc>=0 && nnc<M) {
    				if(matrix[nnr][nnc] == 6) break;
    				if(matrix[nnr][nnc] != -1) {
    					matrix[nnr][nnc] = -1;
    					changed.add(new Cam(0, nnr, nnc));        					
    				}    				
    				nnr += d[j][0];
    				nnc += d[j][1];
    			}
    			
    			dfs(index+1);
    			
    			for(Cam c : changed) {
    				matrix[c.row][c.col] = 0;
    			}
    		}
    		break;
    	case 4:
    		for(int i = 0 ; i < 4 ; i++) {
    			ArrayList<Cam> changed = new ArrayList<Cam>();
    			for(int j = 0 ; j < 4 ; j++) {
    				if(j == i) continue;
    				int nr = cur.row + d[j][0];
    				int nc = cur.col + d[j][1];
    				while(nr>=0 && nr<N && nc>=0 && nc<M) {
        				if(matrix[nr][nc] == 6) break;
        				if(matrix[nr][nc] != -1) {
        					matrix[nr][nc] = -1;
        					changed.add(new Cam(0, nr, nc));        					
        				}
    					nr += d[j][0];
    					nc += d[j][1];
    				}
    			}
    			
    			dfs(index+1);
    			
    			for(Cam c : changed) {
    				matrix[c.row][c.col] = 0;
    			}
    		}
    		break;
    	case 5:
    		ArrayList<Cam> changed = new ArrayList<Cam>();
    		for(int i = 0 ; i < 4 ; i++) {
				int nr = cur.row + d[i][0];
				int nc = cur.col + d[i][1];
				while(nr>=0 && nr<N && nc>=0 && nc<M) {
    				if(matrix[nr][nc] == 6) break;
    				if(matrix[nr][nc] != -1) {
    					matrix[nr][nc] = -1;
    					changed.add(new Cam(0, nr, nc));        					
    				}
					nr += d[i][0];
					nc += d[i][1];
				}
    		}
    		dfs(index+1);
    		break;
    	}
    }
}