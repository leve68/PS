import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < n - 3; i++) {
            if(a[i+1] > a[i+2]){
                while(a[i+1] > a[i+2] && a[i] > 0){
                    sum += 5;
                    a[i]--;
                    a[i+1]--;
                }

                while(a[i] > 0 && a[i+1] > 0 && a[i+2] > 0){
                    sum += 7;
                    a[i]--;
                    a[i+1]--;
                    a[i+2]--;
                }

                while(a[i] > 0){
                    sum += 3;
                    a[i]--;
                }
            } else {
                while(a[i] > 0 && a[i+1] > 0 && a[i+2] > 0){
                    sum += 7;
                    a[i]--;
                    a[i+1]--;
                    a[i+2]--;
                }

                while(a[i] > 0){
                    sum += 3;
                    a[i]--;
                }
            }
        }

        for (int i = n-3; i < n; i++) {
            if(i <= n-3){
                while(a[i] > 0 && a[i+1] > 0 && a[i+2] > 0){
                    sum += 7;
                    a[i]--;
                    a[i+1]--;
                    a[i+2]--;
                }
            }

            if(i <= n-2){
                while(a[i] > 0 && a[i+1] > 0){
                    sum += 5;
                    a[i]--;
                    a[i+1]--;
                }
            }

            if(i <= n-1){
                while(a[i] > 0){
                    sum += 3;
                    a[i]--;
                }
            }
        }

        System.out.println(sum);

        //10^4
        //i a에서 1개 구입하는 경우 3원
        //i, i+1 a에서 2개 구입하는 경우 5원
        //i, i+1, i+2 a에서 3개 구입하는 경우 7원
        //3개씩 최대한 많이 묶어야햠
        //앞에서부터 묶는게 최선이 아님

        //1 3 1 3 (x)
        //0 2 0 3

        //1 3 1 3 (o)
        //0 2 1 3
        //0 1 0 2

        //1 3 2 3 (x)
        //0 2 1 3
        //0 1 0 2

        //1 3 2 3 (o)
        //0 2 2 3
        //0 0 0 1

        //1 3 3 3 (o)
        //0 2 2 3
        //0 0 0 1

        //1 3 3 3 (x)
        //0 2 3 3
        //0 0 1 1

        //1 3 4 3 5
        //0 2 4 3 5
        //0 0 2 1 5
        //0 0 1 0 4

        //1 3 4 2 2  3*1
        //0 2 3 2 2  3*2
        //0 0 1 0 2
        //21+9

        //1 3 4 2 2  3*1
        //0 2 3 2 2  2*2
        //0 0 1 2 2  3*1
        //0 0 0 1 1  2*1
        //24+5

        //1 4 3 2
        //0 3 3 2
        //0 1 1 0

        //i+1이 i+2보다 크면 i가 0이 될 때까지 2로 묶기
        //2 5 2 5
        //0 3 2 5
        //0 1 0 3

        //2 6 5 4 (x)
        //0 4 5 4
        //0 0 1 4
        //남은게 마지막 3개일 경우 전부 3으로 묶기

        //2 6 5 4
        //0 4 5 4
        //0 0 1 0

        //1 5 5 4
        //0 4 4 4
        //0 0 0 0
        //i+1이 i+2와 같아지면 2개씩 묶기를 중지해야 함
    }
}