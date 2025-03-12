import java.util.*;
import java.io.*;

public class Main {

	static int V, E, cnt;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		parent = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			parent[i] = i;
		}

		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new int[] { a, b, c });
		}

		list.sort((o1, o2) -> o1[2] - o2[2]);

		cnt = 0;

		for (int i = 0; i < E; i++) {
			int[] cur = list.get(i);
			int a = cur[0];
			int b = cur[1];
			int c = cur[2];

			if (find(a) != find(b)) {
				cnt += c;
				union(a, b);
			}
		}
		System.out.println(cnt);
	}

	private static void union(int a, int b) {
		int a1 = find(a);
		int b1 = find(b);

		if (a1 < b1) {
			parent[b1] = a1;
		} else {
			parent[a1] = b1;
		}
	}

	private static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
	    return parent[a] = find(parent[a]);
	}
}
