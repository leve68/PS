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
        
        int x = Integer.parseInt(br.readLine());
        
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        
        for (int num : arr) {
            if (map.containsKey(x - num)) {
                count += map.get(x - num);
            }
            
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        System.out.println(count);
    }
}