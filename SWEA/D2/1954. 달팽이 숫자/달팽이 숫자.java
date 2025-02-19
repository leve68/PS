import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       
        int T = Integer.parseInt(br.readLine());
         
        for(int test = 1 ; test <= T ; test++) {
            int N = Integer.parseInt(br.readLine());
             
            int[][] matrix = new int[N][N];
            matrix[0][0] = 1;
             
            int row = 0;
            int col = 0;
            int[][] d = {{0, 1},{1, 0},{0, -1},{-1, 0}};
            int count = 1;
            while(count <= (N+1)/2) {
                for(int i = 0 ; i < d.length ; i++) {
                    for(int j = 1 ; true ; j++) {
                        int nextRow = row + d[i][0];
                        int nextCol = col + d[i][1];
                        if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
                            if(matrix[nextRow][nextCol] == 0) {
                                matrix[nextRow][nextCol] = matrix[row][col] + 1;
                                row = nextRow;
                                col = nextCol;
                            } else break;
                        } else break;
                    }
                }
                count++;
            }
             
            System.out.println("#" + test);
            for(int i = 0 ; i < N ; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0 ; j < N ; j++) {
                    sb.append(matrix[i][j]).append(" ");
                }
                System.out.println(sb);
            }
        }
    }
}