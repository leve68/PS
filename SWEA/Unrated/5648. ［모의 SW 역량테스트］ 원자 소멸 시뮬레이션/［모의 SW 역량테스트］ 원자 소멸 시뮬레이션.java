import java.io.*;
import java.util.*;

public class Solution {
    static class Atom{
        int y;
        int x;
        int dir;
        int energy;
        
        public Atom(int y, int x, int dir, int energy) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.energy = energy;
        }
    }
    static class Collapse {
        int a1;
        int a2;
        double time;
        
        public Collapse(int a1, int a2, double time) {
            super();
            this.a1 = a1;
            this.a2 = a2;
            this.time = time;
        }
    }
    static int N;
    static int[][] d = {{1, 0},{-1, 0},{0, -1},{0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int test = 1 ; test <= T ; test++) {
            N = Integer.parseInt(br.readLine());

            Atom[] atoms = new Atom[N];
            for(int i = 0 ; i < N ; i ++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atoms[i] = new Atom(y, x, dir, k);
            }
            
            // 충돌 정보
            List<Collapse> colList = new ArrayList<>();
            for(int i = 0; i < N-1; i++) {
                for(int j = i+1; j < N; j++) {
                    double age = collapse(atoms[i], atoms[j]);
                    if(age > 0) {
                        colList.add(new Collapse(i, j, age));
                    }
                }
            }

            Collections.sort(colList, (a, b) -> Double.compare(a.time, b.time));
            
            // 소멸 여부
            boolean[] isVanished = new boolean[N];
            int energy = 0;

            int i = 0;
            while(i < colList.size()) {
                double currentTime = colList.get(i).time;

                Set<Integer> atomsInCollision = new HashSet<>();
                int j = i;
                
                // 현재 시간과 같은 충돌 이벤트들 모두 처리
                while(j < colList.size() && Math.abs(colList.get(j).time - currentTime) < 0.000_001) {
                    Collapse col = colList.get(j);
                    
                    if(!isVanished[col.a1] && !isVanished[col.a2]) {
                        atomsInCollision.add(col.a1);
                        atomsInCollision.add(col.a2);
                    }
                    j++;
                }
                
                for(int atom : atomsInCollision) {
                    isVanished[atom] = true;
                    energy += atoms[atom].energy;
                }
                
                i = j;
            }
            
            sb.append("#").append(test).append(" ").append(energy).append("\n");
        }
        System.out.println(sb);
    }
    
    static double collapse(Atom a1, Atom a2) {
        // 정면에서 마주보는 경우
        if ((a1.dir == 0 && a2.dir == 1) || (a1.dir == 1 && a2.dir == 0)) {
            if (a1.x == a2.x) {
                boolean movingTowardsEachOther = 
                    (a1.dir == 0 && a1.y < a2.y) || (a1.dir == 1 && a1.y > a2.y);
                
                if (movingTowardsEachOther) {
                    return ((double) Math.abs(a1.y - a2.y)) / 2;
                }
            }
        }

        if ((a1.dir == 2 && a2.dir == 3) || (a1.dir == 3 && a2.dir == 2)) {
            if (a1.y == a2.y) {
                boolean movingTowardsEachOther = 
                    (a1.dir == 2 && a1.x > a2.x) || (a1.dir == 3 && a1.x < a2.x);
                
                if (movingTowardsEachOther) {
                    return ((double) Math.abs(a1.x - a2.x)) / 2;
                }
            }
        }
        
        //옆각으로 들어오는 경우
        if(a1.dir != a2.dir) {
            int dy1 = d[a1.dir][0];
            int dx1 = d[a1.dir][1];
            int dy2 = d[a2.dir][0];
            int dx2 = d[a2.dir][1];
            
            double t1 = 0;
            if(dy1 - dy2 != 0) {
                t1 = (a2.y - a1.y) / (double)(dy1 - dy2);
            }
            
            double t2 = 0;
            if(dx1 - dx2 != 0) {
                t2 = (a2.x - a1.x) / (double)(dx1 - dx2);
            }
            
            if(t1 > 0 && t2 > 0 && Math.abs(t1 - t2) < 0.000_001) {
                return t1;
            }
        }
        
        return 0;
    }
}