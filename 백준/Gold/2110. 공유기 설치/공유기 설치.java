import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] point = new int[N];
        for(int i = 0 ; i < N ; i++) {
        	point[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(point);
        
        int low = 1;
        int high = point[N-1] - point[0] + 1;
        while(low < high) {
        	int mid = (low+high)/2;
        	
        	int count = 1;
        	int prev = point[0];
        	for(int i = 1 ; i < N ; i++) {
        		int cur = point[i];
        		if(cur - prev >= mid) {
        			count++;
        			prev = cur;
        		}
        	}
        	if(count < C) {
        		high = mid;
        	} else {
        		low = mid + 1;
        	}
        }
        
        System.out.println(low-1);
    }
}