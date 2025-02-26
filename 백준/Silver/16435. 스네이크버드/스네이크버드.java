import java.io.*;
import java.util.*;
 
class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int L = Integer.parseInt(st1.nextToken());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] heights = new int[N];
        for(int i = 0 ; i < N ; i++) {
        	heights[i] = Integer.parseInt(st2.nextToken());
        }
        Arrays.sort(heights);
        
        int got = 0;
        while(got < N && L >= heights[got]) {
        	got++;
        	L++;
        }
        
        System.out.println(L);
    }
}