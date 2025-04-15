import java.io.*;

import java.util.*;

public class Main {
	static class Point {
		int row;
		int col;
		int number;

		public Point(int row, int col, int number) {
			this.row = row;
			this.col = col;
			this.number = number;
		}

	}

	static class Edge {
		int start;
		int end;
		int length;

		public Edge(int start, int end, int length) {
			this.start = start;
			this.end = end;
			this.length = length;
		}

	}

	public static int N;
	public static int M;
	public static int[][] matrix;
	public static int[][] edgeLength;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		matrix = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int number = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 1) {
					matrix[i][j] = number;
					setIsland(i, j, number);
					number++;
				}
			}
		}

		edgeLength = new int[number][number];
		setEdgeLength();

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> a.length - b.length);
		boolean[] visited = new boolean[number];
		visited[2] = true;
		for(int i = 2 ; i < number ; i++) {
			if(edgeLength[2][i] > 1) {
				pq.add(new Edge(2, i, edgeLength[2][i]));
			}
		}
		
		int sum = 0;
		while(!pq.isEmpty()) {
			Edge c = pq.poll();
			if(visited[c.end]) continue;
			
			sum += c.length;
			visited[c.end] = true;
			for(int i = 2 ; i < number ; i++) {
				if(edgeLength[c.end][i] > 1) {
					pq.add(new Edge(c.end, i, edgeLength[c.end][i]));
				}
			}
		}
		
		for(int i = 2 ; i < number ; i++) {
			if(!visited[i]) {
				sum = -1;
				break;
			}
		}

		System.out.println(sum);
	}

	static void setEdgeLength() {
		// 가로줄
		for (int i = 0; i < N; i++) {
			int pointer = 0;
			int c = 0;
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] != 0) {
					c = matrix[i][j];
					pointer = j;
					break;
				}
			}

			if (c != 0) {
				int length = 0;
				for (; pointer < M; pointer++) {
					if (c == matrix[i][pointer]) {
						length = 0;
						continue;
					}

					if (matrix[i][pointer] == 0) {
						length++;
					} else {
						int tmp = matrix[i][pointer];
						if(length != 1) {
							if(edgeLength[c][tmp] == 0) {
								edgeLength[c][tmp] = length;
								edgeLength[tmp][c] = length;
							} else {
								edgeLength[c][tmp] = Math.min(length, edgeLength[c][tmp]);
								edgeLength[tmp][c] = Math.min(length, edgeLength[tmp][c]);
							}
						}
						length = 0;
						c = tmp;
					}
				}
			}
		}
		// 세로줄
		for (int i = 0; i < M; i++) {
			int pointer = 0;
			int c = 0;
			for (int j = 0; j < N; j++) {
				if (matrix[j][i] != 0) {
					c = matrix[j][i];
					pointer = j;
					break;
				}
			}

			if (c != 0) {
				int length = 0;
				for (; pointer < N; pointer++) {
					if (c == matrix[pointer][i]) {
						length = 0;
						continue;
					}

					if (matrix[pointer][i] == 0) {
						length++;
					} else {
						int tmp = matrix[pointer][i];
						if(length != 1) {
							if(edgeLength[c][tmp] == 0) {
								edgeLength[c][tmp] = length;
								edgeLength[tmp][c] = length;
							} else {
								edgeLength[c][tmp] = Math.min(length, edgeLength[c][tmp]);
								edgeLength[tmp][c] = Math.min(length, edgeLength[tmp][c]);
							}
						}
						length = 0;
						c = tmp;
					}
				}
			}
		}
	}

	static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static void setIsland(int row, int col, int number) {
		Queue<Point> q = new ArrayDeque<Point>();
		matrix[row][col] = number;
		q.add(new Point(row, col, number));
		while (!q.isEmpty()) {
			Point c = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = c.row + d[i][0];
				int nc = c.col + d[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (matrix[nr][nc] == 1) {
						matrix[nr][nc] = c.number;
						q.add(new Point(nr, nc, c.number));
					}
				}
			}
		}
	}
}
