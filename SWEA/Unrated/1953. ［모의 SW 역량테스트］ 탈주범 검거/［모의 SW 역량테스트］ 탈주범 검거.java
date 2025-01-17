import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//탈출 1시간뒤 입장
			//시간당 1이동
			//위 오른쪽 아래 왼쪽 순으로 0,1,2,3
			int[][] moveDir = {
					{}, {0,1,2,3}, {0,2}, {1,3}, {0,1}, {1,2}, {2,3}, {3,0}
			};
			
			int n = sc.nextInt();
			int m = sc.nextInt();
			int ry = sc.nextInt();
			int rx = sc.nextInt();
			int time = sc.nextInt();
			
			int[][] timeMatrix = new int[n][m];
			int[][] matrix = new int[n][m];
			int[] start = {ry, rx};
			
			for(int i = 0 ; i<n ; i++) {
				for(int j = 0 ; j<m ; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
			
			Queue<int[]> q = new LinkedList<int[]>();
			q.add(start);
			timeMatrix[ry][rx] = 1;
			while(!q.isEmpty()) {
				int[] current = q.poll();
				int currentY = current[0];
				int currentX = current[1];
				int currentTime = timeMatrix[currentY][currentX];
				int[] dir = moveDir[matrix[currentY][currentX]];
				
				for(int i = 0 ; i<dir.length ; i++) {
					switch(dir[i]) {
					case 0:
						//위 이동
						if(currentY - 1 >= 0 && (timeMatrix[currentY-1][currentX] == 0 || timeMatrix[currentY-1][currentX] > currentTime + 1)) {
							//1,2,5,6 가능
							if(matrix[currentY-1][currentX] == 1||matrix[currentY-1][currentX] == 2||matrix[currentY-1][currentX] == 5||matrix[currentY-1][currentX] == 6)
							{
								timeMatrix[currentY-1][currentX] = currentTime + 1;
								q.add(new int[] {currentY-1, currentX});
							}
						}
						break;
					case 1:
						//오른쪽 이동
						if(currentX + 1 < m  && (timeMatrix[currentY][currentX+1] == 0 || timeMatrix[currentY][currentX+1] > currentTime + 1)) {
							//1,3,6,7 가능
							if(matrix[currentY][currentX+1] == 1||matrix[currentY][currentX+1] == 3||matrix[currentY][currentX+1] == 6||matrix[currentY][currentX+1] == 7)
							{
								timeMatrix[currentY][currentX+1] = currentTime + 1;
								q.add(new int[] {currentY, currentX+1});
							}
						}
						break;
					case 2:
						//아래 이동
						if(currentY + 1 <n && (timeMatrix[currentY+1][currentX] == 0 || timeMatrix[currentY+1][currentX] > currentTime + 1)) {
							//1,2,4,7 가능
							if(matrix[currentY+1][currentX] == 1||matrix[currentY+1][currentX] == 2||matrix[currentY+1][currentX] == 4||matrix[currentY+1][currentX] == 7)
							{
								timeMatrix[currentY+1][currentX] = currentTime + 1;
								q.add(new int[] {currentY+1, currentX});
							}
						}
						
						break;
					case 3:
						//왼쪽 이동
						if(currentX - 1 >= 0 && (timeMatrix[currentY][currentX-1] == 0 || timeMatrix[currentY][currentX-1] > currentTime + 1)) {
							//1,3,4,5 가능
							if(matrix[currentY][currentX-1] == 1 || matrix[currentY][currentX-1] ==3||matrix[currentY][currentX-1] ==4||matrix[currentY][currentX-1] ==5)
							{
								timeMatrix[currentY][currentX-1] = currentTime + 1;
								q.add(new int[] {currentY, currentX-1});
							}
						}
						 break;
					}
				}
			}
			int count = 0;
			for(int i = 0 ; i<n ; i++) {
				for(int j = 0 ; j<m ; j++) {
					if(timeMatrix[i][j] <= time && timeMatrix[i][j] != 0) count++;
				}
			}
			
			
			System.out.println("#" + test_case + " " + count);
		}
	}
}
