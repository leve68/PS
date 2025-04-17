import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int data;
        int sum;

        public Node(int data, int sum) {
            this.data = data;
            this.sum = sum;
        }
    }
    public  static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Node[] dp = new Node[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new Node(arr[i], arr[i]);
            for (int j = 0 ; j < i ; j++) {
                if(dp[j].data < dp[i].data) {
                    if (dp[j].sum + arr[i] > dp[i].sum) {
                        dp[i].sum = dp[j].sum + arr[i];
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i].sum);
        }
        System.out.println(max);
    }
}
