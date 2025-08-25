import java.io.*;
import java.util.*;

public class Main {
    public static String init = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
    public static String query = "\"재귀함수가 뭔가요?\"";
    public static String answer1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
    public static String answer2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
    public static String answer3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
    public static String realAnswer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
    public static String answerEnd = "라고 답변하였지.";
    public static String tab = "____";
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        sb.append(init).append("\n").append(query).append("\n").append(answer1).append("\n").append(answer2).append("\n").append(answer3).append("\n");

        dfs(n - 1, 1);

        sb.append(answerEnd);
        System.out.println(sb);
    }

    private static void dfs(int depth, int weight) {
        if (depth == 0) {
            addTab(weight);
            sb.append(query).append("\n");
            addTab(weight);
            sb.append(realAnswer).append("\n");
            addTab(weight);
            sb.append(answerEnd).append("\n");
            return;
        }

        for (int i = 0 ; i < weight; i++) {
            sb.append(tab);
        }
        sb.append(query).append("\n");
        addTab(weight);
        sb.append(answer1).append("\n");
        addTab(weight);
        sb.append(answer2).append("\n");
        addTab(weight);
        sb.append(answer3).append("\n");

        dfs(depth - 1, weight + 1);

        addTab(weight);
        sb.append(answerEnd).append("\n");
    }

    private static void addTab(int count) {
        for (int i = 0 ; i < count; i++) {
            sb.append(tab);
        }
    }
}