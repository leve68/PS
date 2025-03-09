import java.io.*;
import java.util.*;

class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        char[] schools = new char[N+1];
        boolean[] visited = new boolean[N+1];
        for(int i = 1 ; i <= N ; i++){
            schools[i] = st.nextToken().charAt(0);
        }
        ArrayList<int[]>[] edge = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            edge[v1].add(new int[] {v2, d});
            edge[v2].add(new int[] {v1, d});
        }

        int startV = 1;
        int mstSum = 0;
        visited[startV] = true;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) -> a[1] - b[1]);

        for(int i = 0 ; i < edge[startV].size() ; i++){
            int nv = edge[startV].get(i)[0];
            int nd = edge[startV].get(i)[1];
            if(schools[nv] != schools[startV]) pq.add(new int[] {nv, nd});
        }

        while(!pq.isEmpty()){
            int[] c = pq.poll();
            int cv = c[0];
            int cd = c[1];

            if(visited[cv]) continue;

            visited[cv] = true;
            mstSum += cd;

            for(int i = 0 ; i < edge[cv].size() ; i++){
                int nv = edge[cv].get(i)[0];
                int nd = edge[cv].get(i)[1];
                if(!visited[nv] && schools[nv] != schools[cv]) {
                    pq.add(new int[] {nv, nd});
                }
            }
        }

        boolean allConnected = true;
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                allConnected = false;
                break;
            }
        }

        if(allConnected) {
            System.out.println(mstSum);
        } else {
            System.out.println(-1);
        }
    }
}