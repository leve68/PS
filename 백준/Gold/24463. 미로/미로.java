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
    static int n;
    static int m;
    static char[][] matrix;
    static int[][] visited;
    static Point[] points;
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        //시작지점 끝지점 저장
        points = new Point[2];
        setPoints();

        Point start = points[0];
        Point end = points[1];

        visited[start.row][start.col] = 1;
        bfs(start, end);

        track(end);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(matrix[i][j]);
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    static void bfs(Point start, Point target){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            Point current = queue.poll();

            if(current.row == target.row && current.col == target.col){
                return;
            }

            for(int i = 0; i < d.length; i++){
                int nr = current.row + d[i][0];
                int nc = current.col + d[i][1];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m && visited[nr][nc] == 0 && matrix[nr][nc] == '@'){
                    visited[nr][nc] = visited[current.row][current.col] + 1;
                    queue.offer(new Point(nr, nc));
                }
            }
        }
    }

    static void track(Point end){
        Point current = end;

        while(visited[current.row][current.col] > 1){
            matrix[current.row][current.col] = '.';

            for(int i = 0; i < d.length; i++){
                int nr = current.row + d[i][0];
                int nc = current.col + d[i][1];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m &&
                        visited[nr][nc] == visited[current.row][current.col] - 1){
                    current = new Point(nr, nc);
                    break;
                }
            }
        }
        matrix[current.row][current.col] = '.';
    }

    static void setPoints(){
        int index = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == '.'){
                    if(i == 0 || i == n-1 || j == 0 || j == m-1){
                        points[index++] = new Point(i, j);
                    }
                    matrix[i][j] = '@';
                }
            }
        }
    }
}