import java.io.*;
import java.util.*;

public class Main {
    public static class Seg {
        int[] segTree;

        public Seg(int n) {
            this.segTree = new int[4 * n];
        }

        void update(int flavor, int value, int node, int start, int end) {
            if (flavor < start || end < flavor) {
                return;
            }

            if (start == end) {
                segTree[node] += value;
            } else {
                int mid = (start + end) / 2;
                update(flavor, value, node * 2, start, mid);
                update(flavor, value, node * 2 + 1, mid + 1, end);
                segTree[node] = segTree[node * 2] +  segTree[node * 2 + 1];
            }
        }

        int query(int target, int node, int start, int end) {
            if (start == end) {
                return start;
            }

            int mid = (start + end) / 2;
            int leftSize = segTree[node * 2];

            if (target <=  leftSize) {
                return query(target, node * 2, start, mid);
            } else {
                return query(target - leftSize, node * 2 + 1, mid + 1, end);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n =  Integer.parseInt(br.readLine());
        int maxIndex = 1_000_001;

        Seg seg = new Seg(maxIndex);

        for  (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a =  Integer.parseInt(st.nextToken());
            if (a == 1) {
                // 사탕을 꺼내는 경우
                int b = Integer.parseInt(st.nextToken()); // 사탕을 꺼낼 순위

                int flav = seg.query(b, 1, 1, maxIndex);
                sb.append(flav).append("\n");
                seg.update(flav, -1, 1, 1, maxIndex);
            } else {
                // 사탕을 수정하는 경우
                int b = Integer.parseInt(st.nextToken()); // 사탕의 맛 (1 - 1_000_000)
                int c = Integer.parseInt(st.nextToken()); // 사탕의 개수

                seg.update(b, c, 1, 1, maxIndex);
            }
        }

        System.out.println(sb);
    }
}