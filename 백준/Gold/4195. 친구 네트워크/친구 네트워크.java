import java.io.*;
import java.util.*;

public class Main {
    public static HashMap<String, String> parent;
    public static HashMap<String, Integer> count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int f = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            count = new HashMap<>();

            for (int j = 0; j < f; j++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                int networkSize = union(a, b);
                System.out.println(networkSize);
            }
        }
    }

    private static void makeSet(String x) {
        if (!parent.containsKey(x)) {
            parent.put(x, x);
            count.put(x, 1);
        }
    }

    private static String find(String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    private static int union(String a, String b) {
        makeSet(a);
        makeSet(b);

        String rootA = find(a);
        String rootB = find(b);

        if (rootA.equals(rootB)) {
            return count.get(rootA);
        }

        if (count.get(rootA) < count.get(rootB)) {
            parent.put(rootA, rootB);
            count.put(rootB, count.get(rootA) + count.get(rootB));
            return count.get(rootB);
        } else {
            parent.put(rootB, rootA);
            count.put(rootA, count.get(rootA) + count.get(rootB));
            return count.get(rootA);
        }
    }
}