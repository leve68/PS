import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weights = new int[n];
        int sumWeight = 0;
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            sumWeight += weights[i];
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] beads = new int[m];
        for (int i = 0; i < m; i++) {
            beads[i] = Integer.parseInt(st.nextToken());
        }


        //구슬의 무게 + i개의 무게추 = j개의 무게추 가 성립하면 Y 아니면 N
        //dp 구할 수 있으면 true, 못 구하면 false
        boolean[] dp = new boolean[40001];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sumWeight; j >= 0; j--) {
                if (dp[j]) {
                    dp[j + weights[i]] = true;
                }
            }

            for (int j = 0; j < sumWeight; j++) {
                if (dp[j]) {
                    dp[Math.abs(j - weights[i])] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (dp[beads[i]]) {
                sb.append("Y");
            } else {
                sb.append("N");
            }
            sb.append(" ");
        }


        System.out.println(sb);
    }
}