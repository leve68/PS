
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		StringTokenizer line = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(line.nextToken());
		}
		
		int[] mod = new int[m];
		long[] sum = new long[n];
		sum[0] = arr[0];
		mod[(int)(sum[0] % m)] = 1;
		
		long answer = 0;
		for(int i = 1 ; i < n ; i++) {
			sum[i] = sum[i-1]+arr[i];
			int curMod = (int) (sum[i] % m);
			answer += mod[curMod];
			mod[curMod]++;
		}
		answer += mod[0];
		
		System.out.println(answer);
	}
}