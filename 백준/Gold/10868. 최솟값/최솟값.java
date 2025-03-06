import java.io.*;
import java.util.*;

public class Main {
    static class Seg {
        int[] origin;
        int[] segTree;
        
        Seg(int n) {
            origin = new int[n];
            origin[0] = Integer.MAX_VALUE;
            segTree = new int[4 * n];
        }
        
        int init(int start, int end, int index) {
            if (start == end) return segTree[index] = start;
            
            int mid = (start + end) / 2;
            int leftChild = init(start, mid, index * 2);
            int rightChild = init(mid + 1, end, index * 2 + 1);
            
            if (origin[leftChild] <= origin[rightChild]) {
                return segTree[index] = leftChild;
            } else {
                return segTree[index] = rightChild;
            }
        }
        
        int getMinIndex(int start, int end, int index, int left, int right) {
            if (right < start || left > end) return 0;
            if (left <= start && end <= right) return segTree[index];
            
            int mid = (start + end) / 2;
            int leftChild = getMinIndex(start, mid, index * 2, left, right);
            int rightChild = getMinIndex(mid + 1, end, index * 2 + 1, left, right);
            
            if (origin[leftChild] <= origin[rightChild]) {
                return leftChild;
            } else {
                return rightChild;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        Seg seg = new Seg(N + 1);
        
        for (int i = 1; i <= N; i++) {
            seg.origin[i] = Integer.parseInt(br.readLine());
        }
        
        // 세그트리 만들기
        seg.init(1, N, 1);
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            int minIndex = seg.getMinIndex(1, N, 1, a, b);
            sb.append(seg.origin[minIndex]).append("\n");
        }
        
        System.out.print(sb);
    }
}