import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = Integer.parseInt(br.readLine());
			int[] trees = new int[N];
			int target = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				if(target < trees[i]) target = trees[i];
			}
			for(int i = 0 ; i < N ; i++) {
				trees[i] = target - trees[i];
			}
			
			int odd = 0;
			int even = 0;
			for(int i = 0 ; i < N ; i++) {
				odd += trees[i]%2;
				even += trees[i]/2;
			}
			
			while(even > odd) {
				if(even == odd || even == (odd + 1)) break;
				even--;
				odd += 2;
			}
			
			if(odd >= even) {
				if(odd == even) System.out.println("#" + test_case + " " + odd*2);
				else System.out.println("#" + test_case + " " + (even * 2 + (odd - even) * 2 - 1));
			} else {
				System.out.println("#" + test_case + " " +(odd * 2 + (even - odd)*2));
			}
		}
    }
}