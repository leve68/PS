import java.io.*;
import java.util.*;
 
public class Solution {
    static class Point {
        int row;
        int col;
        int life;
        int time;
        int left;
        int realLife;
 
        public Point(int row, int col, int life, int time) {
            this.row = row;
            this.col = col;
            this.life = life;
            this.time = time;
            this.left = this.life - this.time;
            this.realLife = this.life * 2 - this.time;
        }
 
        public void update() {
            this.time++;
            this.left = this.life - this.time;
            this.realLife = this.life * 2 - this.time;
        }
 
        public String toString() {
            return this.row + " " + this.col;
        }
    }
 
    static int N;
    static int M;
    static int K;
    static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            Set<String> set = new HashSet<String>();
            PriorityQueue<Point> inactive = new PriorityQueue<Point>((a, b) -> a.left - b.left);
            Queue<Point> active = new ArrayDeque<Point>();
            PriorityQueue<Point> beforeDie = new PriorityQueue<Point>((a, b) -> a.realLife - b.realLife);
            Map<String, Point> updateMap = new HashMap<String, Point>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int cur = Integer.parseInt(st.nextToken());
                    if(cur != 0) {
                        Point p = new Point(i, j, cur, 0);
                        inactive.add(p);
                        set.add(p.toString());                      
                    }
                }
            }
 
            for (int t = 1; t <= K; t++) {
                //활성상태에서 죽기를 기다리는 세포
                for(Point p : beforeDie) {
                    p.update();
                }
                 
                if(!beforeDie.isEmpty()) {
                    while(beforeDie.peek().realLife == 0) {
                        beforeDie.poll();
                        if(beforeDie.isEmpty()) break;
                    }
                }
                 
                //비활성 상태에서 활성 상태로 넘어가는 세포
                for(Point p : inactive) {
                    p.update();
                }
                 
                if(!inactive.isEmpty()) {
                    while (inactive.peek().left == 0) {
                        active.add(inactive.poll());
                        if(inactive.isEmpty()) break;
                    }
                }
                 
                //확장하는 활성세포
                if(!active.isEmpty()) {
                    updateMap = new HashMap<String, Point>();
                    while(active.peek().left == -1) {
                        Point p = active.poll();
                        if(p.realLife > 0) beforeDie.add(p);
                        for (int i = 0; i < 4; i++) {
                            int nr = p.row + d[i][0];
                            int nc = p.col + d[i][1];
                            String key = nr + " " + nc;
                            if (!set.contains(key)) {
                                Point np = new Point(nr, nc, p.life, 0);
                                if(updateMap.get(key) == null) {
                                    updateMap.put(key, np);
                                } else {
                                    if(updateMap.get(key).life < np.life) {
                                        updateMap.put(key, np);
                                    }
                                }
                            }
                        }
                        if(active.isEmpty()) break;
                    }
                     
                    for(String key : updateMap.keySet()) {
                        inactive.add(updateMap.get(key));
                        set.add(key);
                    }
                }
                 
                for(Point p : active) {
                    p.update();
                }
            }
 
            int answer = active.size() + inactive.size() + beforeDie.size();
            sb.append("#").append(test).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}