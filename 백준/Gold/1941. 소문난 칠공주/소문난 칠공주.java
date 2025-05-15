import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static char[][] matrix;
    static int answer = 0;
    static boolean[] selected;
    static int[][] d = {{1, 0},{-1, 0}, {0, 1},{0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        matrix = new char[5][5];
        selected = new boolean[25];

        for (int i = 0; i < 5; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int idx, int count, int yCnt) {
        if (yCnt >= 4) {
            return;
        }

        if (count == 7) {
            if (isConnected()) {
                answer++;
            }
            return;
        }

        if (25 - idx < 7 - count) {
            return;
        }

        for (int i = idx; i < 25; i++) {
            selected[i] = true;
            int row = i / 5;
            int col = i % 5;

            if (matrix[row][col] == 'Y') {
                dfs(i + 1, count + 1, yCnt + 1);
            } else {
                dfs(i + 1, count + 1, yCnt);
            }

            selected[i] = false;
        }
    }

    private static boolean isConnected() {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];

        Point start = null;
        for (int i = 0; i < 25; i++) {
            if (selected[i]) {
                start = new Point(i / 5, i % 5);
                break;
            }
        }

        queue.offer(start);
        visited[start.row][start.col] = true;
        int connectedCount = 1;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = current.row + d[i][0];
                int nc = current.col + d[i][1];
                int nextIdx = nr * 5 + nc;

                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 &&
                        !visited[nr][nc] && selected[nextIdx]) {
                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc));
                    connectedCount++;
                }
            }
        }

        return connectedCount == 7;
    }
}