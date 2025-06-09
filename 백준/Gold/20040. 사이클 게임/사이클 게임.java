import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = find(Integer.parseInt(st.nextToken()));
            int b = find(Integer.parseInt(st.nextToken()));

            if(a == b){
                System.out.println(i+1);
                return;
            }

            parent[Math.max(a,b)] = Math.min(a,b);
        }

        System.out.println(0);
    }

    private static int find(int child) {
        if(parent[child] == child) {
            return child;
        }

        return parent[child] = find(parent[child]);
    }
}