import java.io.*;
import java.util.*;
   
class Main {
    public static int N;
    public static int[][] eggs;
    public static int count;
    public static int max = 0;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];
        for(int i = 0 ; i < N ; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	eggs[i][0] = Integer.parseInt(st.nextToken());
        	eggs[i][1] = Integer.parseInt(st.nextToken());
        }
        
        dfs(0);
        
        System.out.println(max);
    }
    
    public static void dfs(int cur) {
		max = Math.max(max, count);

    	if(cur == N) {
    		return;
    	}
    	
    	if(eggs[cur][0] <= 0) {
    		dfs(cur + 1);
    		return;
    	}
    	
    	for(int i = 0 ; i < N ; i++) {
    		if(eggs[i][0] <= 0 || i == cur) continue;
    		
    		eggs[cur][0] -= eggs[i][1];
    		eggs[i][0] -= eggs[cur][1];
    		if(eggs[cur][0] <= 0) count++;
    		if(eggs[i][0] <= 0) count++;
    		
    		dfs(cur + 1);
    		
    		if(eggs[cur][0] <= 0) count--;
    		if(eggs[i][0] <= 0) count--;
    		eggs[cur][0] += eggs[i][1];
    		eggs[i][0] += eggs[cur][1];
    	}
    }
}