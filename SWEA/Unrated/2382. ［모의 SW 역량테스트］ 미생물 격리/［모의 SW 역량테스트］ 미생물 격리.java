import java.io.*;
import java.util.*;
 
public class Solution {
    static class Microbe {
        int size, dir;
         
        Microbe(int size, int dir) {
            this.size = size;
            this.dir = dir;
        }
    }
     
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};
    static int[] oppositeDir = {0, 2, 1, 4, 3};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
             
            Map<Integer, Microbe> microbes = new HashMap<>(); //id 로 미생물의 정보 획득
            Map<Integer, Integer> positions = new HashMap<>(); //id 로 미생물의 위치 획득
             
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int size = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                 
                int id = i;
                microbes.put(id, new Microbe(size, dir));
                positions.put(id, row * N + col); //N으로 나눈 몫이 row, 나머지가 col
            }
             
            for (int time = 0; time < M; time++) {
                Map<Integer, List<Integer>> nextPositions = new HashMap<>(); //이동할 위치에 도달하는 미생물 id list 저장
                 
                // 모든 살아있는 미생물 이동
                // 이터레이터를 사용하여 ConcurrentModificationException 해결
                Iterator<Map.Entry<Integer, Integer>> iter = positions.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iter.next();
                    int id = entry.getKey();
                    int pos = entry.getValue();
                    int r = pos / N;
                    int c = pos % N;
                     
                    Microbe microbe = microbes.get(id);
                    int dir = microbe.dir;
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
                    int newPos = nr * N + nc;
                     
                    // 가장자리인 경우
                    if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
                        microbe.size /= 2;
                        microbe.dir = oppositeDir[dir];
                         
                        if (microbe.size == 0) {
                            iter.remove();
                            microbes.remove(id);
                            continue;
                        }
                    }
                     
                    if (!nextPositions.containsKey(newPos)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(id);
                        nextPositions.put(newPos, list);
                    } else {
                        nextPositions.get(newPos).add(id);
                    }
                    positions.put(id, newPos);
                }
                 
                // 같은 위치에 있는 미생물 합치기
                for (Map.Entry<Integer, List<Integer>> entry : nextPositions.entrySet()) {
                    List<Integer> microbeIds = entry.getValue();
                     
                    if (microbeIds.size() > 1) {
                        // 같은 위치에서 가장 큰 미생물 찾기
                        int maxSize = 0;
                        int maxSizeId = -1;
                        int totalSize = 0;
                         
                        for (int id : microbeIds) {
                            Microbe microbe = microbes.get(id);
                            totalSize += microbe.size;
                             
                            if (microbe.size > maxSize) {
                                maxSize = microbe.size;
                                maxSizeId = id;
                            }
                        }
                         
                        // 나머지 미생물 제거
                        for (int id : microbeIds) {
                            if (id != maxSizeId) {
                                microbes.remove(id);
                                positions.remove(id);
                            }
                        }
                        microbes.get(maxSizeId).size = totalSize;
                    }
                }
            }
             
            // 남아있는 미생물 수 계산
            int totalMicrobes = 0;
            for (Microbe microbe : microbes.values()) {
                totalMicrobes += microbe.size;
            }
            sb.append("#").append(test).append(" ").append(totalMicrobes).append("\n");
        }
        System.out.println(sb);
    }
}