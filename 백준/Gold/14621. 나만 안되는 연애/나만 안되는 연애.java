import java.util.*;
import java.io.*;

public class Main {

	static int n, m, cnt;
	static int[] col, parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		col = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			char c = st.nextToken().charAt(0);
			if (c == 'W')
				col[i] = 1;
		}

		parent = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			parent[i] = i;
		}

		cnt = 0;

		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (col[a] == col[b])
				continue;
			list.add(new int[] { a, b, c });
		}

		list.sort((o1, o2) -> o1[2] - o2[2]);

		for (int i = 0; i < list.size(); i++) {
			int[] cur = list.get(i);
			int a = cur[0];
			int b = cur[1];
			int c = cur[2];

			if (find(a) != find(b)) {
				cnt += c;
				union(a, b);
			}
		}

		for (int i = 1; i < n + 1; i++) {
			if (find(i) != 1) {
				System.out.println("-1");
				return;
			}
		}

		System.out.println(cnt);
	}

	private static void union(int a, int b) {
		int a1 = find(a);
		int b1 = find(b);

		if (a1 > b1)
			parent[a1] = b1;
		else
			parent[b1] = a1;
	}

	private static int find(int a) {
		if (a == parent[a])
			return a;
		return parent[a] = find(parent[a]);
	}

}
