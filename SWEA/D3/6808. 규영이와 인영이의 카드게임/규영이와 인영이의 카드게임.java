import java.io.*;
import java.util.*;
public class Solution {
    public static int[] cards;
    public static int mySum = 0;
    public static int newSum = 0;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int T = Integer.parseInt(br.readLine());
         
        for(int test = 1 ; test <= T ; test++) {
            mySum = 0;
            newSum = 0;
             
            cards = Arrays.stream(br.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
            boolean[] used = new boolean[19];
             
            for(int card : cards) {
                used[card] = true;
            }
             
            dfs(0, used, new int[9]);
            System.out.println("#" + test + " " + mySum + " " + newSum);
        }
    }
     
    public static void dfs(int count, boolean[] used, int[] current) {
        if(count == 9) {
            int myScore = 0;
            int newScore = 0;
            for(int i = 0 ; i < count ; i++) {
                if(cards[i] > current[i]) myScore += (cards[i] + current[i]);
                else newScore += (cards[i] + current[i]);
            }
             
            if(myScore > newScore) mySum++;
            else if(newScore > myScore) newSum++;
            return;
        }
         
        for(int i = 1; i <= 18; i++) {
            if(!used[i]) {
                used[i] = true;
                current[count] = i;
                dfs(count+1, used, current);
                used[i] = false;
            }
        }
    }
}