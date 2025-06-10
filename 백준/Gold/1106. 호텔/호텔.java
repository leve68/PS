import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] costs = new int[n];
        int[] customers = new int[n];
        int maxCustomer = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
            customers[i] = Integer.parseInt(st.nextToken());
            maxCustomer = Math.max(maxCustomer, customers[i]);
        }

        //최소 c명의 고객
        //최소의 비용으로 달성해야 함

        // dp[i] = i명을 유치하는 최소 비용
        int[] dp = new int[c + maxCustomer];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = customers[i]; j < c + maxCustomer; j++) {
                if (dp[j - customers[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - customers[i]] + costs[i]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = c ; i < c + maxCustomer; i++) {
            min = Math.min(min, dp[i]);
        }
        System.out.println(min);
    }
}