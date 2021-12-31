package com.Leetcode.Problems;


class TrieImpl {
    TrieImpl[] childNode = new TrieImpl[26];
    boolean isAWord = false;
}

public class ImplementTrie {
    TrieImpl root;

    public ImplementTrie() {
        root = new TrieImpl();
    }

    public void insert(String word) {
        TrieImpl temp = root;
        char[] ar = word.toCharArray();
        for (char ch : ar) {
            int index = ch - 'a';
            if (temp.childNode[index] == null) {
                temp.childNode[index] = new TrieImpl();
            }
            temp = temp.childNode[index];
        }
        temp.isAWord = true;
    }

    public boolean search(String word) {
        TrieImpl temp = root;
        char[] ar = word.toCharArray();
        for (char ch : ar) {
            int index = ch - 'a';
            if (temp.childNode[index] == null) {
                return false;
            }
            temp = temp.childNode[index];
        }
        return temp.isAWord;
    }

    public boolean startsWith(String prefix) {
        TrieImpl temp = root;
        char[] ar = prefix.toCharArray();
        for (char ch : ar) {
            int index = ch - 'a';
            if (temp.childNode[index] == null) {
                return false;
            }
            temp = temp.childNode[index];
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(""+(char)98);
       /* ImplementTrie trie = new ImplementTrie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app"));*/
    }
}
