import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		//빈곳 .
		//물 *
		//돌 X
		//S => D
		sc.nextLine();
		int[] start = new int[2];
		int[] end = new int[2];
		int waterCount = 0;
		int[][] map = new int[r][c];
		for(int i = 0 ; i<r ; i++) {
			String line = sc.nextLine();
			for(int j = 0 ; j<c ; j++) {
				if(line.charAt(j) == 'X') {
					map[i][j] = -1;
				} else if(line.charAt(j) == '*') {
					map[i][j] = -2;
					waterCount++;
				} else if(line.charAt(j) == 'D') {
					end[0] = i;
					end[1] = j;
					map[i][j] = -1;
				} else if(line.charAt(j) == 'S') {
					start[0] = i;
					start[1] = j;
					map[i][j] = -1;
				}
			}
		}
				
		//물 bfs
		int[][] waters = new int[waterCount][2];
		int count = 0;
		for(int i = 0 ; i<r ; i++) {
			for(int j = 0 ; j<c ; j++) {
				if(map[i][j] == -2) {
					waters[count][0] = i;
					waters[count][1] = j;
					map[i][j] = 0;
					count++;
				}
			}
		}
		
		int[] dx = {1,0,-1,0};
		int[] dy = {0,-1,0,1};
		Queue<int[]> q = new LinkedList<int[]>();
		for(int i = 0 ; i<waterCount ; i++) {
			q.add(waters[i]);
		}
		while(!q.isEmpty()) {
			int[] current = q.poll();
			for(int i = 0 ; i<4 ; i++) {
				int nextY = current[0] + dy[i];
				int nextX = current[1] + dx[i];
				if(nextY>=0 && nextY<r && nextX>=0 && nextX<c) {
					if(map[nextY][nextX] == 0 || map[nextY][nextX] > map[current[0]][current[1]] + 1) {
						//방문하지 않았거나 더 오래 걸린 경우에 갱신
						map[nextY][nextX] = map[current[0]][current[1]] + 1;
						q.add(new int[] {nextY, nextX});
					}
				}
			}
		}
		
		//고슴도치 bfs
		q.add(start);
		map[end[0]][end[1]] = 0;
		map[start[0]][start[1]] = 0;
		boolean canArrive = false;
		while(!q.isEmpty()) {
			int[] current = q.poll();
			for(int i = 0 ; i<4 ; i++) {
				int nextY = current[0] + dy[i];
				int nextX = current[1] + dx[i];
				if(nextY>=0 && nextY<r && nextX>=0 && nextX<c) {
					if(map[nextY][nextX] > map[current[0]][current[1]] + 1 || map[nextY][nextX] == 0) {
						//갈 수 있는 경우
						map[nextY][nextX] = map[current[0]][current[1]] + 1;
						q.add(new int[] {nextY, nextX});
						if(nextY == end[0] && nextX == end[1]) canArrive = true;
					}
				}
			}
		}
		if(canArrive)System.out.println(map[end[0]][end[1]]);
		else System.out.println("KAKTUS");
	}
}

