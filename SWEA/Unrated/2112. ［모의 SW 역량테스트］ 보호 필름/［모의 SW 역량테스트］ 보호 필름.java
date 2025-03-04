import java.io.*;
import java.util.*;


public class Solution {
	public static int[][] origin;
	public static int[][] matrix;
	public static int K;
	public static int count;
	public static ArrayList<Integer> history;
	public static boolean isAnswer;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st1.nextToken());
			int W = Integer.parseInt(st1.nextToken());
			K = Integer.parseInt(st1.nextToken());
			matrix = new int[D][W];
			origin = new int[D][W];
			for(int i = 0 ; i < D ; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < W ; j++) {
					matrix[i][j] = Integer.parseInt(st2.nextToken());
					origin[i][j] = matrix[i][j];
				}
			}
			count = 0;
			isAnswer = false;
			while(!isFine() && count < D) {
				history = new ArrayList<Integer>();
				count++;
				dfs(0);
				if(isAnswer) break;
			}
			
			sb.append("#").append(test).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean isFine() {
	    for(int i = 0; i < matrix[0].length; i++) {
	        boolean isColumnFine = false;
	        
	        for(int j = 0; j <= matrix.length - K; j++) {
	            boolean allSame = true;
	            int firstValue = matrix[j][i];
	            
	            for(int k = 1; k < K; k++) {
	                if(matrix[j+k][i] != firstValue) {
	                    allSame = false;
	                    break;
	                }
	            }
	            
	            if(allSame) {
	                isColumnFine = true;
	                break;
	            }
	        }
	        
	        if(!isColumnFine) return false;
	    }
	    return true;
	}
	
	static void dfs(int current) {
	    if(isAnswer) return;
	    
	    if(current == matrix.length || history.size() == count) {
	        if(isFine()) {
	            isAnswer = true;
	        }
	        return;
	    }
	    
	    dfs(current + 1);
	    
	    if(history.size() < count) {
	        history.add(current);
	        fillA(current);
	        dfs(current + 1);
	        
	        if(isAnswer) return;
	        
	        fillB(current);
	        dfs(current + 1);
	        
	        recover(current);
	        history.remove(history.size()-1);
	    }
	}
	
	static void fillA(int num) {
		for(int i = 0 ; i < matrix[num].length ; i++) {
			matrix[num][i] = 0;
		}
	}
	
	static void fillB(int num) {
		for(int i = 0 ; i < matrix[num].length ; i++) {
			matrix[num][i] = 1;
		}
	}
	
	static void recover(int num) {
		for(int i = 0 ; i < matrix[num].length ; i++) {
			matrix[num][i] = origin[num][i];
		}
	}
}