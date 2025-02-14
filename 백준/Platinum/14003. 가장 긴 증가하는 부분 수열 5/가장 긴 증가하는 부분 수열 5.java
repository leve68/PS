import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
		int[] parent = new int[N];
		int[] dpIndex = new int[N];
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[N];
        dp[0] = arr[0];
        dpIndex[0] = 0;
        parent[0] = -1;
        int maxIndex = 0;
        
        for(int i = 1 ; i < N ; i++) {
        	int current = arr[i];
        	if(dp[maxIndex] < current) {
        		maxIndex++;
        		dp[maxIndex] = current;
        		dpIndex[maxIndex] = i;
        		parent[i] = dpIndex[maxIndex - 1];
        	}
        	else {
        		int index = lowerBound(dp, maxIndex, current);
        		dp[index] = current;
        		dpIndex[index] = i;
        		if(index != 0) parent[i] = dpIndex[index - 1];
        		else parent[i] = -1;
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        int current = dpIndex[maxIndex];
        int[] answer = new int[maxIndex + 1];
        int idx = 0;
        while (current != -1) {
            answer[idx++] = arr[current];
            current = parent[current];
        }

        for(int i = maxIndex ; i >= 0 ; i--) {
            sb.append(answer[i]).append(" ");
        }
        
        System.out.println(maxIndex + 1);
        System.out.println(sb);
    }
    
    public static int lowerBound(int[] arr, int index, int num) {
    	int start = 0;
    	int end = index;
    	
    	while(start < end) {
    		int mid = (start+end)/2;
    		if(arr[mid] < num) {
    			start = mid + 1;
    		} else {
    			end = mid;
    		}
    	}
    	return start;
    }
}


