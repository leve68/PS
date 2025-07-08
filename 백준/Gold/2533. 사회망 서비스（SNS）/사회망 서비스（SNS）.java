import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Integer>[] graph;
    private static int[][] dp;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList();
        }

        for (int i =  1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        // 해당 노드가 어답터인경우 0, 아닌 경우 1
        dp =  new int[n + 1][2];
        visited = new boolean[n + 1];
        check(1);


        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void check(int node){
        visited[node] = true;
        dp[node][0] = 1;
        for (int i = 0; i < graph[node].size(); i++) {
            int child = graph[node].get(i);

            if (!visited[child]) {
                check(child);
                dp[node][1] += dp[child][0];
                dp[node][0] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}