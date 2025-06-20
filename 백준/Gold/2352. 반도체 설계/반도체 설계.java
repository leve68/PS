import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int idx = lowerBound(list, arr[i]);
            if (idx == list.size()) {
                list.add(arr[i]);
            } else {
                list.set(idx, arr[i]);
            }
        }

        System.out.println(list.size());
    }

    private static int lowerBound(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while(left < right) {
            int mid = (left + right) / 2;

            if(list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}