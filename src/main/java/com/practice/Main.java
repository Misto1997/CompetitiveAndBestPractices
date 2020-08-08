package com.practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ar[][] = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                ar[i][j] = sc.nextInt();
        System.out.println(n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i + " " + j);
            }
        }
    }
}
