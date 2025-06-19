import java.io.*;
import java.util.*;

public class Main {
    public static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair pair = (Pair) obj;
            return a == pair.a && b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public static class Edge {
        String state;
        int cost;

        public Edge(String state, int cost) {
            this.state = state;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        HashMap<Pair, Integer> edgeMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            Pair p = new Pair(l, r);
            if (edgeMap.containsKey(p)) {
                edgeMap.put(p, Math.min(edgeMap.get(p), c));
            } else {
                edgeMap.put(p, c);
            }
        }

        int[] sorted = a.clone();
        Arrays.sort(sorted);
        String target = Arrays.toString(sorted);

        HashMap<String, Integer> dist = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

        String start = Arrays.toString(a);
        dist.put(start, 0);
        pq.add(new Edge(start, 0));

        int answer = -1;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            String currentState = current.state;
            int currentCost = current.cost;

            if (dist.containsKey(currentState) && dist.get(currentState) < currentCost) {
                continue;
            }

            if (currentState.equals(target)) {
                answer = currentCost;
                break;
            }

            int[] currentArray = stringToArray(currentState);
            for (Pair p : edgeMap.keySet()) {
                int[] nextArray = swap(currentArray, p.a, p.b);
                String nextState = Arrays.toString(nextArray);
                int nextCost = currentCost + edgeMap.get(p);

                if (!dist.containsKey(nextState) || dist.get(nextState) > nextCost) {
                    dist.put(nextState, nextCost);
                    pq.add(new Edge(nextState, nextCost));
                }
            }
        }

        System.out.println(answer);
    }

    private static int[] swap(int[] arr, int a, int b) {
        int[] copy = arr.clone();
        int temp = copy[a];
        copy[a] = copy[b];
        copy[b] = temp;
        return copy;
    }

    private static int[] stringToArray(String str) {
        str = str.replace("[", "").replace("]", "").replace(" ", "");
        String[] parts = str.split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        return arr;
    }
}