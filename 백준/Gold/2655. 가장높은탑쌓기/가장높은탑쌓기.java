import java.io.*;
import java.util.*;
public class Main {
    static class Brick{
    	int id;
        int area;
        int height;
        int weight;
        
        Brick(int id, int area, int height, int weight) {
        	this.id = id;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }
    }
    static class Item{
    	int sum;
    	Stack<Integer> stack;
    	
    	Item(int sum, int id){
    		this.sum = sum;
    		stack = new Stack<Integer>();
    		stack.add(id);
    	}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        Brick[] bricks = new Brick[N];
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            bricks[i-1] = new Brick(i, area, height, weight);
        }
        
        Arrays.sort(bricks, (a,b) -> a.area-b.area);
        
        Item[] dp = new Item[N];
        
        for(int i = 0 ; i < N ; i++) {
            dp[i] = new Item(bricks[i].height, i);
            for(int j = 0 ; j < i ; j++) {
                if(bricks[i].weight > bricks[j].weight && dp[j].sum + bricks[i].height > dp[i].sum) {
                    Stack<Integer> tmp = new Stack<Integer>();
                    tmp.addAll(dp[j].stack);
                    tmp.add(i);
                    dp[i].sum = dp[j].sum + bricks[i].height;
                    dp[i].stack = tmp;
                }
            }
        }
        
        int maxHeight = 0;
        Stack<Integer> answer = new Stack<Integer>();
        for(int i = 0 ; i < N ; i++) {
        	if(dp[i].sum > maxHeight) {
        		answer = new Stack<Integer>();
        		maxHeight = dp[i].sum;
        		answer.addAll(dp[i].stack);
        	}
        }
        
        sb.append(answer.size()).append("\n");
        for(int index : answer) {
        	sb.append(bricks[index].id).append("\n");
        }
        
        System.out.println(sb);
    }
}