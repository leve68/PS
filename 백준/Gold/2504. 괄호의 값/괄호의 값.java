import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int temp = 1;

        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];

            if (current == '(' || current == '[') {
                stack.push(current);
                if (current == '(') {
                    temp *= 2;
                } else {
                    temp *= 3;
                }
            } else if (current == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
                if (i > 0 && chars[i - 1] == '(') {
                    answer += temp;
                }
                temp /= 2;
            } else if (current == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
                if (i > 0 && chars[i - 1] == '[') {
                    answer += temp;
                }
                temp /= 3;
            } else {
                answer = 0;
                break;
            }
        }

        if (!stack.isEmpty()) {
            answer = 0;
        }

        System.out.println(answer);
    }
}