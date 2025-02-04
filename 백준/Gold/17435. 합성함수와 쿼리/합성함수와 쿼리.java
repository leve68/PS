import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int[][] matrix = new int[20][m + 1];
    	StringTokenizer line = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=m ; i++) {
        	matrix[0][i] = Integer.parseInt(line.nextToken());
        }
        
        int q = Integer.parseInt(br.readLine());
        int[][] query = new int[q][2];
        for(int i = 0 ; i<q ; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	query[i][0] = Integer.parseInt(st.nextToken());
        	query[i][1] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 1 ; i<20 ; i++) {
        	for(int j = 1 ; j <= m ; j++) {
        		matrix[i][j] = matrix[i-1][matrix[i-1][j]];
        	}
        }
                
        for(int[] curQ:query) {
            int n = curQ[1];
            for(int i = 0; (1<<i) <= curQ[0]; i++) {
                if((curQ[0] & (1<<i)) != 0) {
                    n = matrix[i][n];
                }
            }
            System.out.println(n);
        }
	}
}