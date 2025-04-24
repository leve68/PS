import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 첫 번째 차이 구하기
        int gcdValue = 0;
        for(int i = 1; i < n; i++) {
            int diff = Math.abs(arr[i] - arr[0]);
            if(diff != 0) {
                if(gcdValue == 0) gcdValue = diff;
                else gcdValue = gcd(gcdValue, diff);
            }
        }

        // 모든 차이의 GCD의 약수들 찾기
        for(int i = 2; i <= gcdValue; i++) {
            if(gcdValue % i == 0) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}