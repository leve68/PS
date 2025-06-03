import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] players = new int[n][3];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[] totalScores = new int[n];
        
        for (int game = 0; game < 3; game++) {
            Map<Integer, Integer> count = new HashMap<>();
            
            for (int i = 0; i < n; i++) {
                int num = players[i][game];
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
            
            for (int i = 0; i < n; i++) {
                int num = players[i][game];
                if (count.get(num) == 1) {
                    totalScores[i] += num;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            System.out.println(totalScores[i]);
        }
    }
}