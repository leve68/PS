import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] origin;
    public static int[][] dp; // (i,j)에서 시작하는 최대 길이
    public static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        origin = new int[N][N];
        dp = new int[N][N];
        
        // 배열 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        
        int max = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }
        
        System.out.println(max);
    }
    
    static int dfs(int r, int c) {
        if(dp[r][c] != -1) return dp[r][c];
        
        dp[r][c] = 1;
        
        for(int k = 0; k < 4; k++) {
            int nr = r + d[k][0];
            int nc = c + d[k][1];
            
            if(nr >= 0 && nr < N && nc >= 0 && nc < N && origin[nr][nc] < origin[r][c]) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }
        
        return dp[r][c];
    }
}