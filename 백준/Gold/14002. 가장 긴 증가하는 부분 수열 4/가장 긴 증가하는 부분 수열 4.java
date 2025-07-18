import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        int[] index = new int[n];

        for (int i = 1; i < n; i++) {
            if (arr[i] > list.get(list.size() - 1)) {
                list.add(arr[i]);
                index[i] = list.size() - 1;
            } else {
                int tmp = lowerBound(list, arr[i]);
                list.set(tmp, arr[i]);
                index[i] = tmp;
            }
        }

        int[] lis = new int[list.size()];
        int cur = list.size() - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (index[i] == cur) {
                lis[cur] = arr[i];
                cur--;
            }

            if (cur == -1) break;
        }

        for (int i : lis){
            sb.append(i).append(" ");
        }

        System.out.println(list.size());
        System.out.println(sb);
    }

    private static int lowerBound(ArrayList<Integer> arr, int target) {
        int low = 0;
        int high = arr.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
}