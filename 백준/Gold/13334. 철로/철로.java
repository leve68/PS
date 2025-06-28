import java.io.*;
import java.util.*;

public class Main {
    static class Line {
        int left, right;

        Line(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new Line(Math.min(a, b), Math.max(a, b));
        }

        int d = Integer.parseInt(br.readLine());
        Arrays.sort(lines, (a, b) -> a.right - b.right);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxCount = 0;

        for (Line line : lines) {
            pq.add(line.left);

            // 현재 선분의 끝점에서 길이 d만큼 뒤로 간 지점보다
            // 앞에 있는 시작점들 제거
            while (!pq.isEmpty() && pq.peek() < line.right - d) {
                pq.poll();
            }

            maxCount = Math.max(maxCount, pq.size());
        }

        System.out.println(maxCount);
    }
}