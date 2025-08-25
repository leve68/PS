import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static char[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        matrix = new char[n][n];

        for (int i = 0; i < n; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        int maxCandy = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j + 1 < n) {
                    // 교환
                    swap(i, j, i, j + 1);
                    int rowMax = getRowMax(i);
                    int col1Max = getColMax(j);
                    int col2Max = getColMax(j + 1);
                    maxCandy = Math.max(maxCandy, Math.max(rowMax, Math.max(col1Max, col2Max)));
                    // 되돌리기
                    swap(i, j, i, j + 1);
                }

                if (i + 1 < n) {
                    // 교환
                    swap(i, j, i + 1, j);
                    int row1Max = getRowMax(i);
                    int row2Max = getRowMax(i + 1);
                    int colMax = getColMax(j);
                    maxCandy = Math.max(maxCandy, Math.max(colMax, Math.max(row1Max, row2Max)));
                    // 되돌리기
                    swap(i, j, i + 1, j);
                }
            }
        }

        System.out.println(maxCandy);
    }

    private static void swap(int r1, int c1, int r2, int c2) {
        char temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }

    private static int getRowMax(int row) {
        int maxLength = 1;
        int count = 1;

        for (int j = 1; j < n; j++) {
            if (matrix[row][j] == matrix[row][j - 1]) {
                count++;
            } else {
                maxLength = Math.max(maxLength, count);
                count = 1;
            }
        }
        maxLength = Math.max(maxLength, count);

        return maxLength;
    }

    private static int getColMax(int col) {
        int maxLength = 1;
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (matrix[i][col] == matrix[i - 1][col]) {
                count++;
            } else {
                maxLength = Math.max(maxLength, count);
                count = 1;
            }
        }
        maxLength = Math.max(maxLength, count);

        return maxLength;
    }
}