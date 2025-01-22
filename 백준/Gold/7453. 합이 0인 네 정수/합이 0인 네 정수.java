import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		long answer = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] arr = new long[4][n];
        for(int i = 0 ; i<n ; i++) {
        	for(int j = 0 ; j<4 ; j++) {
        		arr[j][i] = sc.nextLong();
        	}
        }
        
        //각 배열 : arr[0], arr[1], arr[2], arr[3]
        long[] arr1 = new long[n*n];
        long[] arr2 = new long[n*n];
        
        int count = 0;
        for(int i = 0 ; i<n ; i++) {
        	for(int j = 0 ; j<n ; j++) {
        		arr1[count] = arr[0][i] + arr[1][j];
        		arr2[count] = arr[2][i] + arr[3][j];
        		count++;
        	}
        }
        //arr1 + arr2 가 0이 되는 모든 경우의 수 구하기
        //arr1[k] 에 대해서 0이되는 수가 여러개일 수 있음
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for(int i = 0 ; i<n*n ; i++) {
        	long current = arr1[i];
        	int left = 0;
        	int right = n*n - 1;
        	
            // Lower Bound 찾기
            while (left < right) {
                int mid = (left + right) / 2;
                if (current + arr2[mid] >= 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int lowerBound = left;

            // Upper Bound 찾기
            left = 0;
            right = n * n;
            while (left < right) {
                int mid = (left + right) / 2;
                if (current + arr2[mid] > 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int upperBound = left - 1;
            
        	if(lowerBound <= upperBound && current+arr2[lowerBound] == 0) answer += (upperBound - lowerBound + 1);
        }
        
        
        System.out.println(answer);
    }
}