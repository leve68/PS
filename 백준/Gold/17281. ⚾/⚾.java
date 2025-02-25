import java.io.*;
import java.util.*;
 
class Main {
	public static boolean[] visited;
	public static int[] history;
	public static int N;
	public static int[][] matrix;
	public static int max;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][9];
        for(int i = 0 ; i < N ; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j = 0 ; j < 9 ; j++) {
        		matrix[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        //0: 아웃, 1,2,3,4안타
        //가장 많은 득점을 하는 타순에서의 득점 구하기
        //1번 선수는 4번 고정
        //이닝이 변경될 때 순서는 연속적
        //모든 순서에 대한 점수 구하기
        
        //1,2,3,4,5,6,7,8 의 순서 구하기
        history = new int[9];
        visited = new boolean[9];
        dfs(0);
        
        sb.append(max);
        
        
        System.out.println(sb);
    }
    
    static void dfs(int index) {
    	if(index == 9) {
    		simulation();
    		return;
    	}
    	
		if(index == 3) {
			history[3] = 0;
			dfs(index+1);
			return;
		}
    	
    	for(int i = 1 ; i < 9 ; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			history[index] = i;
        		dfs(index+1);
        		visited[i] = false;
    		}
    	}
    }
    
    static void simulation() {
    	int sum = 0;
		int currentIndex = 0;
		int currentN = 0;
		while(currentN < N) {
			int[] stack = new int[3];
			int outCount = 0;
			while(outCount < 3) {
				switch(matrix[currentN][history[currentIndex]]) {
					case 0:
						outCount++;
						break;
					case 1:
						sum += stack[2];
						stack[2] = stack[1];
						stack[1] = stack[0];
						stack[0] = 1;
						break;
					case 2:
						sum += (stack[2] + stack[1]);
						stack[2] = stack[0];
						stack[1] = 1;
						stack[0] = 0;
						break;
					case 3:
						sum += (stack[2] + stack[1] + stack[0]);
						stack[2] = 1;
						stack[1] = 0;
						stack[0] = 0;
						break;
					case 4:
						sum += (stack[2] + stack[1] + stack[0] + 1);
						stack[2] = 0;
						stack[1] = 0;
						stack[0] = 0;
						break;
				}
				currentIndex = (currentIndex+1) % 9;
			}
			currentN++;
		}
		max = Math.max(max, sum);
    }
}