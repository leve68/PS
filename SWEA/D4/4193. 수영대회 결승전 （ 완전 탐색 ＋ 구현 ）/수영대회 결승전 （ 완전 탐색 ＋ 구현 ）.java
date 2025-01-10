import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int[][] matrix = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    matrix[i][j] = sc.nextInt();
                }
            }
            int[] start = new int[2];
            int[] end = new int[2];
            for(int i = 0; i < 2; i++){
                start[i] = sc.nextInt();
            }
            for(int i = 0; i < 2; i++){
                end[i] = sc.nextInt();
            }

            int[][] dist = new int[n][n];
            boolean[][] visited = new boolean[n][n];

            //0: 길, 1: 장애물, 2: 주기가 2초인 소용돌이
            //소용돌이가 없으면 bfs => 기준값
            //가려는 방향에 소용돌이가 있다면 대기후의 값으로 변경
            Queue<int[]> q = new LinkedList<int[]>();
            q.add(new int[]{start[0], start[1]});
            visited[start[0]][start[1]] = true;
            while(!q.isEmpty()){
                int[] current = q.poll();
                for(int i = 0; i < dx.length; i++){
                    int nextX = current[0] + dx[i];
                    int nextY = current[1] + dy[i];
                    if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < n){
                        if((matrix[nextX][nextY] == 0 && !visited[nextX][nextY])
                                || (matrix[nextX][nextY] == 0 && dist[nextX][nextY] > dist[current[0]][current[1]] + 1)){
                            dist[nextX][nextY] = dist[current[0]][current[1]] + 1;
                            visited[nextX][nextY] = true;
                            q.add(new int[]{nextX, nextY});
                        } else if((matrix[nextX][nextY] == 2 && !visited[nextX][nextY])
                                || (matrix[nextX][nextY] == 2 && dist[nextX][nextY] > dist[current[0]][current[1]] + 1)){
                            dist[nextX][nextY] = dist[current[0]][current[1]] +
                                    (dist[current[0]][current[1]] % 3 == 0 ? 3 :
                                            (dist[current[0]][current[1]] % 3 == 1 ? 2 : 1));
                            visited[nextX][nextY] = true;
                            q.add(new int[]{nextX, nextY});
                        }
                    }
                }
            }
            if (dist[end[0]][end[1]] == 0){ dist[end[0]][end[1]] = - 1; }
            System.out.println("#" + test_case + " " + dist[end[0]][end[1]]);
        }
    }
}