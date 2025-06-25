import java.io.*;
import java.util.*;

public class Main {
    private static int[] parent;
    private static int[] sum;
    private static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 거리에 있는 아이들의 수
        int m = Integer.parseInt(st.nextToken()); // 아이들의 친구 관계 수
        int k = Integer.parseInt(st.nextToken()); // 울음소리가 공명하기 위한 최소 아이의 수

        st = new StringTokenizer(br.readLine());
        int[] c = new int[n];
        parent = new int[n];
        sum = new int[n];
        count = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
            sum[i] = c[i];
            count[i] = 1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            union(a, b);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            parent[i] = find(i);
        }
        for (int i = 0; i < n; i++) {
            set.add(parent[i]);
        }

        ArrayList<Integer> sumList = new ArrayList<>();
        ArrayList<Integer> countList = new ArrayList<>();
        for (int i : set) {
            sumList.add(sum[i]);
            countList.add(count[i]);
        }

        // list 결과를 이용한 배낭 문제
        int[][] dp = new int[sumList.size()][k];
        for (int j = 0; j < k; j++) {
            dp[0][j] = (countList.get(0) <= j) ? sumList.get(0) : 0;
        }

        for (int i = 1; i < sumList.size(); i++) {
            for (int j = 0 ; j < k ; j++) {
                if (countList.get(i) > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - countList.get(i)] + sumList.get(i));
                }
            }
        }

        System.out.println(dp[sumList.size() - 1][k - 1]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (rootA < rootB) {
                parent[rootB] = rootA;
                sum[rootA] += sum[rootB];
                count[rootA] += count[rootB];
            } else {
                parent[rootA] = rootB;
                sum[rootB] += sum[rootA];
                count[rootB] += count[rootA];
            }
        }
    }

    private static int find(int child) {
        if (parent[child] == child) {
            return child;
        }

        return parent[child] = find(parent[child]);
    }
}