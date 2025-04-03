import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        
        List<Integer> result = new ArrayList<>();
        
        int left = 1;
        int right = 1;
        
        while (true) {
            long diff = (long)right * right - (long)left * left;
            
            if (diff == G) {
                result.add(right);
                right++;
                left++;
            } else if (diff < G) {
                right++;
            } else {
                left++;
            }

            if (right - left == 0 || right >= 100_000) {
                break;
            }
        }
        
        if (result.isEmpty()) {
            System.out.println("-1");
        } else {
            for (int a : result) {
                System.out.println(a);
            }
        }
    }
}