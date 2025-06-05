import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> map = new TreeMap<>(); //true 들어옴, false 나감
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.put(a, map.getOrDefault(a, 0) + 1);
            map.put(b+1, map.getOrDefault(b+1, 0) - 1);
        }

        int[] time = new int[1_000_001];
        int current = 0;
        for(int i = 0; i < time.length; i++) {
            if(map.isEmpty()){
                time[i] = current;
                continue;
            }

            if(i == map.firstKey()) {
                current += map.get(map.firstKey());
                map.pollFirstEntry();
            }

            time[i] = current;
        }

        int q = Integer.parseInt(br.readLine());
        int[] queries = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i = 0; i < q; i++) {
            System.out.println(time[queries[i]]);
        }
    }
}