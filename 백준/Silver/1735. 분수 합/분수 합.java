import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a1 = Integer.parseInt(st.nextToken());
        int b1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int a2 = Integer.parseInt(st.nextToken());
        int b2 = Integer.parseInt(st.nextToken());

        int b3 = lcm(b1, b2);
        int tmp1 = b3 / b1;
        int tmp2 = b3 / b2;
        int a3 = a1 * tmp1 + a2 * tmp2;

        int gcd = gcd(a3, b3);
        System.out.println(a3/gcd + " " + b3/gcd);
    }

    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}