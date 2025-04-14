import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		for(int i = 0 ; i < N ; i++) {
			char[] c = br.readLine().toCharArray();
			
			boolean isPal = true;
			
			int s = 0;
			int e = c.length-1;
			
			while(s<e) {
				if(c[s] != c[e]) {
					isPal = false;
					break;
				}
				s += 1;
				e -= 1;
			}
			
			if(isPal) sb.append(0);
			else {
				char[] c1 = new char[e-s];
				char[] c2 = new char[e-s];
				
				int index = 0;
				for(int cur = s ; cur < e ; cur++) {
					c1[index] = c[cur];
					index++;
				}
				index = 0;
				for(int cur = s+1 ; cur <= e ; cur++) {
					c2[index] = c[cur];
					index++;
				}
				
				boolean isSim1 = true;
				int t1 = 0;
				int t2 = e - s - 1;
				while(t1<t2) {
					if(c1[t1] != c1[t2]) {
						isSim1 = false;
						break;
					}
					t1 += 1;
					t2 -= 1;
				}
				
				boolean isSim2 = true;
				t1 = 0;
				t2 = e - s - 1;
				while(t1<t2) {
					if(c2[t1] != c2[t2]) {
						isSim2 = false;
						break;
					}
					t1 += 1;
					t2 -= 1;
				}
				sb.append((isSim1 || isSim2)?1:2);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}