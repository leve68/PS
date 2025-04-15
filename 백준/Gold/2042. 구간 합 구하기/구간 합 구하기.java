import java.io.*;
import java.util.*;

public class Main {
	static class Seg {
		long[] origin;
		long[] segTree;
		
		Seg(long[] origin){
			this.origin = origin.clone();
			this.segTree = new long[origin.length * 4];
			
			init(0, origin.length-1, 1); //1번이 루트 노드 index
		}
		
		//segTree 만들기
		long init(int start, int end, int index) {
			if(start == end) return segTree[index] = origin[start]; //리프노드
			int mid = (start+end)/2;
			return segTree[index] = init(start, mid, index*2) + init(mid+1, end, index*2+1); //왼쪽 자식 + 오른쪽 자식
		}
		
		//start, end 는 index노드가 가진 구간합 범위
		//left, right 는 구하고자 하는 범위
		long getSum(int start, int end, int index, int left, int right) {
			if(left > end || right < start) return 0;
			if(left <= start && end <= right) return segTree[index];
			//일부만 걸치는 경우
			int mid = (start+end)/2;
			return getSum(start, mid, index*2, left, right) + getSum(mid+1, end, index*2+1, left, right);
		}
		
		//targetIndex 노드를 dif만큼 수정
		//targetIndex 도달 시 origin도 함께 업데이트
		void update(int start, int end, int index, int targetIndex, long dif) {
			if(targetIndex < start || end < targetIndex) return;
			segTree[index] += dif;
			if(start == end) {
			    origin[start] += dif;
			    return;
			}
			
			int mid = (start+end)/2;
			update(start, mid, index*2, targetIndex, dif);
			update(mid+1, end, index*2+1, targetIndex, dif);
		}
	}

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] input = new long[N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			input[i] = Long.parseLong(st.nextToken());
		}
		
		Seg seg = new Seg(input);
		
		for(int i = 0 ; i < M + K ; i++) {
			st = new StringTokenizer(br.readLine());
			switch(Integer.parseInt(st.nextToken())) {
				case 1:
					int targetIndex = Integer.parseInt(st.nextToken())-1;
					long dif = Long.parseLong(st.nextToken()) - seg.origin[targetIndex];
					seg.update(0, N-1, 1, targetIndex, dif);
					break;
				case 2:
					int left = Integer.parseInt(st.nextToken())-1;
					int right = Integer.parseInt(st.nextToken())-1;
					sb.append(seg.getSum(0, N-1, 1, left, right)).append("\n");
					break;
			}
		}
		
		System.out.println(sb);
	}
}