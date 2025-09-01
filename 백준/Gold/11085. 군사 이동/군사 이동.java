import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static class Edge {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    public static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> b.w - a.w);
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int ws = Integer.parseInt(st.nextToken());
            int we = Integer.parseInt(st.nextToken());
            int ww = Integer.parseInt(st.nextToken());

            pq.add(new Edge(ws, we, ww));
        }

        parents = new int[p + 1];
        for (int i = 0; i < p + 1; i++) {
            parents[i] = i;
        }

        int answer = Integer.MAX_VALUE;
        while (find(v) != find(c)) {
            Edge ce = pq.poll();
            union(ce.s, ce.e);
            answer = Math.min(answer, ce.w);
        }

        System.out.println(answer);
    }

    private static int find(int child) {
        while (parents[child] != child) {
            child = parents[child];
        }
        return child;
    }

    private static void union(int i, int j) {
        int parent1 = find(i);
        int parent2 = find(j);
        if (parent1 > parent2) {
            parents[parent1] = parent2;
        } else {
            parents[parent2] = parent1;
        }
    }
}