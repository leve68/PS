import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test = 1 ; test <= T ; test++) {
			int N = Integer.parseInt(br.readLine());
			int[][] matrix = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = -1;
			int sum;
			boolean[] visited;
			for(int i = 0 ; i < N ; i++) {
			    for(int j = 0 ; j < N ; j++) {
			        //왼쪽 아래 l칸, 오른쪽 아래 m칸
			        for(int l = 1 ; l < N ; l++) {
			            for(int m = 1 ; m < N ; m++) {
			                int si = i; //start i
			                int sj = j;
			                int ldi = si + l; //left down i
			                int ldj = sj - l;
			                int rdi = ldi + m;
			                int rdj = ldj + m;
			                int rui = si + m;
			                int ruj = sj + m;
			                
			                if(rdi < N && ruj < N && ldj >= 0) {
			                    //초기화
			                    boolean canUse = true;
			                    sum = 0;
			                    visited = new boolean[101];
			                    
			                    //s -> ld
			                    for(int di = si, dj = sj; di < ldi && dj > ldj; di++, dj--) {
			                        if(!visited[matrix[di][dj]]) {
			                            sum += 1;
			                            visited[matrix[di][dj]] = true;
			                        } else {
			                            canUse = false;
			                            break;
			                        }
			                    }
			                    
			                    if(!canUse) continue;
			                    
			                    //ld -> rd
			                    for(int di = ldi, dj = ldj; di < rdi && dj < rdj; di++, dj++) {
			                        if(!visited[matrix[di][dj]]) {
			                            sum += 1;
			                            visited[matrix[di][dj]] = true;
			                        } else {
			                            canUse = false;
			                            break;
			                        }
			                    }
			                    
			                    if(!canUse) continue;
			                    
			                    //rd -> ru
			                    for(int di = rdi, dj = rdj; di > rui && dj < ruj; di--, dj++) {
			                        if(!visited[matrix[di][dj]]) {
			                            sum += 1;
			                            visited[matrix[di][dj]] = true;
			                        } else {
			                            canUse = false;
			                            break;
			                        }
			                    }
			                    
			                    if(!canUse) continue;
			                    
			                    //ru -> s
			                    for(int di = rui, dj = ruj; di > si && dj > sj; di--, dj--) {
			                        if(!visited[matrix[di][dj]]) {
			                            sum += 1;
			                            visited[matrix[di][dj]] = true;
			                        } else {
			                            canUse = false;
			                            break;
			                        }
			                    }
			                    
			                    if(canUse) {
			                        max = Math.max(max, sum);
			                    }
			                }
			            }
			        }
			    }
			}
			sb.append("#").append(test).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
}