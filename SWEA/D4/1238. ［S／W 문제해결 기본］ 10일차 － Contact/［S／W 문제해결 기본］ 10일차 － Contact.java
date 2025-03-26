import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        for(int test = 1 ; test <= 10 ; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
             
            int[] v = new int[101];
            Set<Integer>[] edge = new HashSet[101];
            for(int i = 1 ; i < 101 ; i++) {
                edge[i] = new HashSet<Integer>();
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i+=2) {
                edge[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
            }
             
            Queue<Integer> q = new LinkedList<Integer>();
            q.add(s);
            while(!q.isEmpty()) {
                int cv = q.poll();
                for(int nv : edge[cv]) {
                    if(v[nv] == 0) {
                        v[nv] = v[cv]+1;
                        q.add(nv);
                    }
                }
            }
             
            int index = 0;
            int max = 0;
            for(int i = 0 ; i < 101 ; i++) {
                if(max <= v[i]) {
                    index = i;
                    max = v[i];
                }
            }
            sb.append("#").append(test).append(" ").append(index).append("\n");
        }
         
        System.out.println(sb);
    }
}