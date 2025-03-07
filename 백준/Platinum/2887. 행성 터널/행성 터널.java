import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
	    int v1, v2, cost; //(v1 < v2)
	    
	    Edge(int v1, int v2, int cost) {
	        this.v1 = v1;
	        this.v2 = v2;
	        this.cost = cost;
	    }
	}
	public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        parent = new int[N];
        int[][] coords = new int[N][3]; // [i][0~2]: x,y,z 좌표
        for(int i = 0; i < N; i++) {
        	parent[i] = i;
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                coords[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

	    for(int axis = 0; axis < 3; axis++) {
			int[][] sorted = new int[N][2]; // [i][0]: 좌표값, [i][1]: 정점번호
			for(int i = 0; i < N; i++) {
			    sorted[i][0] = coords[i][axis];
			    sorted[i][1] = i;
			}
			
			Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
			 
			for(int i = 0; i < N-1; i++) {
			    int cost = sorted[i+1][0] - sorted[i][0];
			    int v1 = Math.min(sorted[i][1], sorted[i+1][1]);
			    int v2 = Math.max(sorted[i][1], sorted[i+1][1]);
			    pq.offer(new Edge(v1, v2, cost));
		    }
		}
	    
	    int sum = 0;
	    while(!pq.isEmpty()) {
	    	Edge e = pq.poll();
	    	if(find(e.v1) != find(e.v2)) {
	    		sum += e.cost;
	    		//union find
	    		union(e.v1, e.v2);
	    	} else continue;
	    }
	    System.out.println(sum);
    }
    
    static int find(int v) {
    	if(v == parent[v]) return v;
    	return parent[v] = find(parent[v]);
    }
    static void union(int v1, int v2) {
    	int f1 = find(v1);
    	int f2 = find(v2);
    	if(f1 != f2) parent[Math.max(f1, f2)] = Math.min(f1, f2);
    }
}