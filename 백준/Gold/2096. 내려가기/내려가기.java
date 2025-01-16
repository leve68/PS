import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] matrix = new int[n][3];
		int[][] maxMatrix = new int[n][3];
		
		for(int i = 0 ; i<n ; i++) {
			for(int j = 0 ; j<3 ; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		
		//dp 문제
		//최대 점수
		//1: 최대값
		//2: [1][0] 기준  + [0][0],[0][1] // [1][1] 기준 + [0][0],[0][1],[0][2] ...
		
		maxMatrix[0] = matrix[0].clone();
		for(int i = 1 ; i<n ; i++) {
			maxMatrix[i][0] = Math.max(maxMatrix[i-1][0], maxMatrix[i-1][1]) + matrix[i][0];
			maxMatrix[i][1] = Math.max(maxMatrix[i-1][0], Math.max(maxMatrix[i-1][1], maxMatrix[i-1][2])) + matrix[i][1];
			maxMatrix[i][2] = Math.max(maxMatrix[i-1][1], maxMatrix[i-1][2]) + matrix[i][2];
			
			matrix[i][0] = Math.min(matrix[i-1][0], matrix[i-1][1]) + matrix[i][0];
			matrix[i][1] = Math.min(matrix[i-1][0], Math.min(matrix[i-1][1], matrix[i-1][2])) + matrix[i][1];
			matrix[i][2] = Math.min(matrix[i-1][1], matrix[i-1][2]) + matrix[i][2];
		}
		
		int max = Math.max(maxMatrix[n-1][0], Math.max(maxMatrix[n-1][1], maxMatrix[n-1][2]));
		int min = Math.min(matrix[n-1][0], Math.min(matrix[n-1][1], matrix[n-1][2]));
		
		System.out.println(max + " " + min);
	}
}

