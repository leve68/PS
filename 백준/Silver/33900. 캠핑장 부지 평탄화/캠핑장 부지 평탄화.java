import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][] plan = new int[r][c];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                plan[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int row = n - r + 1;
        int col = m - c + 1;
        int answer = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean flag = true;
                int target = map[i][j] - plan[0][0];

                for (int k = 0; k < r; k++) {
                    for (int l = 0; l < c; l++) {
                        if(target != (map[i+k][j+l] - plan[k][l])) {
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) {
                        break;
                    }
                }
                if(flag) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}