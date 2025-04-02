import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int answer = 0;
		HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
		for(int i = 0 ; i < k ; i++) {
			count.put(arr[i], count.getOrDefault(arr[i], 0)+1);
		}
		if(count.containsKey(c)) {
			answer = Math.max(answer, count.size());
		}else {
			answer = Math.max(answer, count.size()+1);
		}
		
		for(int i = k ; i < N ; i++) {
			count.put(arr[i], count.getOrDefault(arr[i], 0)+1);
			
			int j = i-k;
			if(count.get(arr[j])==1) {
				count.remove(arr[j]);
			}else {
				count.put(arr[j], count.get(arr[j])-1);
			}
			
			if(count.containsKey(c)) {
				answer = Math.max(answer, count.size());
			}else {
				answer = Math.max(answer, count.size()+1);
			}
		}
		
		for(int i = 0 ; i < k-1 ; i++) {
			count.put(arr[i], count.getOrDefault(arr[i], 0)+1);
			
			int j = N-k+i;
			if(count.get(arr[j])==1) {
				count.remove(arr[j]);
			}else {
				count.put(arr[j], count.get(arr[j])-1);
			}
			
			if(count.containsKey(c)) {
				answer = Math.max(answer, count.size());
			}else {
				answer = Math.max(answer, count.size()+1);
			}
		}
		
		System.out.println(answer);
	}
}
