import java.io.*;
import java.util.*;

public class Main {
    static class Shortcut {
        int start, end, length;

        Shortcut(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Shortcut> shortcuts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            if (end <= D && length < (end - start)) {
                shortcuts.add(new Shortcut(start, end, length));
            }
        }

        int[] dp = new int[D + 1];

        // 초기화
        for (int i = 0; i <= D; i++) {
            dp[i] = i;
        }

        for (int i = 0; i <= D; i++) {
            if (i > 0) {
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            }

            // 현재 위치에서 출발하는 지름길들 확인
            for (Shortcut shortcut : shortcuts) {
                if (shortcut.start == i) {
                    dp[shortcut.end] = Math.min(dp[shortcut.end], dp[i] + shortcut.length);
                }
            }
        }

        System.out.println(dp[D]);
    }
}