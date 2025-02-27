import java.io.*;
import java.util.*;

public class Main {
    public static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int todayYear = Integer.parseInt(st.nextToken());
        int todayMonth = Integer.parseInt(st.nextToken());
        int todayDay = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int dDayYear = Integer.parseInt(st.nextToken());
        int dDayMonth = Integer.parseInt(st.nextToken());
        int dDayDay = Integer.parseInt(st.nextToken());

        if (isThousandYears(todayYear, todayMonth, todayDay, dDayYear, dDayMonth, dDayDay)) {
            System.out.println("gg");
        } else {
            int daysBetween = getDaysBetween(todayYear, todayMonth, todayDay, dDayYear, dDayMonth, dDayDay);
            System.out.println("D-" + daysBetween);
        }
    }

    public static boolean isThousandYears(int y1, int m1, int d1, int y2, int m2, int d2) {
        if (y2 - y1 > 1000) {
            return true;
        } else if (y2 - y1 == 1000) {
            if (m2 > m1) {
                return true;
            } else if (m2 == m1) {
                return d2 >= d1;
            }
        }
        return false;
    }

    // 두 날짜 사이의 일수
    public static int getDaysBetween(int y1, int m1, int d1, int y2, int m2, int d2) {
        if (y1 == y2) {
            return getDays(y2, m2, d2) - getDays(y1, m1, d1);
        }
        
        int totalDays = 0;
        totalDays += daysInYear(y1) - getDays(y1, m1, d1) + 1;
        for (int year = y1 + 1; year < y2; year++) {
            totalDays += daysInYear(year);
        }
        totalDays += getDays(y2, m2, d2);
        return totalDays - 1;
    }

    // 해당 연도의 1월 1일부터 날짜까지 일수
    private static int getDays(int year, int month, int day) {
        int result = day;

        for (int i = 1; i < month; i++) {
            result += days[i];
            if (i == 2 && isLeapYear(year)) {
                result++; // 윤년이면 2월에 하루 추가
            }
        }

        return result;
    }

    // 윤년 여부
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 해당 연도의 총 일수
    private static int daysInYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }
}