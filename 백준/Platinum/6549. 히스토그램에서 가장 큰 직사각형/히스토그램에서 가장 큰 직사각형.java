import java.io.*;
import java.util.*;

public class Main {
    public static class Seg {
        int[] origin;
        int[] segTree;

        public Seg(int[] origin) {
            this.origin = origin.clone();
            this.segTree = new int[origin.length * 4];

            init(0, origin.length - 1, 1);
        }

        int init(int start, int end, int index) {
            if(start == end) {
                return segTree[index] = start;
            }

            int mid = (start + end) / 2;
            int leftIndex = init(start, mid, index * 2);
            int rightIndex = init(mid + 1, end, index * 2 + 1);
            return segTree[index] = origin[leftIndex] < origin[rightIndex] ? leftIndex : rightIndex;
        }

        int getMinIndex(int start, int end, int index, int left, int right) {
            if(end < left || right < start) return -1;
            if(left <= start && end <= right) return segTree[index];

            int mid = (start + end) / 2;
            int leftIndex = getMinIndex(start, mid, index * 2, left, right);
            int rightIndex = getMinIndex(mid + 1, end, index * 2 + 1, left, right);
            if(leftIndex != -1 && rightIndex != -1) {
                return origin[leftIndex] < origin[rightIndex] ? leftIndex : rightIndex;
            } else if (leftIndex != -1) {
                return leftIndex;
            } else if (rightIndex != -1) {
                return rightIndex;
            }
            return -1;
        }

        long getArea(int left, int right) {
            if(left == right) return origin[left];
            int minIdx = getMinIndex(0, origin.length - 1, 1, left, right);
            long area = (long)origin[minIdx] * (right - left + 1);

            if (minIdx > left)
                area = Math.max(area, getArea(left, minIdx - 1));
            if (minIdx < right)
                area = Math.max(area, getArea(minIdx + 1, right));

            return area;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if(t == 0) break;

            int[] input = new int[t];
            for(int i = 0; i < t; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            Seg seg = new Seg(input);
            sb.append(seg.getArea(0, input.length - 1)).append("\n");
        }

        System.out.println(sb);
    }
}