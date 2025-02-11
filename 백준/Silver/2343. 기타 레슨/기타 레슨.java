import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());
        
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] sum = new int[N+1];
        int maxLecture = 0;
        for(int i = 1 ; i <= N ; i++) {
            int lecture = Integer.parseInt(st2.nextToken());
            maxLecture = Math.max(maxLecture, lecture);
            sum[i] = lecture + sum[i-1];
        }
        
        int min = maxLecture;
        int max = sum[N];
        int answer = max;
        
        while(min <= max) {
            int maxSize = (min + max)/2;
            int count = 1;
            int current = 0;
            
            for(int i = 1; i <= N; i++) {
                if(sum[i] - sum[current] > maxSize) {
                    count++;
                    current = i-1;
                }
            }
            
            if(count <= M) {
                answer = Math.min(answer, maxSize);
                max = maxSize - 1;
            } else {
                min = maxSize + 1;
            }
        }
        
        System.out.println(answer);
    }
}