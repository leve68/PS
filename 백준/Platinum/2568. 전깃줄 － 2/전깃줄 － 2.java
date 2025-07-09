import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<Integer> dp =  new ArrayList<>();
    private static TreeMap<Integer, Integer> link;
    private static TreeMap<Integer, Integer> index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n =  Integer.parseInt(br.readLine());
        link =  new TreeMap<>();
        index = new TreeMap<>((a, b) -> b - a);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            link.put(a, b);
        }

        for (int i :  link.keySet()) {
            if (dp.size() == 0) {
                // dp가 비어있는 경우
                dp.add(link.get(i));
                index.put(i, 0);
            } else if (dp.get(dp.size() - 1) < link.get(i)) {
                // 가장 뒤에 추가
                dp.add(link.get(i));
                index.put(i, dp.size() - 1);
            } else {
                // 중간에 갱신
                int dpIndex = binarySearch(link.get(i));
                dp.set(dpIndex, link.get(i));
                index.put(i, dpIndex);
            }
        }

        sb.append(n - dp.size()).append("\n");
        Set<Integer> set = new HashSet<>();
        int flag = dp.size() - 1;
        for (int i : index.keySet()) {
            if (index.get(i) == flag) {
                set.add(i);
                flag--;
            }
        }

        for (int l : link.keySet()) {
            if (set.contains(l)) continue;

            sb.append(l).append("\n");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int target) {
        int s = 0;
        int e = dp.size() - 1;

        while (s < e) {
            int mid = (s + e) / 2;
            if (dp.get(mid) < target) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }

        return s;
    }
}