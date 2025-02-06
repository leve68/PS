import java.io.*;
import java.util.*;

public class Main {
	public static ArrayList<Integer>[] tree;
	public static int[][] sparse;
	public static int[] level;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N+1];
		for(int i = 1 ; i<N+1 ; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		sparse = new int[19][N+1];
		level = new int[N+1];
		Arrays.fill(level, -1);
		
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int child = Integer.parseInt(st.nextToken());
			int parent = Integer.parseInt(st.nextToken());
			tree[parent].add(child);
			tree[child].add(parent);
		}

		dfs(1, 0);

		for(int i = 1 ; i < 19; i++) {
			for(int j = 1 ; j < N+1 ; j++) {
				sparse[i][j] = sparse[i-1][sparse[i-1][j]];
			}
		}

		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int large = level[a] > level[b] ? a : b;
			int small = level[a] > level[b] ? b : a;
			
			System.out.println(LCA(large,small));
		}
	}
	
	public static void dfs(int node, int depth) {
        level[node] = depth;
        for(int next : tree[node]) {
            if(level[next] > -1) {
                continue;
            }
            sparse[0][next] = node;
            dfs(next, depth + 1);
        }
    }
	
	public static int LCA(int a, int b) {
		//a,b의 레벨 맞추기
		int dif = level[a] - level[b];
		int temp = 0;
		while(dif > 0) {
			if(dif % 2 == 1) {
				a = sparse[temp][a];
			}
			temp++;
			dif >>= 1;
		}
		
		if(a == b) return a;
		
	    for(int i = 18; i >= 0; i--) { 
	        if(sparse[i][a] != sparse[i][b]) {
	            a = sparse[i][a];
	            b = sparse[i][b];
	        }
	    }
		return sparse[0][a];
	}
}