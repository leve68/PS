import java.util.Scanner;
 
class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
 
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int m = sc.nextInt();
            int[] b = new int[m];
 
            // a, b 배열 생성
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b[i] = sc.nextInt();
            }
 
            // 최댓값 계산
            int answer = Integer.MIN_VALUE;
 
            if (n < m) {
                // a가 더 작은 배열일 경우
                int range = m - n;
                for (int i = 0; i <= range; i++) {
                    int sum = 0;
                    for (int j = 0; j < n; j++) {
                        sum += a[j] * b[i + j];
                    }
                    answer = Math.max(answer, sum);
                }
            } else {
                // b가 더 작은 배열일 경우
                int range = n - m;
                for (int i = 0; i <= range; i++) {
                    int sum = 0;
                    for (int j = 0; j < m; j++) {
                        sum += b[j] * a[i + j];
                    }
                    answer = Math.max(answer, sum);
                }
            }
 
            System.out.println("#" + test_case + " " + answer);
        }
    }
}