package com.practice;

import java.util.Scanner;

public class RabinKarpSubstringSearch {

    private static boolean RabinKarp(char[] str, char[] pattern) {
        int m = pattern.length;
        int n = str.length;
        long patternHashcode = generateHashcode(pattern, m - 1);
        long strHashcode = generateHashcode(str, m - 1);
        for (int i = 1; i <= n - m + 1; i++) {
            if (patternHashcode == strHashcode && matchStrAndPattern(str, i - 1, i + m - 2, pattern, 0, m - 1)) {
                return true;
            }
            if (i < n - m + 1) {
                strHashcode = reGenerateNewHashCode(str, i - 1, i + m - 1, strHashcode, m);
            }
        }
        return false;
    }

    private static long reGenerateNewHashCode(char[] str, int oldIndex, int newIndex, long oldHashcode, int size) {
        int prime = 547;
        long newHashcode = oldHashcode - str[oldIndex];
        newHashcode = newHashcode / prime;
        newHashcode += str[newIndex] * Math.pow(prime, size - 1);
        return newHashcode;
    }

    private static long generateHashcode(char[] str, int size) {
        int prime = 547;
        long hashcode = 0;
        for (int i = 0; i <= size; i++) {
            hashcode += str[i] * Math.pow(prime, i);
        }
        return hashcode;
    }

    private static boolean matchStrAndPattern(char str1[], int start1, int end1, char str2[], int start2, int end2) {
        if (end1 - start1 != end2 - start2) {
            return false;
        }
        while (start1 <= end1 && start2 <= end2) {
            if (str1[start1] != str2[start2]) {
                return false;
            }
            start1++;
            start2++;
        }
        return true;
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String pattern = input.nextLine();
        System.out.print(RabinKarp(str.toCharArray(), pattern.toCharArray()));
    }
}
