import java.io.*;
import java.util.*;

public class Main {
    private static TreeMap<Integer, Integer> treeMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n =  Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        treeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            treeMap.put(num, i);
        }

        int[] scores = new int[n];
        for (int card : treeMap.keySet()) {
            for (int i = card * 2; i <= 1_000_000; i += card) {
                if (treeMap.containsKey(i)) {
                    scores[treeMap.get(card)]++;
                    scores[treeMap.get(i)]--;
                }
            }
        }

        for (int i : scores) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}