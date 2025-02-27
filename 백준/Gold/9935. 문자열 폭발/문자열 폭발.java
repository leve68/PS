import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input1 = br.readLine();
        String input2 = br.readLine();
        
        char[] origin = input1.toCharArray();
        char[] bomb = input2.toCharArray();
        
        Stack<Character> newString = new Stack<>();
        
        for (int i = 0; i < origin.length; i++) {
            newString.push(origin[i]);
            
            // 폭발 문자열의 길이보다 스택이 커지면 검사 시작
            if (newString.size() >= bomb.length) {
                boolean isMatch = true;
                
                // 스택의 최근 문자들이 폭발 문자열과 일치하는지 확인
                for (int j = 0; j < bomb.length; j++) {
                    if (newString.get(newString.size() - bomb.length + j) != bomb[j]) {
                        isMatch = false;
                        break;
                    }
                }
                
                // 일치하면 폭발 문자열 길이만큼 스택에서 제거
                if (isMatch) {
                    for (int j = 0; j < bomb.length; j++) {
                        newString.pop();
                    }
                }
            }
        }
        
        for (char c : newString) {
            sb.append(c);
        }
        
        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}