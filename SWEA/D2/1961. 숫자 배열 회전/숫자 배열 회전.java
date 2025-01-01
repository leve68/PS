import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
        	int n = sc.nextInt();
        	int[][] origin = new int[n][n];
        	for(int i = 0 ; i<n ; i++) {
        		for(int j = 0 ; j<n ; j++){
        			origin[i][j] = sc.nextInt();
        		}
        	}
        	
	    	int[][] rotated90 = rotate90(origin, n);
	        int[][] rotated180 = rotate90(rotated90, n);
	        int[][] rotated270 = rotate90(rotated180, n);
        	
            System.out.println("#" + test_case);
            for (int i = 0; i < n; i++) {
                printRow(rotated90[i]);
                printRow(rotated180[i]);
                printRow(rotated270[i]);
                System.out.println();
            }
        }
        sc.close();
    }
    
    //90도 회전한 2차원 배열을 반환
    public static int[][] rotate90(int[][] matrix, int n) {
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }
    
    //row에 맞는 열을 출력
    public static void printRow(int[] row) {
        for (int num : row) {
            System.out.print(num);
        }
        System.out.print(" ");
    }
}