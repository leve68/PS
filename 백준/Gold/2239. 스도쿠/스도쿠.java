import java.io.*;
import java.util.*;

public class Main {
    private static int n = 9;
    private static int[][] matrix = new int[n][n];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        solve();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(i, j, num)) {
                            matrix[i][j] = num;
                            if (solve()) {
                                return true;
                            }
                            matrix[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int row, int col, int num) {
        // 행 검사
        for (int j = 0; j < n; j++) {
            if (matrix[row][j] == num) {
                return false;
            }
        }

        // 열 검사
        for (int i = 0; i < n; i++) {
            if (matrix[i][col] == num) {
                return false;
            }
        }

        // 3x3 박스 검사
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (matrix[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}