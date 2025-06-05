import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] nums = new int[n];
            String[] input = new String[n];
            for (int j = 0; j < n; j++) {
                String tmp = br.readLine();
                while(tmp.length() < 10) {
                    tmp += "_";
                }
                input[j] = tmp;
            }

            String answer = "YES";
            Arrays.sort(input);
            for(int j = 1; j < n; j++) {
                String a = input[j-1];
                String b = input[j];

                for(int k = 0 ; k < 10 ; k++){
                    if(a.toCharArray()[k] == '_' || b.toCharArray()[k] == '_') {
                        answer = "NO";
                        break;
                    }

                    if(a.toCharArray()[k] != b.toCharArray()[k]) {
                        break;
                    }
                }
                if(answer.equals("NO")) break;
            }
            System.out.println(answer);
        }
    }
}