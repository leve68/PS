import java.io.*;
import java.util.*;

public class Solution {
	static class Island{
		int x;
		int y;
		
		Island(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge {
		int s;
		int e;
		double weight;
		
		Edge(int s, int e, double wieght){
			this.s = s;
			this.e = e;
			this.weight = wieght;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int test = 1 ; test <= T ; test++) {
        	int N = Integer.parseInt(br.readLine());
        	Island[] islands = new Island[N];
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0 ; i < N ; i++) {
        		islands[i] = new Island(Integer.parseInt(st.nextToken()), 0);
        	}
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0 ; i < N ; i++) {
        		islands[i].y = Integer.parseInt(st.nextToken());
        	}
        	double E = Double.parseDouble(br.readLine());
        	
        	Set<Integer> remain = new HashSet<Integer>();
        	PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a,b)->Double.compare(a.weight, b.weight));
        	for(int i = 1 ; i < N ; i++) {
        		remain.add(i);
        		pq.add(new Edge(0, i, getWeight(islands[0], islands[i], E)));
        	}
        	
        	double sum = 0;
        	while(remain.size() > 0) {
        		Edge ne = pq.poll();
        		if(!remain.contains(ne.e)) continue;
        		
        		sum += ne.weight;
        		remain.remove(ne.e);
        		for(int n : remain) {
        			pq.add(new Edge(ne.e, n, getWeight(islands[ne.e], islands[n], E)));
        		}
        	}
        	
        	sb.append("#").append(test).append(" ").append(Math.round(sum)).append("\n");
        }
        
        System.out.println(sb);
    }
    
    public static double getWeight(Island a, Island b, double e) {
    	double l = Math.pow((a.x-b.x), 2) + Math.pow((a.y-b.y), 2);
    	return e * l;
    }
}