import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int answer = 0;
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigDecimal[] bigSlopeArr = new BigDecimal[n];
        BigDecimal[] smallSlopeArr = new BigDecimal[n];

        for (int i = 0; i < n; i++) {
            // 오른쪽 아래, 왼쪽 위
        	int lx = sc.nextInt(); // 왼쪽 x
        	int ly = sc.nextInt(); // 왼쪽 y
            int ox = sc.nextInt(); // 오른쪽 x
            int oy = sc.nextInt(); // 오른쪽 y

            BigDecimal bigSlope = new BigDecimal(oy).divide(new BigDecimal(lx), 10, BigDecimal.ROUND_HALF_UP);
            BigDecimal smallSlope = new BigDecimal(ly).divide(new BigDecimal(ox), 10, BigDecimal.ROUND_HALF_UP);

            smallSlopeArr[i] = smallSlope; 
            bigSlopeArr[i] = bigSlope;
        }
		
        Arrays.sort(smallSlopeArr);
        Arrays.sort(bigSlopeArr);
        
        int indexS = 0;
        int indexB = 0;
        int current = 0;
        
        while(indexS < n) {
        	int result = smallSlopeArr[indexS].compareTo(bigSlopeArr[indexB]);
        	if(result == 0 || result == -1) {
        		current++;
        		indexS++;
        	} else {
        		current--;
        		indexB++;
        	}
        	answer = Math.max(answer, current);
        }
        
        System.out.println(answer);
    }
}