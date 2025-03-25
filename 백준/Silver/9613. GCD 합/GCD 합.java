import java.io.*;
import java.util.*;

class Main
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t = 0 ; t < T ; t++) {
			long sum = 0;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			for(int i = 0 ; i < n ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0 ; i < n-1 ; i++) {
				for(int j = i+1 ; j < n ; j++) {
					sum += gcd(arr[i], arr[j]);
				}
			}
			
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int gcd(int a, int b) {
		int max = Math.max(a, b);
		int min = Math.min(a, b);
		
		while(max%min != 0) {
			int tmp = max%min;
			max = Math.max(tmp, min);
			min = Math.min(tmp, min);
		}
		
		return min;
	}
}