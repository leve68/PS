import java.io.*;
import java.util.*;

public class Main {
    public static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private static char[][] matrix;
    private static int[][] visited;
    private static Map<Character, int[]> dir = Map.of(
            'U', new int[]{-1, 0},
            'D', new int[]{1, 0},
            'L', new int[]{0, -1},
            'R', new int[]{0, 1}
    );
    private static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        matrix = new char[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        int flag = 1;
        answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    bfs(new Point(i, j), flag++);
                }
            }
        }

        System.out.println(answer);
    }

    private static void bfs(Point start, int flag) {
        visited[start.row][start.col] = flag;
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Point p = q.poll();
            int row = p.row;
            int col = p.col;
            int[] d = dir.get(matrix[row][col]);

            int nr = row + d[0];
            int nc = col + d[1];
            if (visited[nr][nc] == 0) {
                q.add(new Point(nr, nc));
                visited[nr][nc] = flag;
            } else if (visited[nr][nc] == flag) {
                answer++;
            }
        }
    }
}