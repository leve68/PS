import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[v1].add(new int[] {v2, d});
            graph[v2].add(new int[] {v1, d});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // [v1, v2, 가중치]
        boolean[] visited = new boolean[N+1];
        ArrayList<int[]>[] mst = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            mst[i] = new ArrayList<>();
        }
        
        int sum = 0;
        pq.add(new int[] {0, 1, 0});
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int prev = curr[0];
            int node = curr[1];
            int weight = curr[2];
            
            if(visited[node]) continue;
            visited[node] = true;
            sum += weight;
            
            if(prev != 0) {
                mst[prev].add(new int[] {node, weight});
                mst[node].add(new int[] {prev, weight});
            }
            
            // 인접 노드들을 우선순위 큐에 추가
            for(int[] next : graph[node]) {
                int nextNode = next[0];
                int nextWeight = next[1];
                if(!visited[nextNode]) {
                    pq.add(new int[] {node, nextNode, nextWeight});
                }
            }
        }
        
        int Q = Integer.parseInt(br.readLine());
        // x->y 경로에서 가장 큰 간선 제거
        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            visited = new boolean[N+1];
            ArrayDeque<int[]> stack = new ArrayDeque<>(); // [노드, 최대 가중치]
            stack.add(new int[] {x, 0});
            visited[x] = true;
            
            while(!stack.isEmpty() && !visited[y]) {
                int[] current = stack.peekLast();
                int c = current[0];
                int maxWeight = current[1];
                
                boolean found = false;
                for(int[] next : mst[c]) {
                    int nv = next[0];
                    int nd = next[1];
                    if(!visited[nv]) {
                        stack.add(new int[] {nv, Math.max(maxWeight, nd)});
                        visited[nv] = true;
                        found = true;
                        break;
                    }
                }
                
                if(!found) stack.pollLast();
            }
            
            int max = 0;
            if(visited[y]) {
                max = stack.peekLast()[1];
            }
            
            sb.append(sum - max).append("\n");
        }
        
        System.out.println(sb);
    }
}