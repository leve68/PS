import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long euler = n;

        for (long p = 2; p*p <= n; p++) {
            // p가 n의 소인수인 경우
            if (n % p == 0) {
                // 오일러 피 함수 공식 적용: euler = euler * (1 - 1/p)
                euler = euler / p * (p - 1);
                // n에서 소인수 p를 모두 제거
                while (n % p == 0) {
                    n = n / p;
                }
            }
        }

        // n이 1보다 크면 남은 n은 소수이므로 처리
        // 이 경우 n도 소인수로 처리해야 함: euler = euler * (1 - 1/n)
        System.out.println(n == 1 ? euler : euler / n * (n - 1));
    }
}