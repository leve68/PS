import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp =  new int[n + 1][10];
        // 자리수, 숫자 => 올 수 있는 숫자 개수

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = i + 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 10_007;
            }
        }

        // 1 -> 0,1, ... 9
        // 2 -> 00, 01, 02, ... ,88, 89, 99

        System.out.println(dp[n][9]);
    }
}