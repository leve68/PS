import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int answer = Integer.MAX_VALUE;
		
		if(e == 0) {
			System.out.println(-1);
			return;
		}
		
		ArrayList<int[]>[] edgeMap = new ArrayList[v];
		for(int i = 0; i < v; i++) {
		    edgeMap[i] = new ArrayList<>(); 
		}
		
		for(int i = 0 ; i < e ; i++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			//a -> b 거리 c
			int a = Integer.parseInt(line.nextToken())-1;
			int b = Integer.parseInt(line.nextToken())-1;
			int c = Integer.parseInt(line.nextToken());
			edgeMap[a].add(new int[] {b, c});
		}
				
		int[] distance = new int[v];
		
		for(int start = 0 ; start < v ; start++) {
			Arrays.fill(distance, Integer.MAX_VALUE);
			
			PriorityQueue<int[]> pQ = new PriorityQueue<int[]>(new EdgeComparator());
			pQ.add(new int[] {start, 0});
            
            while(!pQ.isEmpty()) {
                int[] current = pQ.poll();
                //node : 현재 노드, dist: 출발 노드로부터 현재 노드까지의 거리
                int node = current[0];
                int dist = current[1];
                if(distance[node] < dist) continue;

                // 시작점으로 돌아왔고, 거리가 0이 아니면 사이클 발견
                if(node == start && dist > 0) {
                    answer = Math.min(answer, dist);
                    break;
                }
                
                for(int[] nextNode : edgeMap[node]) {
                    int next = nextNode[0];
                    int value = nextNode[1];
                    if(distance[next] > dist + value) {
                        distance[next] = dist + value;
                        pQ.add(new int[] {next, distance[next]});
                    }
                }
            }
		}
		
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}
}

class EdgeComparator implements Comparator<int[]>{
	@Override
	public int compare(int[] o1, int[] o2) {
		return o1[1] - o2[1];
	}
}