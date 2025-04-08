import java.io.*;
import java.util.*;

public class Main {
    static int[][] gear = new int[4][8];
    static int[] gearSpin = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 톱니바퀴 상태 입력
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = line.charAt(j) - '0';
            }
        }
        
        // 회전 횟수 입력
        int k = Integer.parseInt(br.readLine());
        
        // 회전 명령 수행
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int spinCount = -Integer.parseInt(st.nextToken());
            spinGear(gearNum, spinCount);
        }
        
        // 점수 계산
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (gear[i][getCog(gearSpin[i])] == 1) {
                answer += Math.pow(2, i);
            }
        }
        
        System.out.println(answer);
        br.close();
    }
    
    static void spinGear(int gearNum, int spinDir) {
        int[] directions = new int[4];
        directions[gearNum] = spinDir;
        
        // 오른쪽 톱니바퀴 확인
        for (int i = gearNum; i < 3; i++) {
            if (isDiffCog(i, i + 1)) {
                directions[i + 1] = -directions[i];
            } else {
                break;
            }
        }
        
        // 왼쪽 톱니바퀴 확인
        for (int i = gearNum; i > 0; i--) {
            if (isDiffCog(i - 1, i)) {
                directions[i - 1] = -directions[i];
            } else {
                break;
            }
        }
        
        // 회전 적용
        for (int i = 0; i < 4; i++) {
            gearSpin[i] += directions[i];
            if (gearSpin[i] < -8) {
                gearSpin[i] += 8;
            }
        }
    }
    
    static int getCog(int cog) {
        return cog >= 0 ? cog % 8 : cog + 8;
    }
    
    // a가 더 왼쪽에 있는 톱니바퀴
    static boolean isDiffCog(int a, int b) {
        return gear[a][getCog(2 + gearSpin[a])] != gear[b][getCog(6 + gearSpin[b])];
    }
}