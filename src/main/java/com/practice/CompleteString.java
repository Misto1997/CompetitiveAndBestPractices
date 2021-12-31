package com.practice;

import java.util.Scanner;

class TrieImpl {
    TrieImpl[] childNode = new TrieImpl[26];
    boolean isAWord = false;
}

public class CompleteString {
    TrieImpl root;
    static int max;

    public CompleteString() {
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

    private static String completeString(int n, String[] a) {
        CompleteString ob = new CompleteString();
        for (String str : a) {
            ob.insert(str);
        }
        String word = getPrefix(0, "", "None", ob.root);
        return word;
    }

    private static String getPrefix(int i, String str, String word, TrieImpl root) {
        if (i > max) {
            max = i;
            word = str;
        }
        if (root == null)
            return word;
        for (int counter = 0; counter < 26; counter++) {
            if (root.childNode[counter] != null && root.childNode[counter].isAWord)
                word = getPrefix(i + 1, str + (char) (97 + counter), word, root.childNode[counter]);
        }

        return word;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while (t-- > 0) {
            int n = input.nextInt();
            String[] a = new String[n];
            for (int i = 0; i < n; i++) {
                a[i] = input.next();
            }
            max=0;
            System.out.println(completeString(n, a));
        }
    }
}
