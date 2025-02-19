import java.io.*;
import java.util.*;

public class Main {
	public static int[][] matrix = new int[20][20];
	public static int[][] d = {{1,0},{1,1},{0,1},{1,-1}}; //이전 값들은 확인할 필요 없음

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i = 1 ; i < 20 ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1 ; j < 20 ; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		int answer = 0;
		int[] answerPoint = new int[2];
		
		for(int i = 1 ; i < 20 ; i++) {
			for (int j = 1 ; j < 20 ; j++) {
				if(matrix[i][j] == 1 || matrix[i][j] == 2) {
					int current = matrix[i][j];
					for(int dir = 0 ; dir < 4 ; dir++) {
						int dirCount = 1;
						for(int k = 1 ; true ; k++) {
							int nextRow = i + d[dir][0] * k;
							int nextCol = j + d[dir][1] * k;
							if(nextRow >= 20 || nextCol >= 20 || nextRow < 0 || nextCol < 0) break;
							if(matrix[nextRow][nextCol] != current) break;

							dirCount++;
						}
						if(dirCount == 5) {
						    int prevRow = i - d[dir][0];
						    int prevCol = j - d[dir][1];
						    
						    if(prevRow >= 1 && prevRow < 20 && prevCol >= 1 && prevCol < 20 && matrix[prevRow][prevCol] == current) {
						        continue;
						    }
						    
						    answer = current;
						    answerPoint[0] = dir != 3 ? i : i + d[dir][0] * 4;
						    answerPoint[1] = dir != 3 ? j : j + d[dir][1] * 4;
						    break;
						}
					}
				}
			}
			if(answer != 0) break;
		}
		
		System.out.println(answer);
		if(answer != 0) System.out.println(answerPoint[0] + " " + answerPoint[1]);
	}

}
