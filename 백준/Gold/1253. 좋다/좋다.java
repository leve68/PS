import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        long[] arr = new long[N];
        HashMap<Long, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        
        HashSet<Long> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            map.put(arr[i], map.get(arr[i]) - 1);
            
            for(int j = i+1; j < N; j++) {
                map.put(arr[j], map.get(arr[j]) - 1);
                
                long sum = arr[i] + arr[j];
                if(map.getOrDefault(sum, 0) > 0) {
                    set.add(sum);
                }
                
                map.put(arr[j], map.get(arr[j]) + 1);
            }
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        
        int answer = 0;
        for(Long sum : set) {
        	answer += map.getOrDefault(sum, 0);
        }
        
        System.out.println(answer);
    }
}