import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static class Point{
        int row;
        int col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[n][m];
        Point start = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken()) * -1;
                if(matrix[i][j] == -2){
                    start = new Point(i,j);
                }
            }
        }

        Deque<Point> q = new LinkedList<>();
        q.push(start);
        matrix[start.row][start.col] = 0;
        while(!q.isEmpty()){
            Point cur = q.poll();
            int cr = cur.row;
            int cc = cur.col;
            for(int i = 0; i < d.length; i++){
                int nr = cr + d[i][0];
                int nc = cc + d[i][1];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m){
                    if(matrix[nr][nc] == -1){
                        matrix[nr][nc] = matrix[cr][cc] + 1;
                        q.add(new Point(nr,nc));
                    }
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
