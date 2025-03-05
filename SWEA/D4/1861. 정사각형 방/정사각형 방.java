import java.io.*;
import java.util.*;
    
class Solution {
    public static int[] parent;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test = 1 ; test <= T ; test++) {
            int N = Integer.parseInt(br.readLine());
            int[][] matrix = new int[N][N];
            parent = new int[N*N+1];
            for(int i = 0 ; i < N ; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N ; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    matrix[i][j] = num;
                    parent[num] = num; //부모
                }
            }
 
            int[][] d = {{0, 1},{1, 0},{0, -1},{-1, 0}};
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    for(int k = 0 ; k < 4 ; k++) {
                        int nextI = i + d[k][0];
                        int nextJ = j + d[k][1];
                        if(nextI >= 0 && nextI < N && nextJ >= 0 && nextJ < N) {
                            if(matrix[nextI][nextJ] == matrix[i][j] - 1) {
                                parent[matrix[i][j]] = find(matrix[nextI][nextJ]);
                            }
                        }
                    }
                }
            }
             
            //완전 압축
            for(int i = 1; i <= N*N; i++) {
                find(i);
            }
             
            int currentStart = parent[1];
            int currentMax = 1;
            int start = parent[1]; 
            int max = 1;    
             
            for(int i = 2 ; i < N*N+1 ; i++) {
                if(currentStart != parent[i]) {
                    if(currentMax > max) {
                        max = currentMax;
                        start = currentStart;
                    }
                    currentStart = parent[i];
                    currentMax = 1;
                } else {
                    currentMax++;
                }
            }
            if(currentMax > max) {
                max = currentMax;
                start = currentStart;
            }
         
            sb.append("#").append(test).append(" ").append(start).append(" ").append(max).append("\n");
        }
         
        System.out.println(sb);
    }
     
    static int find(int num) {
        if(parent[num] == num) return num;
         
        return parent[num] = find(parent[num]);
    }
}