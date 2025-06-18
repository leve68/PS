import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            ArrayList<Integer> leftList = map.getOrDefault(left, new ArrayList<>());
            leftList.add(height);
            map.put(left, leftList);
            ArrayList<Integer> rightList = map.getOrDefault(right, new ArrayList<>());
            rightList.add(height * -1);
            map.put(right, rightList);
        }

        TreeMap<Integer, ArrayList<Integer>> sortedMap = new TreeMap<>(map);
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        int prevH = 0;

        for (int i : sortedMap.keySet()) {
            ArrayList<Integer> list = map.get(i);

            for (int j : list) {
                if(j > 0) {
                    countMap.put(j, countMap.getOrDefault(j, 0) + 1);
                } else {
                    int count = countMap.get(j * -1);
                    if(count > 1) {
                        countMap.put(j * -1, count - 1);
                    } else {
                        countMap.remove(j * -1);
                    }
                }
            }

            if (!countMap.isEmpty() && countMap.lastKey() != prevH) {
                sb.append(i).append(" ").append(countMap.lastKey()).append(" ");
                prevH = countMap.lastKey();
            } else if (countMap.isEmpty() && prevH != 0) {
                sb.append(i).append(" ").append(0).append(" ");
                prevH = 0;
            }
        }

        System.out.println(sb);
    }
}