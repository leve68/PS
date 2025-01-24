import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int min = Integer.MAX_VALUE;
		int n = sc.nextInt();
		int[] pop = new int[n];
		for(int i = 0 ; i<n ; i++) {
			pop[i] = sc.nextInt();
		}
		int[][] matrix = new int[n][];
		for(int i = 0 ; i<n ; i++) {
			int count = sc.nextInt();
			int[] nodes = new int[count];
			for(int j = 0 ; j<count ; j++) {
				nodes[j] = sc.nextInt() - 1;
			}
			matrix[i] = nodes;
		}
				
		for(int i = 1 ; i < 1<<n - 1 ; i++) {
			int[] divide = new int[n];
			for(int j = 0 ; j<n ; j++) {
				if((i & (1<<j)) != 0) {
					divide[j] = 1;
				}
			}
			
			int firstZero = -1;
			int firstOne = -1;
			for(int j = 0; j < n; j++) {
			   if(firstZero == -1 && divide[j] == 0) {
			       firstZero = j;
			   }
			   if(firstOne == -1 && divide[j] == 1) {
			       firstOne = j;
			   }
			   if(firstZero != -1 && firstOne != -1) break;
			}
			
			//방문해야하는 지점 0
			//0을 모두 1로 만들면 성공
			int[] visited = divide.clone();
			Queue<Integer> q = new LinkedList<Integer>();
			visited[firstZero] = 1;
			q.add(firstZero);
			while(!q.isEmpty()) {
				int currentIndex = q.poll();
				int[] nextNodes = matrix[currentIndex];
				for(int j = 0 ; j<nextNodes.length ; j++) {
					if(visited[nextNodes[j]] == 0) {
						visited[nextNodes[j]] = 1;
						q.add(nextNodes[j]);
					}
				}
			}
			
			if(checkInt(visited, 0)) {
				continue;
			}
			
			
			//성공한 경우
			//방문해야하는 지점 1
			//1을 모두 0으로 만들면 성공
			visited = divide.clone();
			visited[firstOne] = 0;
			q.add(firstOne);
			while(!q.isEmpty()) {
				int currentIndex = q.poll();
				int[] nextNodes = matrix[currentIndex];
				for(int j = 0 ; j<nextNodes.length ; j++) {
					if(visited[nextNodes[j]] == 1) {
						visited[nextNodes[j]] = 0;
						q.add(nextNodes[j]);
					}
				}
			}
			
			if(checkInt(visited, 1)) {
				continue;
			}
			
			//성공한 경우 인구수구하기
			int sum1 = 0;
			int sum2 = 0;
			for(int j = 0 ; j<n ; j++) {
				if(divide[j] == 0) sum1+=pop[j];
				else sum2 += pop[j];
			}
			
			int answer = 0;
			if(sum2>sum1) answer = sum2-sum1;
			else answer = sum1 - sum2;
			min = Math.min(answer, min);
		}
		
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	
	//target이 있으면 true 반환
	public static boolean checkInt(int[] arr, int target) {
		for(int i = 0 ; i<arr.length ; i++) {
			if(arr[i] == target) return true;
		}
		return false;
	}

}

