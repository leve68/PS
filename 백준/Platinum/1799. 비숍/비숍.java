import java.io.*;
import java.util.*;

class Main {
    static int[][] origin;
    static boolean[] diag1, diag2;
    static int n;
    static int maxBlack = 0, maxWhite = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        origin = new int[n][n];
        diag1 = new boolean[2 * n]; // ↘ 대각선 비숍 존재 여부 (row + col)
        diag2 = new boolean[2 * n]; // ↙ 대각선 비숍 존재 여부 (row - col + (n - 1))

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //0,0 부터 백트래킹 시작 (검은칸)
        setBishop(0, 0, 0, true);
        //0,1 부터 백트래킹 시작 (흰색칸)
        setBishop(0, 1, 0, false);

        System.out.println(maxBlack + maxWhite);
        br.close();
    }

    // 비숍을 놓는 함수
    static void setBishop(int row, int col, int count, boolean isBlack) {
        if (col >= n) { //다음 열로 이동할 때
            row++;
            col = (col % 2 == 0) ? 1 : 0; // 흰색or검은색 칸을 유지하며 이동
        }
        if (row >= n) { //끝까지 탐색했을 때
            if (isBlack) maxBlack = Math.max(maxBlack, count);
            else maxWhite = Math.max(maxWhite, count);
            return;
        }

        if (origin[row][col] == 1 && !diag1[row + col] && !diag2[row - col + (n - 1)]) {
            // 비숍을 놓을 수 있음
            diag1[row + col] = true;
            diag2[row - col + (n - 1)] = true;
            setBishop(row, col + 2, count + 1, isBlack);
            diag1[row + col] = false;
            diag2[row - col + (n - 1)] = false;
        }

        // 비숍을 놓지 않고 다음 칸 탐색
        setBishop(row, col + 2, count, isBlack);
    }
}
