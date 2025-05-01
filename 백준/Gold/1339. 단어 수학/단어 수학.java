import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] matrix = new char[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = br.readLine().toCharArray();
        }
        Arrays.sort(matrix, (a, b) -> a.length - b.length);

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                map.put(matrix[i][j], map.getOrDefault(matrix[i][j], 0) + (int) Math.pow(10, (matrix[i].length - j - 1)));
            }
        }

        Integer[] arr = new Integer[map.size()];
        int index = 0;
        for(char c : map.keySet()){
            arr[index] = map.get(c);
            index++;
        }
        Arrays.sort(arr, Collections.reverseOrder());
        int sum = 0;
        int num = 9;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i] * num--;
        }

        System.out.println(sum);
    }
}