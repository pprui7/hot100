package solution;

import java.util.*;

public class Trie {
    class Tree {
        char c;
        Map<Character, Tree> next;
        boolean wordEndFlag;

        Tree() {
            next = new HashMap<>();
        }

        Tree(char c) {
            this.c = c;
            next = new HashMap<>();
        }
    }
    private final Tree root;

    public Trie() {
        root = new Tree();
    }

    public void insert(String word) {
        Tree pre = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            pre.next.putIfAbsent(c, new Tree(c));
            pre = pre.next.get(c);
        }
        pre.wordEndFlag = true;
    }

    public boolean search(String word) {
        Tree node = root;
        for (char c : word.toCharArray()) {
            node = node.next.get(c);
            if (node == null) return false;
        }
        return node.wordEndFlag;
    }
    public boolean startsWith(String word) {
        Tree node = root;
        for (char c : word.toCharArray()) {
            node = node.next.get(c);
            if (node == null) return false;
        }
        return true;
    }

}
