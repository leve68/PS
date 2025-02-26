import java.io.*;
import java.util.*;
   
class Main {
    public static void main(String args[]) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st1 = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st1.nextToken()); //집에 있는 물건 종류의 수
    	int M = Integer.parseInt(st1.nextToken()); //최대 무게
    	List<int[]> objects = new ArrayList<int[]>(); //무게, 만족도
    	for(int i = 0 ; i < N ; i++) {
        	StringTokenizer st2 = new StringTokenizer(br.readLine());
        	int v = Integer.parseInt(st2.nextToken());
        	int c = Integer.parseInt(st2.nextToken());
        	int k = Integer.parseInt(st2.nextToken());
        	for(int j = 1; k > 0; j *= 2) {
        	    int count = Math.min(j, k);
        	    objects.add(new int[] {v * count, c * count});
        	    k -= count;
        	}
    	}
    	
    	int[][] dp = new int[M+1][objects.size()]; //dp[무게][현재 index]
    	
    	//초기값
    	int startV = objects.get(0)[0];
    	int startC = objects.get(0)[1];
    	for(int i = startV ; i <= M ; i++) {
    		dp[i][0] = startC;
    	}
    	
    	for(int i = 1 ; i < objects.size() ; i++) {
    		for(int m = 0 ; m <= M ; m++) {
    			int currentV = objects.get(i)[0];
    			int currentC = objects.get(i)[1];
    			
    			if(m < currentV) dp[m][i] = dp[m][i-1];
    			else dp[m][i] = Math.max(dp[m][i-1], dp[m - currentV][i-1] + currentC);
    		}
    	}
    	
    	System.out.println(dp[M][objects.size()-1]);
    }
}