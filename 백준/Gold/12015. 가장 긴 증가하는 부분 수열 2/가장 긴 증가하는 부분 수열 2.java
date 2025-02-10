import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i<N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		dp[0] = arr[0];
		int size = 1;
		
		for(int i = 1 ; i<N ; i++) {
			if(dp[size - 1] < arr[i]) {
				dp[size++] = arr[i];
			} else if(dp[size - 1] > arr[i]) {
				int index = findIndex(dp, size, arr[i]);
				dp[index] = arr[i];
			}
		}
		System.out.println(size);
	}
	
	public static int findIndex(int[] array, int size, int number) {
		int front = 0;
		int end = size - 1;
		
		while(front < end) {
			int mid = (front + end)/2;

			if(array[mid] < number) {
				front = mid + 1;
			} else {
				end = mid;
			}
		}
		return front;
	}
}
