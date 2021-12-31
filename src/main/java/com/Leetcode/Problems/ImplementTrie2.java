package com.Leetcode.Problems;


public class ImplementTrie2 {
    TrieImpl1 root;

    public ImplementTrie2() {
        root = new TrieImpl1();
    }

    public void insert(String word) {
        TrieImpl1 temp = root;
        char[] ar = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (temp.childNode[index] == null) {
                temp.childNode[index] = new TrieImpl1();
            }
            temp = temp.childNode[index];
            if (!temp.isAWord || i == word.length() - 1)
                temp.count += 1;
        }
        temp.isAWord = true;
    }

    public int countWordsEqualTo(String word) {
        TrieImpl1 temp = root;
        char[] ar = word.toCharArray();
        for (char ch : ar) {
            int index = ch - 'a';
            if (temp.childNode[index] == null) {
                return 0;
            }
            temp = temp.childNode[index];
        }
        return temp.isAWord ? temp.count : 0;
    }

    public int countWordsStartingWith(String word) {
        TrieImpl1 temp = root;
        char[] ar = word.toCharArray();
        for (char ch : ar) {
            int index = ch - 'a';
            if (temp.childNode[index] == null) {
                return 0;
            }
            temp = temp.childNode[index];
        }
        return temp.count;
    }

    public void erase(String word) {
        TrieImpl1 temp = root;
        eraseString(word, 0, temp);
    }

    private int eraseString(String word, int i, TrieImpl1 temp) {
        if (temp == null)
            return 0;
        if (i == word.length()) {
            temp.isAWord = false;
            int count = 0;
            for (int counter = 0; counter < 26; counter++) {
                if (temp.childNode[counter] != null)
                    count += temp.childNode[counter].count;
            }
            temp.count -= count;
            return temp.count;
        }
        int index = word.charAt(i) - 'a';
        int count = eraseString(word, i + 1, temp.childNode[index]);
        temp.count -= count;
        return count;
    }

    public static void main(String[] args) {
        ImplementTrie2 ob = new ImplementTrie2();
        ob.insert("qeeaad");
        ob.insert("dfweadsaf");
        ob.insert("ewadasewd");
        System.out.println(ob.countWordsEqualTo("ewadasewd"));


        ob.erase("ew");

        System.out.println(ob.countWordsEqualTo("dfweadsaf"));

    }

}

class TrieImpl1 {
    TrieImpl1[] childNode = new TrieImpl1[26];
    int count;
    boolean isAWord = false;
}