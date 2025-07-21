import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();

        int sum = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                stack.push(input[i]);
            } else if (input[i] == ')') {
                if (input[i - 1] == '(') {
                    // 레이저
                    stack.pop();
                    sum += stack.size();
                } else {
                    stack.pop();
                    sum++;
                }
            }
        }

        System.out.println(sum);
    }
}