import java.io.*;
import java.util.*;
   
class Main {
    public static int R;
    public static int C;
    public static char[][] matrix;
    public static boolean[][] visited;
    public static int[] dy = {-1, 0, 1};
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        matrix = new char[R][C];
        visited = new boolean[R][C];
        
        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }
        
        int answer = 0;
        for(int i = 0; i < R; i++) {
            if(matrix[i][0] == '.') {
                if(dfs(i, 0)) {
                    answer++;
                }
            }
        }
        
        System.out.println(answer);
    }
    
    static boolean dfs(int y, int x) {
        if(x == C-1) {
            return true;
        }
        
        visited[y][x] = true;
        
        for(int i = 0; i < 3; i++) {
            int nextY = y + dy[i];
            int nextX = x + 1;
            
            if(nextY < 0 || nextY >= R || nextX >= C) continue;
            
            // 이미 방문했거나 장애물인 경우
            if(visited[nextY][nextX] || matrix[nextY][nextX] == 'x') continue;
            
            if(dfs(nextY, nextX)) {
                return true;
            }
        }
        
        return false;
    }
}