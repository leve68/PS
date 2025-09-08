import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        int aCount = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 'a') {
                aCount++;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i + aCount < input.length ; i++) {
            int count = 0;
            for (int j = i ; j < i + aCount ; j++) {
                if (input[j] != 'a') {
                    count++;
                }
            }
            min = Math.min(min, count);
        }

        int s = 0;
        int e = input.length - aCount;
        while (s < input.length) {
            int count = 0;
            for (int i = 0 ; i < s ; i++) {
                if (input[i] != 'a') {
                    count++;
                }
            }

            for (int i = e ; i < input.length ; i++) {
                if (input[i] != 'a') {
                    count++;
                }
            }

            min = Math.min(min, count);
            s++;
            e++;
        }

        System.out.println(min);
    }
}