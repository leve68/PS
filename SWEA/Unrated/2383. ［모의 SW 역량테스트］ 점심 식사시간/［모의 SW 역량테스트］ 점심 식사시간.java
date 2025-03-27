import java.io.*;
import java.util.*;
public class Solution {
    static class Point {
        int row;
        int col;
        int weight;
        
        Point(int row, int col, int weight){
            this.row = row;
            this.col = col;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int test = 1 ; test <= T ; test++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Point> people = new ArrayList<Point>();
            ArrayList<Point> exits = new ArrayList<Point>();
            for(int i = 0 ; i < N ; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N ; j++) {
                    int c = Integer.parseInt(st.nextToken());
                    if(c == 1) {
                        people.add(new Point(i, j, c));
                    } else if(c > 1) {
                        exits.add(new Point(i, j, c));
                    }
                }
            }
                        
            int answer = Integer.MAX_VALUE;
            int cur = 0;
            while(cur < 1<<people.size()) {
                PriorityQueue<Integer> pq0 = new PriorityQueue<Integer>();
                PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
                for(int i = 0 ; i < people.size() ; i++) {
                    if((1<<i & cur) != 0) {
                        pq1.add(getWeight(people.get(i), exits.get(1)));
                    } else {
                        pq0.add(getWeight(people.get(i), exits.get(0)));
                    }
                }
                
                int sum0 = 0;
                if(!pq0.isEmpty()) {
                	//계단 입구 큐
                    PriorityQueue<Integer> stairQueue = new PriorityQueue<>();
                    while(!pq0.isEmpty()) {
                        stairQueue.add(pq0.poll()+1);
                    }
                    //계단 출구 큐
                    PriorityQueue<Integer> onStair = new PriorityQueue<>();
                    
                    while(!stairQueue.isEmpty() || !onStair.isEmpty()) {
                        while(!onStair.isEmpty() && onStair.peek() <= sum0) {
                            onStair.poll();
                        }
                        
                        while(onStair.size() < 3 && !stairQueue.isEmpty()) {
                            int arrivalTime = stairQueue.poll();
                            if(arrivalTime > sum0) {
                                sum0 = arrivalTime;
                            }
                            onStair.add(sum0 + exits.get(0).weight);
                        }
                        
                        if(!onStair.isEmpty()) {
                            sum0 = onStair.poll();
                        } else if(!stairQueue.isEmpty()) {
                            sum0 = Math.max(sum0, stairQueue.peek());
                        }
                    }
                }
                
                int sum1 = 0;
                if(!pq1.isEmpty()) {
                    PriorityQueue<Integer> stairQueue = new PriorityQueue<>();
                    while(!pq1.isEmpty()) {
                        stairQueue.add(pq1.poll()+1);
                    }
                    PriorityQueue<Integer> onStair = new PriorityQueue<>();
                    
                    while(!stairQueue.isEmpty() || !onStair.isEmpty()) {
                        while(!onStair.isEmpty() && onStair.peek() <= sum1) {
                            onStair.poll();
                        }
                        
                        while(onStair.size() < 3 && !stairQueue.isEmpty()) {
                            int arrivalTime = stairQueue.poll();
                            if(arrivalTime > sum1) {
                                sum1 = arrivalTime;
                            }
                            onStair.add(sum1 + exits.get(1).weight);
                        }
                        
                        if(!onStair.isEmpty()) {
                            sum1 = onStair.poll();
                        } else if(!stairQueue.isEmpty()) {
                            sum1 = Math.max(sum1, stairQueue.peek());
                        }
                    }
                }
                
                answer = Math.min(answer, Math.max(sum0, sum1));
                cur++;
            }
            
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        
        System.out.println(sb);
    }
    public static int getWeight(Point a, Point b) {
        return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
    }
}