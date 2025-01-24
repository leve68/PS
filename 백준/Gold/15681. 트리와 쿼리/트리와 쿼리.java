import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int[] size = new int[100000];
	public static ArrayList<Integer>[] tree = new ArrayList[100000];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt() - 1;
		int q = sc.nextInt();
		
		for(int i = 0 ; i<n-1 ; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			
			if(tree[u] == null) {
				tree[u] = new ArrayList<Integer>();
			}
			if(tree[v] == null) {
				tree[v] = new ArrayList<Integer>();
			}
			
			tree[u].add(v);
			tree[v].add(u);
		}
		//루트는 n개의 정점을 가짐
		countSubtreeNodes(r);
		
		for(int i = 0 ; i<q ; i++) {
			int cur = sc.nextInt() - 1;
			System.out.println(size[cur]);
		}
	}
	
	public static void countSubtreeNodes(int currentNode) {
		size[currentNode] = 1;
		for(int child : tree[currentNode]) {
			if(size[child] > 0) continue;
			
			countSubtreeNodes(child);
			
			size[currentNode] += size[child];
		}
	}
}

