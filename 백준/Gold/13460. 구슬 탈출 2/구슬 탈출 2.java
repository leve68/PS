import java.io.*;
import java.util.*;

public class Main {
	public static int[][] matrix;
	public static int[] rp;
	public static int[] bp;
	public static int[][] d = {{0, 1},{1, 0},{0, -1},{-1, 0}};//우, 하, 좌, 상
	public static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		matrix = new int[N][M];
		char[][] origin = new char[N][M];
		for(int i = 0 ; i < N ; i++) {
			origin[i] = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; j++) {
				if(origin[i][j] == '#') matrix[i][j] = -1;
				else if(origin[i][j] == 'O') matrix[i][j] = -2;
				else if(origin[i][j] == 'R') rp = new int[] {i, j};
				else if(origin[i][j] == 'B') bp = new int[] {i, j};
			}
		}
		
		dfs(1);
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}
	
	static void dfs(int t) {
		if(t == 11) {
			return;
		}
		
		int[] orp = rp.clone();
		int[] obp = bp.clone();
		
		if(move(0)) {
			if(!setAnswer(0, t)) {
				dfs(t+1);				
			};
		}
		restore(orp, obp);
		
		if(move(1)) {
			if(!setAnswer(1, t)) {
				dfs(t+1);				
			}
		}
		restore(orp, obp);
		
		if(move(2)) {
			if(!setAnswer(2, t)) {
				dfs(t+1);				
			}
		}
		restore(orp, obp);
		
		if(move(3)) {
			if(!setAnswer(3, t)) {
				dfs(t+1);
			}
		}
		restore(orp, obp);
	}
	
	static boolean setAnswer(int dir, int t) {
		if(matrix[rp[0] + d[dir][0]][rp[1] + d[dir][1]] == -2) {
			answer = Math.min(answer, t);
			return true;
		} else return false;
	}
	
	//시뮬레이션한 결과와 가능한지 판별한 bool값 반환
	static boolean move(int dir) {
		//rp 이동
		int rm = 0;
		while(matrix[rp[0] + d[dir][0]][rp[1] + d[dir][1]] == 0) {
			rp[0] += d[dir][0];
			rp[1] += d[dir][1];
			rm++;
		}
		//bp 이동
		int bm = 0;
		while(matrix[bp[0] + d[dir][0]][bp[1] + d[dir][1]] == 0) {
			bp[0] += d[dir][0];
			bp[1] += d[dir][1];
			bm++;
		}
		
		//B가 이동하는 길에 O가 있는 경우
		if(matrix[bp[0] + d[dir][0]][bp[1] + d[dir][1]] == -2) return false;
		//R이 이동하는 길에 O가 있는 경우
		if(matrix[rp[0] + d[dir][0]][rp[1] + d[dir][1]] == -2) return true;
		//R, B 둘다 이동하지 않은 경우
		if(rm == 0 && bm == 0) return false;
		
		//둘이 겹친 경우 처리
		if(rp[0] == bp[0] && rp[1] == bp[1]) {
			if(rm > bm) {
				rp[0] -= d[dir][0];
				rp[1] -= d[dir][1];
			} else {
				bp[0] -= d[dir][0];
				bp[1] -= d[dir][1];
			}
		}
		return true;
	}
	
	static void restore(int[] orp, int[] obp) {
		rp = orp.clone();
		bp = obp.clone();
	}
}
