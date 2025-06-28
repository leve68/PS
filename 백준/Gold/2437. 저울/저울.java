import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights);

        int max = weights[0];
        if (max != 1) {
            max = 0;
        } else {
            for (int i = 1 ; i < n ; i++) {
                if (weights[i] > max + 1) {
                    break;
                }

                max += weights[i];
            }
        }

        System.out.println(max + 1);
    }
}