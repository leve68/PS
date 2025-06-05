import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        long cost1 = b;
        long cost2 = b + c;
        long cost3 = b + 2 * c;

        long[] a = new long[n];
        long cost = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
            cost += cost1 * a[i];
        }

        if (b >= c) {
            // 앞에서부터 3개씩 최적화
            for (int i = 2; i < n; i++) {
                // a[i-1] > a[i]인 경우 먼저 2개 처리
                if (a[i - 1] > a[i]) {
                    long minValue = Math.min(a[i - 2], a[i - 1] - a[i]);
                    cost -= (minValue * cost1) * 2;
                    cost += cost2 * minValue;
                    a[i - 2] -= minValue;
                    a[i - 1] -= minValue;
                }

                // 3개 묶음 처리
                if (a[i - 2] > 0 && a[i - 1] > 0 && a[i] > 0) {
                    long minValue = Math.min(a[i - 2], Math.min(a[i - 1], a[i]));
                    cost -= (minValue * cost1) * 3;
                    cost += cost3 * minValue;
                    a[i - 2] -= minValue;
                    a[i - 1] -= minValue;
                    a[i] -= minValue;
                }
            }

            // 남은 2개 묶음 처리
            for (int i = 1; i < n; i++) {
                if (a[i - 1] > 0 && a[i] > 0) {
                    long minValue = Math.min(a[i - 1], a[i]);
                    cost -= (minValue * cost1) * 2;
                    cost += cost2 * minValue;
                    a[i - 1] -= minValue;
                    a[i] -= minValue;
                }
            }
        }

        System.out.println(cost);
    }
}