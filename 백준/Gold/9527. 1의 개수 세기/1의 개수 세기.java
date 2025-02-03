import java.io.*;
import java.util.*;

public class Main {
	public static long[] dp = new long[63];
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long answer = 0;

		dp[0] = 0;
		dp[1] = 1;
		for(int i = 2 ; i < dp.length ; i++) {
			dp[i] = (1L << (i-1)) + dp[i-1]*2;
		}
				
		answer = solve(b) - solve(a-1);
		System.out.println(answer);
	}
	
	public static long solve(long n) {
		if (n <= 0) return 0;
		if (n == 1) return 1;
		 
	    long answer = 0;
	    long temp = n;
	    
	    for(int i = 63; i >= 0; i--) {
	        if((temp & (1L << i)) != 0) {
                answer += dp[i];  // 2^i-1까지의 1의 개수
                answer += (temp & ((1L << i) - 1)) + 1;  // i번째 비트가 1인 나머지 수들의 개수
                temp &= ~(1L << i);  // i번째 비트를 0으로
	        }
	    }
	    
	    return answer;
	}
}