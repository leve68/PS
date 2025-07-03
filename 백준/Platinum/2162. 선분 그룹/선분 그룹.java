import java.io.*;
import java.util.*;

public class Main {
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Seg {
        Point p1;
        Point p2;

        public Seg(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        Seg[] segs = new Seg[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            segs[i] = new Seg(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())), new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isCrossing(segs[i].p1, segs[i].p2, segs[j].p1, segs[j].p2)) {
                    union(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            parent[i] = find(parent[i]);
        }

        Map<Integer, Integer> count = new HashMap<>();
        for (int p : parent) {
            count.put(p, count.getOrDefault(p, 0) + 1);
        }

        int groupCount = count.size();
        int maxCount = 0;
        for (int c : count.keySet()) {
            maxCount = Math.max(maxCount, count.get(c));
        }

        System.out.println(groupCount);
        System.out.println(maxCount);
    }

    private static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        if (p1 != p2) {
            parent[Math.min(p1, p2)] = Math.max(p1, p2);
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static int ccw(Point a1, Point a2, Point b) {
        return (a2.x - a1.x) * (b.y - a1.y) - (a2.y - a1.y) * (b.x - a1.x);
    }

    private static boolean onSeg(Point a1, Point a2, Point b) {
        return (b.x >= Math.min(a1.x, a2.x) && b.x <= Math.max(a1.x, a2.x)) && (b.y >= Math.min(a1.y, a2.y) && b.y <= Math.max(a1.y, a2.y));
    }

    private static boolean isCrossing(Point a1, Point a2, Point b1, Point b2) {
        long da1 = ccw(a1, a2, b1);
        long da2 = ccw(a1, a2, b2);
        long db1 = ccw(b1, b2, a1);
        long db2 = ccw(b1, b2, a2);

        if (((da1 > 0 && da2 < 0) || (da1 < 0 && da2 > 0)) && ((db1 > 0 && db2 < 0) || (db1 < 0 && db2 > 0))) {
            return true;
        } else if (
                (da1 == 0 && onSeg(a1, a2, b1)) ||
                        (da2 == 0 && onSeg(a1, a2, b2)) ||
                        (db1 == 0 && onSeg(b1, b2, a1)) ||
                        (db2 == 0 && onSeg(b1, b2, a2))
        ) {
            return true;
        } else {
            return false;
        }
    }
}