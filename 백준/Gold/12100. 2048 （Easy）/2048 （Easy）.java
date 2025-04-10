import java.io.*;

import java.util.*;
public class Main {
    public static int N;
    public static int[][] matrix;
    public static int max = 0;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
         
        matrix = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0);
        
        System.out.println(max);
    }
    
    static void dfs(int count) {
    	if(count == 5) {
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    max = Math.max(matrix[i][j], max);
                }
            }
            return;
    	}
    	
    	int[][] tmp = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                tmp[i][j] = matrix[i][j];
            }
        }
        
        for(int d = 0 ; d < 4 ; d++) {
            switch(d) {
            case 0:
                move();
                break;
            case 1:
                rotate(3);
                move();
                rotate(1);
                break;
            case 2:
                rotate(2);
                move();
                rotate(2);
                break;
            case 3:
                rotate(1);
                move();
                rotate(3);
                break;
            }
            
            dfs(count+1);
            
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                	matrix[i][j] = tmp[i][j];
                }
            }
        }
    }
    
    static void rotate(int num) {
        int[][] copy = new int[N][N];
         
        for(int count = 0; count < num; count++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    copy[i][j] = matrix[i][j];
                }
            }
             
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    matrix[j][N-1-i] = copy[i][j];
                }
            }
        }
    }
     
    static void move() {
        for(int i = 0 ; i < N ; i++) {
            Queue<Integer> q = new LinkedList<Integer>();
            for(int j = 0 ; j < N ; j++) {
                if(matrix[i][j] != 0) {
                    q.add(matrix[i][j]);
                    matrix[i][j] = 0;
                }
            }
            int index = 0;
            if(!q.isEmpty()) {
                matrix[i][0] = q.poll();
                while(!q.isEmpty()) {
                    int current = q.poll();
                    if(matrix[i][index] == current) matrix[i][index++] *= 2;
                    else if(matrix[i][index] == 0) matrix[i][index] = current;
                    else matrix[i][++index] = current;
                }
            }
        }
    }
}