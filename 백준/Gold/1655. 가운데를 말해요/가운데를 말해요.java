import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int s = Integer.parseInt(br.readLine());
		maxHeap.add(s);
		sb.append(s).append("\n");
		
        //최대 힙의 크기는 최소 힙보다 1크거나 같다
        //최대 힙의 최대 <= 최소 힙의 최소
        //위 원칙이 위배되면 최대/최소 swap
		for(int i = 1 ; i < N ; i++) {
			int c = Integer.parseInt(br.readLine());
			if(maxHeap.size() == minHeap.size()) {
				maxHeap.add(c);
			} else {
				minHeap.add(c);
			}
			
			if(maxHeap.peek() > minHeap.peek()) {
				int a = maxHeap.poll();
				int b = minHeap.poll();
				maxHeap.add(b);
				minHeap.add(a);
			}
			
			sb.append(maxHeap.peek()).append("\n");
		}
		System.out.println(sb);
	}
}