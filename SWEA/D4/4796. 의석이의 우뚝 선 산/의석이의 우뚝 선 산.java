import java.util.*;
 
class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        int T = sc.nextInt();
        for(int test_case = 1 ; test_case <= T ; test_case++) {
            int N = sc.nextInt();
            int[] h = new int[N];
            boolean[] isIncreasing = new boolean[N-1];
            h[0] = sc.nextInt();
            for(int i = 1 ; i < N ; i++) {
                h[i] = sc.nextInt();
                isIncreasing[i-1] = (h[i] > h[i-1]);
            }
            
            int answer = 0;
            boolean current = isIncreasing[0];
            int[] count = new int[2];
            count[current ? 0 : 1] = 1;
            for(int i = 1 ; i < N-1 ; i++) {
                if(current && current != isIncreasing[i]) {
                    //true -> false
                    count[1] = 1;
                } else if(!current && current != isIncreasing[i]) {
                    //false -> true
                    if(count[0] != 0 && count[1] != 0) {
                        answer += count[0] * count[1];
                    }
                    count[0] = 1;
                    count[1] = 0;
                } else if(current && current == isIncreasing[i]) {
                    //true -> true
                    count[0]++;
                } else if(!current && current == isIncreasing[i]) {
                    //false -> false
                    count[1]++;
                }
                current = isIncreasing[i];
            }
            
            if(count[0] != 0 && count[1] != 0) {
                answer += count[0] * count[1];
            }
            
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        sc.close();
    }
}