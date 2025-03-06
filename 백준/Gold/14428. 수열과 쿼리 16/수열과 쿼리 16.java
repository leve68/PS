import java.io.*;
import java.util.*;

public class Main {
	static class Seg {
		int[] origin;
		int[] segTree;
	
		Seg(int n){
			origin = new int[n];
			origin[0] = Integer.MAX_VALUE;
			segTree = new int[4*n];
		}
		
		int init(int start, int end, int index) {
			if(start == end) return segTree[index] = start;
			int mid = (start+end)/2;
			int leftChild = init(start, mid, index*2);
			int rightChild = init(mid+1, end, index*2+1);
			if(origin[leftChild] <= origin[rightChild]) {
				return segTree[index] = leftChild;
			} else {
				return segTree[index] = rightChild;
			}
		}
		
		int getMinIndex(int start, int end, int index, int left, int right) {
			if(right < start || left > end) return 0;
			if(left <= start && end <= right) return segTree[index];
			
			int mid = (start+end)/2;
			int leftChild = getMinIndex(start, mid, index*2, left, right);
			int rightChild = getMinIndex(mid+1, end, index*2+1, left, right);
			
			if(origin[leftChild] <= origin[rightChild]) {
				return leftChild;
			} else {
				return rightChild;
			}
		}
		
		void update(int start, int end, int index, int originIndex, int value) {
			if(originIndex < start || end < originIndex) return;
			
			if(value < origin[segTree[index]] || (value == origin[segTree[index]] && originIndex < segTree[index])) {
				segTree[index] = originIndex;
			}
			
			if(start == end) return;
			
			if(segTree[index] == originIndex) {
				init(start, end, index);
			} else {
				int mid = (start+end)/2;
				update(start, mid, index*2, originIndex, value);
				update(mid+1, end, index*2+1, originIndex, value);
			}
		}
	}

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Seg seg = new Seg(N+1);
		for(int i = 1 ; i <= N ; i++) {
			seg.origin[i] = Integer.parseInt(st.nextToken());
		}
		
		//세그트리 만들기
		seg.init(1, N, 1);
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			switch(Integer.parseInt(st.nextToken())) {
				case 1:
					int originIndex = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());
					seg.origin[originIndex] = value;
					seg.update(1, N, 1, originIndex, value);
					break;
				case 2:
					int left = Integer.parseInt(st.nextToken());
					int right = Integer.parseInt(st.nextToken());
					sb.append(seg.getMinIndex(1, N, 1, left, right)).append("\n");
					break;
			}
		}
		System.out.println(sb);
	}
}