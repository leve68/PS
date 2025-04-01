import java.io.*;
import java.util.*;
public class Solution {
	public static int N;
	public static int M;
	public static int C;
	public static int[][] matrix;
	public static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int test = 1 ; test <= T ; test++) {
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            max = 0;
            matrix = new int[N][N];
            for(int i = 0 ; i < N ; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j = 0 ; j < N ; j++){
            		matrix[i][j] = Integer.parseInt(st.nextToken());
            	}
            }
            
            int[][] sum = new int[N][N-M+1];
            for(int i = 0 ; i < N ; i++) {
            	for(int j = 0 ; j < N-M+1 ; j++) {
            		int[] arr = new int[M];
            		for(int k = 0 ; k < M ; k++) {
            			arr[k] = matrix[i][j+k];
            		}
            		max = 0;
            		dfs(arr, 0, 0, 0);
            		sum[i][j] = max;
            	}
            }
            
            for(int i = 0 ; i < N ; i++) {
            	for(int j = 0 ; j < N-M+1 ; j++) {
            		int curSum = sum[i][j];
            		for(int k = j+M ; k < N-M+1 ; k++) {
            			max = Math.max(curSum + sum[i][k], max);
            		}
            		for(int x = i+1 ; x < N ; x++) {
            			for(int y = 0 ; y < N-M+1 ; y++) {
            				max = Math.max(curSum + sum[x][y], max);
            			}
            		}
            	}
            }
            
            sb.append("#").append(test).append(" ").append(max).append("\n");
        }
        
        System.out.println(sb);
    }
    static void dfs(int[] arr, int index, int sum, int price) {
    	if(index == arr.length) {
    		max = Math.max(max, price);
    		return;
    	}
    	
    	if(sum + arr[index] <= C) {
    		dfs(arr, index+1, sum + arr[index], price + arr[index]*arr[index]);    		
    	}
    	
    	dfs(arr, index+1, sum, price);
    }
}