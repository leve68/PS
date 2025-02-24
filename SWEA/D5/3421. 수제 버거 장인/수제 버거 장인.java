import java.io.*;
import java.util.*;

class Solution {
    static ArrayList<Integer> current;
    static HashSet<String> incompatible;
    static int count, N;
    
    static boolean canAdd(int num) {
        for (int existingNum : current) {
            int min = Math.min(existingNum, num);
            int max = Math.max(existingNum, num);
            if (incompatible.contains(min + "," + max)) {
                return false;
            }
        }
        return true;
    }
    
    static void generateCombinations(int start) {
        if (start > N) {
            if (!current.isEmpty()) count++;  // 빈 집합이 아닐 때만 카운트
            return;
        }
        
        // start 숫자를 추가하지 않는 경우
        generateCombinations(start + 1);
        
        // start 숫자를 추가하는 경우
        if (canAdd(start)) {
            current.add(start);
            generateCombinations(start + 1);
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            int[][] incompatiblePairs = new int[M][2];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                incompatiblePairs[i][0] = Integer.parseInt(st.nextToken());
                incompatiblePairs[i][1] = Integer.parseInt(st.nextToken());
            }
            
            incompatible = new HashSet<>();
            current = new ArrayList<>();
            count = 1;  // 빈 집합 포함
            
            // 궁합이 맞지 않는 재료 쌍을 HashSet에 저장
            for (int[] pair : incompatiblePairs) {
                int min = Math.min(pair[0], pair[1]);
                int max = Math.max(pair[0], pair[1]);
                incompatible.add(min + "," + max);
            }
            
            // 모든 가능한 크기의 조합 생성
            generateCombinations(1);
            
            System.out.println("#" + test_case + " " + count);
        }
    }
}