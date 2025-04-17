import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int data;
        int id;
        boolean isStart;

        public Point(int data, int id, boolean isStart) {
            this.data = data;
            this.id = id;
            this.isStart = isStart;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Point[] arr = new Point[n * 2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i * 2] = new Point(start, i, true);
            arr[i * 2 + 1] = new Point(end, i, false);
        }

        Arrays.sort(arr, (l, r) -> {
            if (l.data != r.data) return l.data - r.data;
            else if (l.isStart != r.isStart) return l.isStart ? 1 : -1;
            else return 0;
        });

        int max = 0;
        int cur = 0;

        for (int i = 0; i < 2 * n; i++) {
            if (arr[i].isStart) {
                cur++;
                max = Math.max(max, cur);
            } else {
                cur--;
            }
        }

        System.out.println(max);
    }
}