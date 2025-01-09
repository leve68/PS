import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	public static int[][] dx = {{0,1}, {1,1},{1,0},{1,-1},{0,-1},{-1, -1},{-1,0},{-1,1}};
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//지뢰찾기 문제
			//최소 시도로 모든 지뢰 찾기
			
			int n = sc.nextInt();
			sc.nextLine();//개행제거
			char[][] map = new char[n][n];
			for(int i = 0 ; i<n ; i++){
				String str = sc.nextLine();
				map[i] = str.toCharArray();
			}
			
			//8반경 칸 입력
			boolean[][] visited = new boolean[n][n];
			int[][] newMap = new int[n][n];
			for(int i = 0 ; i<n ; i++){
				for(int j = 0 ; j<n ;j++){
					newMap[i][j] = getCount(i,j,map);
					if(newMap[i][j] == -1) visited[i][j] = true;
				}
			}
			
			//0인 칸 선택 후 방문처리
			Queue<int[]> q = new LinkedList<int[]>();
			int answer = 0;
			for(int i = 0 ; i<n ; i++){
				for(int j = 0 ; j<n ;j++){
					if(newMap[i][j] == 0 && !visited[i][j]) {
						//방문한 적이 없고 값이 0이면 큐에 삽입(선택)
						answer++;
						visited[i][j] = true;
						int[] current = {i, j};
						q.add(current);
						//bfs로 current를 선택시 밝혀지는 모든 지점을 방문처리
						while(!q.isEmpty()) {
							int[] start = q.poll();
							for(int k = 0 ; k<dx.length ; k++) {
								int[] next = {start[0]+dx[k][0], start[1]+dx[k][1]};
								if(0 <= next[0] && next[0] < n && 0 <= next[1] && next[1] < n) {
									if(newMap[next[0]][next[1]] == 0 && !visited[next[0]][next[1]]) {
                                        //주변 값이 0이면 방문처리 후 큐에 삽입
										visited[next[0]][next[1]] = true;
										q.add(new int[] {next[0], next[1]});
									} else if(newMap[next[0]][next[1]] != 0 && !visited[next[0]][next[1]]) {
                                        //주변 값이 0이 아니면 방문 처리
										visited[next[0]][next[1]] = true;
									}
								}
							}
						}
					}
				}
			}
			
			//나머지 방문 처리
			for(int i = 0 ; i<n ; i++){
				for(int j = 0 ; j<n ;j++){
					if(!visited[i][j]) answer++;
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
	
	//map[i][j] 주변 지뢰 개수 반환
	public static int getCount(int i, int j, char[][] map) {
		if(map[i][j] == '*') return -1;

		int n = map.length;
		int count = 0;
		for(int k = 0 ; k<dx.length ; k++) {
			if(0 <= i+dx[k][0] && i+dx[k][0] < n && 0 <= j+dx[k][1] && j+dx[k][1] < n) {
				if(map[i+dx[k][0]][j+dx[k][1]] == '*') count++;
			}
		}
		return count;
	}
}
