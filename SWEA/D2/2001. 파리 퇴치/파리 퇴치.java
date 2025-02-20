import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int T = Integer.parseInt(br.readLine());
        for(int test = 1 ; test <= T ; test++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = input[0];
            int m = input[1];
             
            //파리 matrix
            int[][] matrix = new int[n][n];
            for(int i = 0 ; i < n ; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < n ; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            //파리 누적합 matrix
            int[][] rowMatrix = new int[n][n];
            for(int i = 0 ; i < n ; i++) {
                rowMatrix[i][0] = matrix[i][0];
                for(int j = 1 ; j < n ; j++) {
                    rowMatrix[i][j] = matrix[i][j] + rowMatrix[i][j-1];
                }
            }
            int[][] colMatrix = new int[n][n];
            for(int i = 0 ; i < n ; i++) {
                colMatrix[0][i] = matrix[0][i];
                for(int j = 1 ; j < n ; j++) {
                    colMatrix[j][i] = matrix[j][i] + colMatrix[j-1][i];
                }
            }
             
            //파리채로 잡을 수 있는 파리 matrix
            int[][] sumMatrix = new int[n-m+1][n-m+1];
            //0,0 초기값
            for(int i = 0 ; i < m ; i++) {
                sumMatrix[0][0] += rowMatrix[i][m-1];
            }
            //row 초기값
            for(int i = 1 ; i < n-m+1 ; i++) {
                sumMatrix[i][0] = sumMatrix[i-1][0] + rowMatrix[i+m-1][m-1] - rowMatrix[i-1][m-1];
            }
            //col 초기값
            for(int i = 1 ; i < n-m+1 ; i++) {
                sumMatrix[0][i] = sumMatrix[0][i-1] + colMatrix[m-1][i+m-1] - colMatrix[m-1][i-1];
            }
             
            //채우기
            for(int i = 1; i < n-m+1; i++) {
                for(int j = 1; j < n-m+1; j++) {
                    int newRow = rowMatrix[i+m-1][j+m-1] - rowMatrix[i+m-1][j-1];
                    int oldRow = rowMatrix[i-1][j+m-1] - rowMatrix[i-1][j-1];
                     
                    sumMatrix[i][j] = sumMatrix[i-1][j] + newRow - oldRow;
                }
            }
             
             
            int answer = 0;
            for(int i = 0 ; i < n-m+1 ; i++) {
                for(int j = 0 ; j < n-m+1 ; j++) {
                    answer = Math.max(answer, sumMatrix[i][j]);
                }
            }
            System.out.println("#" + test + " " + answer);
        }        
    }
}