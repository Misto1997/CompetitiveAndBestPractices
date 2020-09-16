package com.Leetcode.InterviewQuestionsEasy.String;

import java.util.Scanner;

public class ValidPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        System.out.println(checkValidPalindrome(s));
    }

    private static boolean checkValidPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        char ar[] = s.toCharArray();
        while (start < end) {
            if (!Character.isLetterOrDigit(ar[start])) start++;
            else if (!Character.isLetterOrDigit(ar[end])) end--;
            else if (Character.toLowerCase(ar[start++]) != Character.toLowerCase(ar[end--]))
                return false;
        }
        return true;
    }

}

