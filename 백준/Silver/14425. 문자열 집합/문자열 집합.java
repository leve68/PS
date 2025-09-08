import java.io.*;
import java.util.*;

public class Main {
    static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26]; // 알파벳 소문자 26개
            isEnd = false;
        }
    }
    static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 문자열 삽입
        public void insert(String word) {
            TrieNode current = root;

            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                current = current.children[index];
            }

            current.isEnd = true;
        }

        // 문자열 검색
        public boolean search(String word) {
            TrieNode current = root;

            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';

                if (current.children[index] == null) {
                    return false;
                }

                current = current.children[index];
            }

            return current.isEnd;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            trie.insert(str);
        }

        int count = 0;

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (trie.search(str)) {
                count++;
            }
        }

        System.out.println(count);
    }
}