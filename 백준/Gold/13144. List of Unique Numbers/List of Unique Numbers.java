import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> current = new HashSet<>();
        long sum = 0;
        int s = 0;
        int e = 0;
        int prevE = -1;
        while (e < n){
            if (!current.contains(arr[e])){
                current.add(arr[e]);
            } else {
                sum += count(e - s);
                if(prevE != -1){
                    sum -= count(prevE - s);
                }
                prevE = e;

                while (arr[s] != arr[e]){
                    current.remove(arr[s]);
                    s++;
                }
                s++;
            }
            e++;
        }

        sum += count(e - s);
        if(prevE != -1){
            sum -= count(prevE - s);
        }

        System.out.println(sum);
    }

    private static long count(long n) {
        return n * (n + 1) / 2;
    }
}