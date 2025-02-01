import java.io.*;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for(int test_case = 1 ; test_case<=T ; test_case++) {
        	int n = Integer.parseInt(br.readLine());
        	StringTokenizer st1 = new StringTokenizer(br.readLine());
        	LinkedList<Integer> origin = new LinkedList<Integer>();
        	for(int i = 0 ; i < n ; i++) {
        		origin.add(Integer.parseInt(st1.nextToken()));
        	}
        	int m = Integer.parseInt(br.readLine());
        	StringTokenizer st2 = new StringTokenizer(br.readLine());
        	for(int i = 0 ; i < m ; i++) {
        		String command = st2.nextToken();
        		switch(command) {
        		case "I":
        			int indexI = Integer.parseInt(st2.nextToken());
        			int countI = Integer.parseInt(st2.nextToken());
        			for(int j = 0 ; j < countI ; j++) {
        				origin.add(indexI + j, Integer.parseInt(st2.nextToken()));
        			}
        			break;
        		case "D":
        			int indexD = Integer.parseInt(st2.nextToken());
        			int countD = Integer.parseInt(st2.nextToken());
        			for(int j = 0 ; j < countD ; j++) {
        				origin.remove(indexD + 1);
        			}
        			break;
        		case "A":
        			int countA = Integer.parseInt(st2.nextToken());
        			for(int j = 0 ; j < countA ; j++) {
        				origin.addLast(Integer.parseInt(st2.nextToken()));
        			}
        			break;
        		}
        	}
        	
            if (origin.size() > 10) {
            	origin.subList(10, origin.size()).clear();
            }

            System.out.println("#" + test_case + " " + 
            	    origin.toString().replaceAll("[\\[\\],]", "")
            	);
        }
        br.close();
    }
}