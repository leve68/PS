import java.io.*;
import java.util.*;

class Solution {
    class Point {
        int row;
        int col;
        
        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    static int[][] matrix;
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();

        matrix = new int[maps.length][maps[0].toCharArray().length];
        for(int i = 0 ; i < maps.length ; i++){
            char[] tmp = maps[i].toCharArray();
            for(int j = 0 ; j < maps[0].toCharArray().length ; j++){
                matrix[i][j] = tmp[j] == 'X' ? 0 : Character.getNumericValue(tmp[j]);
            }
        }
        
        for(int i = 0 ; i < maps.length ; i++){
            for(int j = 0 ; j < maps[0].toCharArray().length ; j++){
                if(matrix[i][j] != 0){
                    answer.add(bfs(new Point(i, j)));
                }
            }
        }
        
        if(answer.size() == 0) answer.add(-1);
        int[] tmp = new int[answer.size()];
        for(int i = 0 ; i < tmp.length ; i++){
            tmp[i] = answer.get(i);
        }
        Arrays.sort(tmp);
        return tmp;
    }
    
    int[][] d = {{1, 0},{-1, 0},{0, 1},{0, -1}};
    int bfs(Point start) {
        int sum = matrix[start.row][start.col];
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        matrix[start.row][start.col] = 0;
        while(!q.isEmpty()){
            Point c = q.poll();
            int cr = c.row;
            int cc = c.col;
            
            for(int i = 0 ; i < d.length ; i++){
                int nr = cr + d[i][0];
                int nc = cc + d[i][1];
                if(nr>=0 && nr<matrix.length && nc>=0 && nc<matrix[0].length){
                    if(matrix[nr][nc] != 0){
                        sum += matrix[nr][nc];
                        matrix[nr][nc] = 0;
                        q.add(new Point(nr, nc));
                    }
                }
            }
        }
        
        return sum;
    }
}