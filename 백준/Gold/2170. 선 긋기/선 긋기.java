import java.io.*;
import java.util.*;

class Main
{
	static class Line {
		int min;
		int max;
		
		Line(int min, int max){
			this.min = min;
			this.max = max;
		}
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Line[] lineArr = new Line[N];
		StringTokenizer st;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			lineArr[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(lineArr, (a,b) -> a.min - b.min);
		
		int sum = 0;
		int min = lineArr[0].min;
		int max = lineArr[0].max;
		for(int i = 1 ; i < N ; i++) {
			Line curL = lineArr[i];
			if(max < curL.min) {
				sum += (max - min);
				min = curL.min;
				max = curL.max;
			}
			max = Math.max(max, curL.max);
		}
		sum += (max - min);
		
		System.out.println(sum);
	}
}