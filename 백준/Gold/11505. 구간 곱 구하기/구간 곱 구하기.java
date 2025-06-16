import java.io.*;
import java.util.*;

public class Main {
    public static class Seg {
        long divisor = 1_000_000_007;
        int[] origin;
        long[] segTree;

        Seg(int[] origin) {
            this.origin = origin.clone();
            this.segTree = new long[origin.length * 4];

            init(0, origin.length - 1, 1);
        }

        long init(int start, int end, int index) {
            if (start == end) {
                return segTree[index] = origin[start];
            }

            int mid = (start + end) / 2;
            return segTree[index] = init(start, mid, index * 2) * init(mid + 1, end, index * 2 + 1) % divisor;
        }

        long getMulti(int start, int end, int index, int left, int right) {
            if (right < start || end < left) return 1;
            if (left <= start && end <= right) return segTree[index];

            int mid = (start + end) / 2;
            return getMulti(start, mid, index * 2, left, right) * getMulti(mid + 1, end, index * 2 + 1, left, right) % divisor;
        }

        void update(int start, int end, int index, int targetIndex, int newValue) {
            if (targetIndex < start || end < targetIndex) return;

            if (start == end) {
                origin[targetIndex] = newValue;
                segTree[index] = newValue;
                return;
            }

            int mid = (start + end) / 2;
            update(start, mid, index * 2, targetIndex, newValue);
            update(mid + 1, end, index * 2 + 1, targetIndex, newValue);

            segTree[index] = segTree[index*2] * segTree[index*2 + 1] % divisor;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        Seg seg = new Seg(input);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                //b번째 수를 c로 업데이트
                if (seg.origin[b - 1] != 0) {
                    seg.update(0, n - 1, 1, b - 1, c);
                } else {
                    seg.origin[b - 1] = c;
                    seg.init(0, n - 1, 1);
                }
            } else {
                //b부터 c까지의 곱을 출력
                sb.append(seg.getMulti(0, n - 1, 1, b - 1, c - 1)).append("\n");
            }
        }

        System.out.println(sb);
    }
}