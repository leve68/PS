import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int testCase = 1 ; testCase<=T ; testCase++) {
			int n = sc.nextInt();
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	        for (int i = 0; i <= n; i++) { 
	        	graph.add(new ArrayList<>());
	        }
			for(int i = 0; i<n-1 ; i++) {
				int parent = sc.nextInt();
				int child = sc.nextInt();
				
				graph.get(child).add(parent);
			}
			
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			
			//node1 다 타고 올라간뒤
			//node2 올라가면서 방문한 기록이 있는 노드가 정답
			//graph[child] = [parent, visited]
			//parent가 0이면 루트
            
			int currentNode = node1;
			graph.get(currentNode).add(0);
			while(graph.get(currentNode).size() != 0) {
				currentNode = graph.get(currentNode).get(0);
				if(graph.get(currentNode).size() == 0) break;
				graph.get(currentNode).add(0);
			}
			
			currentNode = node2;
			while(graph.get(currentNode).size() == 1 && graph.get(currentNode).get(0) != 0) {
				currentNode = graph.get(currentNode).get(0);
			}
			
			System.out.println(currentNode);
		}
	}
}

