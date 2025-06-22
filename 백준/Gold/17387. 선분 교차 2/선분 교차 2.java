import java.io.*;
import java.util.*;

public class Main {
    public static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Point[] points = new Point[4];
        for (int i = 0; i < 4; i += 2) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            points[i + 1] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }

        System.out.println(solve(points[0], points[1], points[2], points[3]));
    }

    private static long ccw(Point a1, Point a2, Point b) {
        return (a2.x - a1.x) * (b.y - a1.y) - (a2.y - a1.y) * (b.x - a1.x);
    }

    private static boolean onSeg(Point a1, Point a2, Point b) {
        return (b.x >= Math.min(a1.x, a2.x) && b.x <= Math.max(a1.x, a2.x)) && (b.y >= Math.min(a1.y, a2.y) && b.y <= Math.max(a1.y, a2.y));
    }

    private static int solve(Point a1, Point a2, Point b1, Point b2) {
        long da1 = ccw(a1, a2, b1);
        long da2 = ccw(a1, a2, b2);
        long db1 = ccw(b1, b2, a1);
        long db2 = ccw(b1, b2, a2);

        if (((da1 > 0 && da2 < 0) || (da1 < 0 && da2 > 0)) && ((db1 > 0 && db2 < 0) || (db1 < 0 && db2 > 0))) {
            return 1;
        } else if (
                (da1 == 0 && onSeg(a1, a2, b1)) ||
                (da2 == 0 && onSeg(a1, a2, b2)) ||
                (db1 == 0 && onSeg(b1, b2, a1)) ||
                (db2 == 0 && onSeg(b1, b2, a2))
        ) {
            return 1;
        } else {
            return 0;
        }
    }
}