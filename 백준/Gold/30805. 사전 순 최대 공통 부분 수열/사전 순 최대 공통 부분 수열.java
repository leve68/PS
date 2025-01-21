import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		//앞의 수가 가장 큰 수열
		//길이가 긴 수열
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Integer[] a = new Integer[n];
		for(int i = 0 ; i<n ; i++) {
			a[i] = sc.nextInt();
		}
		int m = sc.nextInt();
		Integer[] b = new Integer[m];
		for(int i = 0 ; i<m ; i++) {
			b[i] = sc.nextInt();
		}
		
		//가장 큰 공통 수 찾기
		ArrayList<Integer> answer = new ArrayList<>();
		while(true) {
			int maxA = Collections.max(Arrays.asList(a));
			if(maxA == -1) break;
			if(Arrays.asList(b).contains(maxA)) {
				answer.add(maxA);
				for(int i = 0 ; i<n ; i++) {
					if(a[i] == maxA) {
						a[i] = -1;
						break;
					}
					a[i] = -1;
				}
				for(int i = 0 ; i<n ; i++) {
					if(b[i] == maxA) {
						b[i] = -2;
						break;
					}
					b[i] = -2;
				}
			} else {
				for(int i = 0 ; i<n ; i++) {
					if(a[i] == maxA) {
						a[i] = -1;
						break;
					}
				}
			}
		}
		
		System.out.println(answer.size());
		System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining(" ")));
	}
}
