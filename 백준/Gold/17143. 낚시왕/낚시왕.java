import java.io.*;
import java.util.*;

public class Main {
    public static int[] dr = {0, -1, 1, 0, 0};
    public static int[] dc = {0, 0, 0, 1, -1};
    public static class Shark {
        int r; //row
        int c; //col
        int s; //speed
        int d; //direction -> 상하우좌 1 2 3 4
        int z; //size

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        private void move(int row, int col) {
            int speed = s;

            if (d == 1 || d == 2) {
                // 왕복 거리로 나눈 나머지만 이동
                speed = speed % ((row - 1) * 2);

                for (int i = 0; i < speed; i++) {
                    int nr = r + dr[d];

                    if (nr < 0) {
                        r = 1;
                        d = 2; // 상 -> 하
                    } else if (nr > row - 1) {
                        r = row - 2;
                        d = 1; // 하 -> 상
                    } else {
                        r = nr;
                    }
                }
            }
            else if (d == 3 || d == 4) {
                // 왕복 거리로 나눈 나머지만 이동
                speed = speed % ((col - 1) * 2);

                for (int i = 0; i < speed; i++) {
                    int nc = c + dc[d];

                    if (nc < 0) {
                        c = 1;
                        d = 3; // 좌 -> 우
                    } else if (nc > col - 1) {
                        c = col - 2;
                        d = 4; // 우 -> 좌
                    } else {
                        c = nc;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, Shark> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            map.put(r * 1000 + c, new Shark(r, c, s, d, z));
        }

        int answer = 0;
        for (int t = 0; t < col; t++) {
            ArrayList<Shark> tmp = new ArrayList<>();
            ArrayList<Shark> sharks = new ArrayList<>();
            for (int point : map.keySet()) {
                Shark shark = map.get(point);

                if (shark.c == t) {
                    tmp.add(shark);
                } else {
                    shark.move(row, col);
                    sharks.add(shark);
                }
            }

            if (tmp.size() > 0) {
                tmp.sort((o1, o2) -> o1.r - o2.r);
                answer += tmp.get(0).z;
                for (int i = 1; i < tmp.size(); i++) {
                    Shark shark = tmp.get(i);
                    shark.move(row, col);
                    sharks.add(shark);
                }
            }

            map = new HashMap<>();
            for (Shark shark : sharks) {
                if (map.containsKey(shark.r * 1000 + shark.c)) {
                    Shark shark2 = map.get(shark.r * 1000 + shark.c);
                    shark2 = shark2.z > shark.z ? shark2 : shark;
                    map.put(shark.r * 1000 + shark.c, shark2);
                } else {
                    map.put(shark.r * 1000 + shark.c, shark);
                }
            }
        }

        System.out.println(answer);
    }
}