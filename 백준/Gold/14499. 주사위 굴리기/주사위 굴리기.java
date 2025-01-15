import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] matrix = new int[n][m];
		int[] startPoint = {x, y};
		
		sc.nextLine();
		for(int i = 0 ; i<n ; i++) {
			for(int j = 0 ; j<m ; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		
		sc.nextLine();
		String[] str = sc.nextLine().split(" ");
		int[] instruction = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
		
		//n,m 크기의  matrix
		//주사위는 [x,y]에서 시작
		//상하좌우 + 위아래
		//주사위 = [천장, 오른쪽, 왼쪽, 위쪽, 아래쪽, 바닥]
		int[] dice = {0,0,0,0,0,0};
		
		for(int i = 0 ; i<k ; i++) {
			int dir = instruction[i];
			
			switch(dir) {
			case 1:
				//오른쪽
				if(startPoint[1] + 1 >= m) break;
				startPoint[1] = startPoint[1] + 1;
				dice = rollDice(dir, dice);
				if(matrix[startPoint[0]][startPoint[1]] == 0) {
					matrix[startPoint[0]][startPoint[1]] = dice[5];
				} else {
					dice[5] = matrix[startPoint[0]][startPoint[1]];
					matrix[startPoint[0]][startPoint[1]] = 0;
				}
				System.out.println(dice[0]);
				break;
			case 2:
				//왼쪽
				if(startPoint[1] - 1 < 0) break;
				startPoint[1] = startPoint[1] - 1;
				dice = rollDice(dir, dice);
				if(matrix[startPoint[0]][startPoint[1]] == 0) {
					matrix[startPoint[0]][startPoint[1]] = dice[5];
				} else {
					dice[5] = matrix[startPoint[0]][startPoint[1]];
					matrix[startPoint[0]][startPoint[1]] = 0;
				}
				System.out.println(dice[0]);
				break;
			case 3:
				//위쪽
				if(startPoint[0] - 1 < 0) break;
				startPoint[0] = startPoint[0] - 1;
				dice = rollDice(dir, dice);
				if(matrix[startPoint[0]][startPoint[1]] == 0) {
					matrix[startPoint[0]][startPoint[1]] = dice[5];
				} else {
					dice[5] = matrix[startPoint[0]][startPoint[1]];
					matrix[startPoint[0]][startPoint[1]] = 0;
				}
				System.out.println(dice[0]);
				break;
			case 4:
				//아래쪽
				if(startPoint[0] + 1 >= n) break;
				startPoint[0] = startPoint[0] + 1;
				dice = rollDice(dir, dice);
				if(matrix[startPoint[0]][startPoint[1]] == 0) {
					matrix[startPoint[0]][startPoint[1]] = dice[5];
				} else {
					dice[5] = matrix[startPoint[0]][startPoint[1]];
					matrix[startPoint[0]][startPoint[1]] = 0;
				}
				System.out.println(dice[0]);
				break;
			}
		}
	}
	
	public static int[] rollDice(int dir, int[] startDice) {
		int[] nextDice = startDice.clone();
		//주사위 = [천장, 오른쪽, 왼쪽, 위쪽, 아래쪽, 바닥]

		switch(dir) {
		case 1:
			nextDice[0] = startDice[2];
			nextDice[1] = startDice[0];
			nextDice[2] = startDice[5];
			nextDice[3] = startDice[3];
			nextDice[4] = startDice[4];
			nextDice[5] = startDice[1];
			break;
		case 2:
			nextDice[0] = startDice[1];
			nextDice[1] = startDice[5];
			nextDice[2] = startDice[0];
			nextDice[3] = startDice[3];
			nextDice[4] = startDice[4];
			nextDice[5] = startDice[2];
			break;
		case 3:
			nextDice[0] = startDice[4];
			nextDice[1] = startDice[1];
			nextDice[2] = startDice[2];
			nextDice[3] = startDice[0];
			nextDice[4] = startDice[5];
			nextDice[5] = startDice[3];
			break;
		case 4:
			nextDice[0] = startDice[3];
			nextDice[1] = startDice[1];
			nextDice[2] = startDice[2];
			nextDice[3] = startDice[5];
			nextDice[4] = startDice[0];
			nextDice[5] = startDice[4];
			break;
		}
		return nextDice;
	}
}

