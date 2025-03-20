import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        ArrayList<Integer>[] edge = new ArrayList[N+1];
        for(int i = 0 ; i < N+1 ; i++) {
        	edge[i] = new ArrayList<Integer>();
        }
        int[][] time = new int[N+1][2];
        int[] income = new int[N+1];
        for(int i = 1 ; i <= N ; i++) {
        	int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        	time[i][1] = arr[0];
        	for(int j = 1 ; j < arr.length-1 ; j++) {
        		income[i]++;
        		edge[arr[j]].add(i);
        	}
        }

        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for(int i = 1 ; i <= N ; i++) {
        	if(income[i] == 0) {
        		q.add(i);
        		time[i][0] = time[i][1];
        	}
        }
        
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	for(int i = 0 ; i < edge[cur].size() ; i++) {
        		int nv = edge[cur].get(i);
        		income[nv]--;
        		time[nv][0] = Math.max(time[nv][0], time[nv][1] + time[cur][0]);
        		if(income[nv] == 0) q.add(nv);
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= N ; i++) {
        	sb.append(time[i][0]).append("\n");
        }
        System.out.println(sb);
    }
}