import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int test = 1 ; test <= 10 ; test++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] edge = new ArrayList[V+1];
			for(int i = 1 ; i <= V ; i++) {
				edge[i] = new ArrayList<Integer>();
			}
			//들어오는 간선 수 저장
			int[] incomingCount = new int[V+1];
			//간선 저장
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < E ; i++) {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				edge[s].add(e);
				incomingCount[e]++;
			}
			
			Deque<Integer> q = new LinkedList<Integer>();
			
			for(int i = 1 ; i <= V ; i++) {
				if(incomingCount[i] == 0) q.add(i);
			}
			
			sb.append("#").append(test).append(" ");
			
			while(!q.isEmpty()) {
				int v = q.poll();
				sb.append(v).append(" ");
				for(int nv : edge[v]) {
					if(--incomingCount[nv] == 0) q.add(nv);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}