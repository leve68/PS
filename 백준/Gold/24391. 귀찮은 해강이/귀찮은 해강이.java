import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = find(Integer.parseInt(st.nextToken()));
            int v2 = find(Integer.parseInt(st.nextToken()));
            int min = Math.min(v1, v2);
            int max = Math.max(v1, v2);

            parent[max] = min;
        }

        st = new StringTokenizer(br.readLine());
        int answer = 0;
        int prev = find(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < n; i++) {
            int current = find(Integer.parseInt(st.nextToken()));
            if(prev != current) {
                answer++;
            }
            prev = current;
        }
        System.out.println(answer);
    }

    private static int find(int child){
        if(parent[child] == child){
            return child;
        }

        return parent[child] = find(parent[child]);
    }
}