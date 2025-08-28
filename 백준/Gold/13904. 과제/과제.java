import java.io.*;
import java.util.*;

public class Main {
    public static class Task {
        int d;
        int w;

        public Task(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<Task>> tasks = new HashMap<>();
        int maxDay = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Task task = new Task(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            if (tasks.containsKey(task.d)) {
                tasks.get(task.d).add(task);
            } else {
                tasks.put(task.d, new ArrayList<Task>());
                tasks.get(task.d).add(task);
            }
            maxDay = Math.max(maxDay, task.d);
        }

        int answer = 0;
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> b.w - a.w);
        for (int i = maxDay ; i > 0 ; i--) {
            if(tasks.containsKey(i)) {
                for (Task task : tasks.get(i)) {
                    pq.add(task);
                }
            }

            if(pq.isEmpty()) {
                continue;
            }
            answer += pq.poll().w;
        }

        System.out.println(answer);
    }
}