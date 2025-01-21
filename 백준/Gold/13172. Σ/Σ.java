
import java.util.Scanner;

public class Main {
	
	public static long mod = 1000000007;
	public static long answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		for(int i = 0 ; i<m ; i++) {
			int n = sc.nextInt();
			int s = sc.nextInt();
			
			
			answer += s* multi(n, mod - 2) % mod;
			answer %= mod;
		}
		System.out.println(answer);
		
		
		// 페르마의 소정리 : a^p = a (mod p)
		// 역원 : n^(x-2)%x
	}
	
	public static long multi(long a, long b) {
		if(b == 1) return a;
		if(b % 2 == 1) return a * multi(a, b-1) % mod;
		long t = multi(a, b/2);
		return t*t % mod;
	}
}
