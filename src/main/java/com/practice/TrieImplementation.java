package com.practice;

import java.util.Arrays;

public class TrieImplementation {
    static final int ALPHABET_SIZE = 26;
    static TrieNode root;

    static void insert(String word) {
        int size = word.length();
        int index;
        TrieNode node = root;
        for (int i = 0; i < size; i++) {
            index = word.charAt(i) - 'a';
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.isLastWord = true;
    }

    static String search(String word) {
        int size = word.length();
        int index;
        TrieNode node = root;
        for (int i = 0; i < size; i++) {
            index = word.charAt(i) - 'a';
            if (node.children[index] == null)
                return "Not Found";
            node = node.children[index];
        }
        if (node != null && node.isLastWord)
            return "found";
        return "Not Found";
    }

    public static void main(String args[]) {
        String words[] = {"data", "structure", "date", "struct", "stand", "dam"};
        root = new TrieNode();
        for (int i = 0; i < words.length; i++)
            insert(words[i]);
        System.out.println(search("data"));
        System.out.println(search("datm"));
        System.out.println(search("date"));
        System.out.println(search("dam"));
        System.out.println(search("dat"));
        System.out.println(search("structure"));
        System.out.println(search("struc"));
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isLastWord;

        TrieNode() {
            isLastWord = false;
            Arrays.fill(children, null);
        }
    }
}
