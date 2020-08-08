package com.practice;

import java.util.Scanner;

public class KMPSubstringSearch {

    private static int[] prefixSuffixArrayBuilder(char pattern[]) {
        int[] prefixSuffixArray = new int[pattern.length];
        int j = 0, i = 1;
        while (i < pattern.length) {
            if (pattern[i] == pattern[j]) {
                prefixSuffixArray[i] = j + 1;
                j++;
                i++;
            } else {
                if (j != 0) {
                    j = prefixSuffixArray[j - 1];
                } else {
                    prefixSuffixArray[i] = 0;
                    i++;
                }
            }
        }
        return prefixSuffixArray;
    }

    private static boolean KMP(char[] str, char[] pattern) {
        int prefixSuffixArray[] = prefixSuffixArrayBuilder(pattern);
        int i = 0, j = 0;
        while (i < str.length && j < pattern.length) {
            if (str[i] == pattern[j]) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = prefixSuffixArray[j - 1];
                } else {
                    i++;
                }
            }
        }
        if (j == pattern.length) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String pattern = input.nextLine();
        System.out.print(KMP(str.toCharArray(), pattern.toCharArray()));

    }
}
