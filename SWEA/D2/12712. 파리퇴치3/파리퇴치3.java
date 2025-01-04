import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//태케가 적어서 완전 탐색
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			//배열 입력
			int[][] map = new int[n][n];
			for(int i = 0 ; i<n ; i++) {
                for(int j = 0 ; j<n ; j++){
                    map[i][j] = sc.nextInt();
                }
            }
			
			//배열을 순회하며 +, x 합을 검사해 최대값 반환
			int answer = 0;
			for(int col = 0 ; col<n ; col++) {
				for(int row = 0 ; row<n ; row++) {
					answer = Math.max(getSum(map, row, col, n, m), answer);
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
			
		}
	}
	
	//matrix[col][row] 에 대한 최대값
	public static int getSum(int[][] matrix, int row, int col, int n, int m) {
		//+인 경우, 중복 제거
		int sumPlus = -matrix[col][row];
		int startRow = row - (m - 1) > 0 ? row - (m - 1) : 0;
		int endRow = row + (m - 1) < n ? row + (m - 1) : n - 1;
		int startCol = col - (m - 1) > 0 ? col - (m - 1) : 0;
		int endCol = col + (m - 1) < n ? col + (m - 1) : n - 1;
		
		//가로축
		for(int i = startRow ; i<=endRow ; i++) {
			sumPlus += matrix[col][i];
		}
		//세로축
		for(int i = startCol ; i<=endCol ; i++) {
			sumPlus += matrix[i][row];
		}
		
		//x인 경우
		int sumX = matrix[col][row];
	    for (int i = 1; i < m; i++) {
	        // 대각선 오른쪽 아래
	        if (row + i < n && col + i < n) {
	            sumX += matrix[col + i][row + i];
	        }
	        // 대각선 왼쪽 아래
	        if (row - i >= 0 && col + i < n) {
	            sumX += matrix[col + i][row - i];
	        }
	        // 대각선 오른쪽 위
	        if (row + i < n && col - i >= 0) {
	            sumX += matrix[col - i][row + i];
	        }
	        // 대각선 왼쪽 위
	        if (row - i >= 0 && col - i >= 0) {
	            sumX += matrix[col - i][row - i];
	        }
	    }
		
		
		return Math.max(sumPlus, sumX);
	}
}