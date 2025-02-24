import java.io.*;
import java.util.*;

class Solution {
    static boolean[] current;
    static boolean[][] incompatible;
    static int count, N;
    
    static boolean isCompatible(int num) {
        for(int i = 1; i <= N; i++) {
            if(current[i] && incompatible[i][num]) return false;
        }
        return true;
    }
    
    static void generateCombinations(int start) {
        if(start > N) {
            boolean hasElement = false;
            for(int i = 1; i <= N; i++) {
                if(current[i]) {
                    hasElement = true;
                    break;
                }
            }
            if(hasElement) count++;
            return;
        }
        
        generateCombinations(start + 1);
        
        if(isCompatible(start)) {
            current[start] = true;
            generateCombinations(start + 1);
            current[start] = false;
        }
    }
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            current = new boolean[N + 1];
            incompatible = new boolean[N + 1][N + 1];
            count = 1;  // 빈 집합
            
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                incompatible[a][b] = incompatible[b][a] = true;
            }
            
            generateCombinations(1);
            
            System.out.println("#" + test_case + " " + count);
        }
    }
}