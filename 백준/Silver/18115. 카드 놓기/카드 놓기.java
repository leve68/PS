import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] commands = new int[N+1];
		for(int i = 0 ; i < N ; i++) {
			commands[N-i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		for(int i = 1 ; i <= N ; i++) {
			switch(commands[i]){
				case 1:
					dq.addFirst(i);
					break;
				case 2:
					int temp = dq.pollFirst();
					dq.addFirst(i);
					dq.addFirst(temp);
					break;
				case 3:
					dq.add(i);
					break;
			}
		}

		for(int c : dq) {
			sb.append(c).append(" ");
		}
		System.out.println(sb);
	}
}
