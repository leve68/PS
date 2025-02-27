import java.io.*;
import java.util.*;
   
class Main {
	public static int R;
	public static int C;
	public static int[][] matrix;
	public static int temp;
	public static int[][] d = {{-1, 1},{0, 1},{1, 1}};
    public static void main(String args[]) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int answer = 0;
    	StringTokenizer st1 = new StringTokenizer(br.readLine());
    	R = Integer.parseInt(st1.nextToken());
    	C = Integer.parseInt(st1.nextToken());
    	
    	matrix = new int[R][C];
    	for(int i = 0 ; i < R ; i++) {
    		matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(e -> e.equals(".") ? 0 : -1).toArray();
    	}
    	
    	for(int i = 0 ; i < C ; i++) {
    		matrix[0][i] |= (1 << 0);
    		matrix[R-1][i] |= (1 << 2);
    	}

    	for(int i = 0 ; i < R ; i++) {
    		for(int j = 0 ; j < C ; j++) {
    			for(int k = 0 ; k < 3 ; k++) {
    				int nextI = i + d[k][0];
    				int nextJ = j + d[k][1];
    				if(nextI >= 0 && nextJ >= 0 && nextI < R && nextJ < C) {
    					if(matrix[nextI][nextJ] == -1) {
    						matrix[i][j] |= (1 << k);
    					}
    				}
    			}
    		}
    	}
    	    	
    	//000 => 3방향 가능
    	//001 => 오른쪽위 대각선 방향 이미 존재 (1 << 0)
    	//100 => 오른쪽아래 대각선 방향 이미 존재 (1 << 2)
    	//111 => 모두 불가능
    	int[] end = new int[R];
    	for(int i = 0 ; i < R ; i++) {
    		if(matrix[i][0] == -1) continue;
    		temp = -1;
    		dfs(i, 0);
    		end[i] = temp;
    	}
    	for(int i = 0 ; i < end.length ; i++) {
    		if(end[i] != -1) answer++;
    	}
    	
    	System.out.println(answer);
    }
    
    static void dfs(int row, int col) {
    	if(col == C-1) {
    		temp = row;
    		return;
    	}
    	
    	for(int i = 0 ; i < 3 ; i++) {
    		if(((1 << i) & matrix[row][col]) != 0) {
    			continue;
    		}
    		
    		int nextRow = row + d[i][0];
    		int nextCol = col + d[i][1];
    		
    		if(matrix[nextRow][nextCol] == 7 || matrix[nextRow][nextCol] == -1) {
    		    matrix[row][col] |= (1 << i);
    		    continue;
    		}
    		
    		matrix[row][col] |= (1 << i);
    		dfs(nextRow, nextCol);
    		if(temp != -1) {
    			matrix[row][col] = 7;
    			return;
    		}
    		matrix[row][col] &= ~(1 << i);
    	}
    }
}