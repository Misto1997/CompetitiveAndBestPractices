package com.hackerearth;


import java.util.Scanner;

public class MinimumNodes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        System.out.println(findMinimumNodes(height));
    }

    private static int findMinimumNodes(int height) {
        if (height == 0)
            return 1;
        if (height == 1)
            return 2;
        return 1 + findMinimumNodes(height - 1) + findMinimumNodes(height - 2);
    }

}
