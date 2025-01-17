import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//교착상태 개수 찾기
			//n극: 1 s극: 2
			//위쪽에 N극 아래쪽에 S극 => N극은 아래로, S극은 위로
			//y축 한줄에 대해서 s,n이 교차하는 지점 찾기
			
			int n = sc.nextInt();
			int[][] matrix = new int[n][n];
			for(int i = 0 ; i<n ; i++) {
				for(int j = 0 ; j<n ; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
			
			int count = 0;
			for(int j = 0 ; j<n ;j++) {
				int current = 2;
				int currentCount = 0;
				for(int i = 0 ; i<n ; i++) {
					if(matrix[i][j] != 0 && matrix[i][j] != current) {
						currentCount++;
						current = matrix[i][j];
					}
				}
				if(currentCount == 1 || currentCount == 0) continue;
				else if(currentCount%2 == 1) {
					currentCount--;
				}
				count += currentCount/2;
			}
			
			System.out.println("#"+test_case + " " + count);
		}
	}
}