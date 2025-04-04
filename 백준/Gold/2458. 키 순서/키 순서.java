import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

    	int answer = 0;
		st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	int[][] edge = new int[N+1][N+1];
    	for(int i = 1 ; i < N+1 ; i++) {
    		for(int j = 1 ; j < N+1 ; j++) {
    			edge[i][j] = 1_000;
    		}
    	}
    	for(int i = 0 ; i < M ; i++) {
    		st = new StringTokenizer(br.readLine());
    		edge[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
    	}
    	
    	for(int k = 1 ; k < N+1 ; k++) {
    		for(int i = 1 ; i < N+1 ; i++) {
    			for(int j = 1 ; j < N+1 ; j++) {
    				if(edge[i][k] + edge[k][j] < edge[i][j]) {
    					edge[i][j] = edge[i][k] + edge[k][j];
    				}
    			}
    		}
    	}
    	
    	for(int i = 1 ; i < N+1 ; i++) {
    		int count = 0;
    		for(int j = 1 ; j < N+1 ; j++) {
    			if(i == j) continue;
    			
    			if(edge[i][j] != 1_000 || edge[j][i] != 1_000) count++;
    		}
    		if(count == N-1) answer++;
    	}

        System.out.println(answer);
    }
}