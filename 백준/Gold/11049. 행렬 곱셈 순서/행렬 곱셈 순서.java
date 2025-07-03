import java.io.*;
import java.util.*;

public class Main {
    public static class Matrix {
        int row;
        int col;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        Matrix[] matrix = new Matrix[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            matrix[i] = new Matrix(a, b);
        }

        // 1차원 dp 아님 -> 2차원
        // dp[a][b] -> a~b까지 최소 곱셈 횟수
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                if (i == j) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = matrix[i].row * matrix[i].col * matrix[i + 1].col;
        }

        for (int l = 2; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                for (int k = i; k < i + l; k++){
                    dp[i][i + l] = Math.min(dp[i][i + l], dp[i][k] + dp[k + 1][i + l] + matrix[i].row * matrix[k].col * matrix[i + l].col);
                }
            }
        }

        System.out.println(dp[0][n-1]);
    }
}

// a b
// (a b)

// a b c
// (a (b c))
// ((a b) c)

// a b c d
// (((a b) c) d)
// ((a (b c)) d)
// ((a b) (c d))
// (a ((b c) d))
// (a (b (c d)))