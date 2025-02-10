import java.io.*;
import java.util.*;

public class Main {
	public static int[] parent = new int[100001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		int answer = 0;
		
		for(int i = 0 ; i<=N ; i++) {
			parent[i] = i;
		}
		for(int i = 0 ; i<P ; i++) {
			int cur = Integer.parseInt(br.readLine());
			cur = find(cur);
			if(cur != 0) unite(cur, cur-1);
			else break;
			answer++;
		}
		
		System.out.println(answer);
	}
	
	public static int find(int v) {
		if(parent[v] == v) return v;
		else return parent[v] = find(parent[v]);
	}
	
	public static void unite(int v1, int v2) {
		v1 = find(v1);
		v2 = find(v2);
		
		parent[v1] = v2;
	}
}
