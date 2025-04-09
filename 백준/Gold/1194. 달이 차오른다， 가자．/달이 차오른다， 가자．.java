import java.io.*;
import java.util.*;
public class Main {
    static class Point {
        int row;
        int col;
        int keys;
        
        Point(int row, int col, int keys){
            this.row = row;
            this.col = col;
            this.keys = keys;
        }
    }
    public static int n;
    public static int m;
    static int[][][] visited;
    static int[][] d = {{0, 1},{1, 0},{0, -1},{-1, 0}};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] origin = new char[n][m];
        visited = new int[n][m][64];
        Point start = new Point(0, 0, 0);
        for(int i = 0 ; i < n ; i++) {
            origin[i] = br.readLine().toCharArray();
            
            for(int j = 0 ; j < m ; j++) {
                if(origin[i][j] == '0') start = new Point(i, j, 0);
            }
        }
        
        Queue<Point> q = new ArrayDeque<Point>();
        q.add(start);
        visited[start.row][start.col][0] = 1;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            int cr = cur.row;
            int cc = cur.col;
            int ck = cur.keys;
            
            if(origin[cr][cc] == '1') {
                min = Math.min(min, visited[cr][cc][ck] - 1);
                continue;
            }
            
            for(int i = 0 ; i < 4 ; i++) {
                int nr = cr + d[i][0];
                int nc = cc + d[i][1];
                if(nr>=0 && nr<n && nc>=0 && nc<m && origin[nr][nc] != '#') {
                    char cell = origin[nr][nc];
                    
                    if(visited[nr][nc][ck] == 0) {
                        if(cell >= 'A' && cell <= 'F') {
                            int doorBit = 1 << (cell - 'A');
                            if((ck & doorBit) != 0) {
                                visited[nr][nc][ck] = visited[cr][cc][ck] + 1;
                                q.add(new Point(nr, nc, ck));
                            }
                        } 
                        else if(cell >= 'a' && cell <= 'f') {
                            int nk = ck | (1 << (cell - 'a'));
                            visited[nr][nc][nk] = visited[cr][cc][ck] + 1;
                            q.add(new Point(nr, nc, nk));
                        }
                        else if(cell == '.' || cell == '0' || cell == '1') {
                            visited[nr][nc][ck] = visited[cr][cc][ck] + 1;
                            q.add(new Point(nr, nc, ck));
                        }
                    }
                }
            }
        }
        
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}