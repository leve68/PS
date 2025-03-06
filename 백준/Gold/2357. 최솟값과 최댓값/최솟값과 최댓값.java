import java.io.*;
import java.util.*;

public class Main {
    static class SegmentTree {
        int[] arr;
        int[] minTree;
        int[] maxTree;
        
        SegmentTree(int n) {
            arr = new int[n + 1];
            minTree = new int[4 * n];
            maxTree = new int[4 * n];
        }
        
        void initMin(int node, int start, int end) {
            if (start == end) {
                minTree[node] = arr[start];
                return;
            }
            
            int mid = (start + end) / 2;
            initMin(node * 2, start, mid);
            initMin(node * 2 + 1, mid + 1, end);
            
            minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
        }
        
        void initMax(int node, int start, int end) {
            if (start == end) {
                maxTree[node] = arr[start];
                return;
            }
            
            int mid = (start + end) / 2;
            initMax(node * 2, start, mid);
            initMax(node * 2 + 1, mid + 1, end);
            
            maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        }
        
        int getMin(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return Integer.MAX_VALUE;
            }
            
            if (left <= start && end <= right) {
                return minTree[node];
            }
            
            int mid = (start + end) / 2;
            int leftMin = getMin(node * 2, start, mid, left, right);
            int rightMin = getMin(node * 2 + 1, mid + 1, end, left, right);
            
            return Math.min(leftMin, rightMin);
        }
        
        int getMax(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return Integer.MIN_VALUE;
            }
            
            if (left <= start && end <= right) {
                return maxTree[node];
            }
            
            int mid = (start + end) / 2;
            int leftMax = getMax(node * 2, start, mid, left, right);
            int rightMax = getMax(node * 2 + 1, mid + 1, end, left, right);
            
            return Math.max(leftMax, rightMax);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        SegmentTree segTree = new SegmentTree(N);
        
        for (int i = 1; i <= N; i++) {
            segTree.arr[i] = Integer.parseInt(br.readLine());
        }
        
        // Initialize both min and max segment trees
        segTree.initMin(1, 1, N);
        segTree.initMax(1, 1, N);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            int min = segTree.getMin(1, 1, N, a, b);
            int max = segTree.getMax(1, 1, N, a, b);
            
            sb.append(min).append(" ").append(max).append("\n");
        }
        
        System.out.print(sb);
    }
}