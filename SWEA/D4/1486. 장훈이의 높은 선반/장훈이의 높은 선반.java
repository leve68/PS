import java.util.Scanner;
 
class Solution
{
    //최대값
    public static int answer = 10000 * 20;
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
         
        for(int test_case = 1; test_case <= T; test_case++)
        {
            //B이상의 탑의 높이들 중 가장 낮은 높이
            //만들 수 있는 경우의 수 중 B를 넘는 최소값
            //O(2^n)
            //n<20 이므로 처리 가능
            answer = 10000 * 20; //전역변수 초기화
            int n = sc.nextInt();
            int b = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0 ; i<n ; i++) {
                arr[i] = sc.nextInt();
            }
             
            //n개 중에서 i개 선택
            for(int i = 1 ; i<=n ; i++) {
                boolean[] visited = new boolean[n];
                combination(i, arr, visited, b, 0);
            }
             
            answer = answer - b;
            System.out.println("#" + test_case + " " + answer);
             
        }
    }
         
    public static void combination(int count, int[] array, boolean[] visited, int min, int start) {
        if (count == 0) {
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                if (visited[i]) {
                    sum += array[i];
                }
            }
             
            if(sum >= min && sum < answer) answer = sum;
            return;
        }
 
        for (int i = start; i < array.length; i++) {
            visited[i] = true;
            combination(count - 1, array, visited, min, i+1);
            visited[i] = false;
        }
    }
}