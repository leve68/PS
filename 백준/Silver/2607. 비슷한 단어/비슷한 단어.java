import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    static HashMap<Character, Integer> sMap;
    static int sLength;
    public  static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();

        sMap = new HashMap<Character, Integer>();
        sLength = s.length;
        for(char c : s) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }

        int count = 0;
        for (int i = 1; i < n; i++) {
            if(isSim(br.readLine())){
                count++;
            }
        }

        System.out.println(count);
    }

    static boolean isSim(String str){
        int tLength = str.length();

        if(Math.abs(sLength - tLength) > 1) {
            return false;
        }

        HashMap<Character, Integer> tMap = new HashMap<Character, Integer>();
        for(char c : str.toCharArray()){
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        HashSet<Character> allChars = new HashSet<>();
        allChars.addAll(sMap.keySet());
        allChars.addAll(tMap.keySet());

        int diffCount = 0;
        int totalDiff = 0;

        for(char c : allChars){
            int sCount = sMap.getOrDefault(c, 0);
            int tCount = tMap.getOrDefault(c, 0);

            if(sCount != tCount){
                diffCount++;
                totalDiff += Math.abs(sCount - tCount);
            }
        }

        // 완전히 같은 구성
        if(diffCount == 0) return true;

        // 한 문자 추가/제거인 경우
        if(Math.abs(sLength - tLength) == 1 && diffCount == 1 && totalDiff == 1) return true;

        // 한 문자 교체인 경우
        if(sLength == tLength && diffCount == 2 && totalDiff == 2) return true;

        return false;
    }
}
