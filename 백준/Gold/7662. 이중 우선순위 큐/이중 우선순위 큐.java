import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < T ; i++) {
			TreeMap<Integer, Integer> minQ = new TreeMap();
			int Q = Integer.parseInt(br.readLine());
			for(int j = 0 ; j < Q ; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int k = Integer.parseInt(st.nextToken());
				
				if(command.equals("I")) {
					if(minQ.containsKey(k)) {
						minQ.put(k, minQ.get(k)+1);
					} else {
						minQ.put(k, 1);
					}
				} else {
					if(minQ.isEmpty()) continue;

					if(k == 1) {
						//최대값 삭제
						int max = minQ.lastKey();
						if(minQ.get(max) > 1) {
							minQ.put(max, minQ.get(max)-1);
						} else {
							minQ.remove(max);
						}
					} else {
						//최소값 삭제
						int min = minQ.firstKey();
						if(minQ.get(min) > 1) {
							minQ.put(min, minQ.get(min)-1);
						} else {
							minQ.remove(min);
						}
					}
				}
			}
			if(minQ.isEmpty()) System.out.println("EMPTY");
			else System.out.println(minQ.lastKey() + " " + minQ.firstKey());
		}
		
	}
}
