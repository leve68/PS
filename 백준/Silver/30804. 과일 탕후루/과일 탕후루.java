import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	num[i] = Integer.parseInt(st.nextToken());
        }
        
        int start = 0;
        int maxLength = 0;
        Map<Integer, Integer> fruitCount = new HashMap<>();

        for(int end = 0; end < N; end++) {
            fruitCount.put(num[end], fruitCount.getOrDefault(num[end], 0) + 1);
            
            while(fruitCount.size() > 2) {
                int cnt = fruitCount.get(num[start]);
                if(cnt == 1) {
                    fruitCount.remove(num[start]);
                } else {
                    fruitCount.put(num[start], cnt - 1);
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        System.out.println(maxLength);
    }
}