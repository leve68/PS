import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        //3*N을 2*1, 1*2로 채우는 경우의 수
        
        //홀수 -> 0
        //2 -> 1*3 = 3
        //4 -> 3*3 + 2 = 11
        //6 -> 11*3 + 2*3 + 2 = 41
        //8 -> 41*3 + 11*2 + 3*2 + 2 = 123 + 22 + 6 + 2 = 153
        
        int[] dp = new int[31];
        
        dp[0] = 1;
        for(int i=2; i<=n; i+=2){
			dp[i] = dp[i-2]*3;
			for(int j=i-4; j>=0; j-=2){
			    dp[i] += dp[j]*2;
			}
        }
        
        System.out.println(dp[n]);
    }
}