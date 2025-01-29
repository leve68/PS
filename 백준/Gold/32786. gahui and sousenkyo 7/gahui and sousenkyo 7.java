import java.io.*;
import java.util.*;

class Main {
   public static void main(String args[]) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

       int n = Integer.parseInt(st.nextToken());
       int c = Integer.parseInt(st.nextToken());
       int k = Integer.parseInt(st.nextToken());

       int[][] answer = new int[c][n];
       
       if(k == 0) {
           int id = 1_000_000;
           for(int i = 0; i < c; i++) {
               StringBuilder sb = new StringBuilder();
               for(int j = 0; j < n; j++) {
                   sb.append(id--).append(" ");
                   if(id < 1) id = 1_000_000;
               }
               bw.write(sb.toString());
               bw.newLine();
           }
           bw.flush();
           bw.close();
           br.close();
           return;
       }
       
       // k > 0인 경우 처리
       st = new StringTokenizer(br.readLine());
       int[] rankDivide = new int[k];
       for(int i = 0; i < k; i++) {
           rankDivide[i] = Integer.parseInt(st.nextToken());
       }
       Arrays.sort(rankDivide);

       int prevR = 0;
       for(int i = 0; i < k; i++) {
           int currentSize = rankDivide[i] - prevR;
           if(currentSize <= 0) continue;
           
           int[] numbers = new int[currentSize];
           for(int j = 0; j < currentSize; j++) {
               numbers[j] = prevR + j + 1;
           }

           for(int election = 0; election < c; election++) {
               for(int j = 0; j < currentSize; j++) {
                   int shiftedIndex = (j + election) % currentSize;
                   answer[election][prevR + j] = numbers[shiftedIndex];
               }
           }
           prevR = rankDivide[i];
       }

       int max_r = rankDivide[k - 1];
       int id = 1_000_000;

       for(int i = 0; i < c; i++) {
           for(int j = max_r; j < n; j++) {
               answer[i][j] = id--;
               if(id < 1) id = 1_000_000;
           }
       }

       for(int i = 0; i < c; i++) {
           StringBuilder sb = new StringBuilder();
           for(int j = 0; j < n; j++) {
               sb.append(answer[i][j]).append(" ");
           }
           bw.write(sb.toString());
           bw.newLine();
       }
       
       bw.flush();
       bw.close();
       br.close();
   }
}