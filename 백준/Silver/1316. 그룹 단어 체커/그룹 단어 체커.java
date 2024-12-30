import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int answer = 0;
		int n = in.nextInt();
		for(int j = 0; j<n ; j++) {
			String word = in.next();
			
			//배열로 쪼개서 char 배열로 만들기
			char[] testChar = word.toCharArray();
			int[] testInt = new int[testChar.length];
			//각 char를 int값으로 바꾸기
			for(int i = 0; i<testChar.length ; i++) {
				testInt[i] = (int) (testChar[i] - 'a');
			}
			//미리 준비한 int 배열에 맞춰보기
			int[] lastIndex = new int[26];
			boolean isGroup = true;
			//현재 index와 int 배열에 저장된 값의 차이가 1이상이면 그룹 단어가 아님
			for(int i = 0; i<testInt.length ; i++) {
				if(lastIndex[testInt[i]] == 0 || lastIndex[testInt[i]] == i)
					lastIndex[testInt[i]] = i + 1;
				else {
					isGroup = false;
					break;
				}
			}
			if(isGroup) answer++;
		}
		System.out.println(answer);
	}
}