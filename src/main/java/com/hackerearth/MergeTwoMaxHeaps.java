package com.hackerearth;

import java.util.Scanner;

public class MergeTwoMaxHeaps {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ar1size = input.nextInt();
        int ar1[] = new int[ar1size];
        for (int i = 0; i < ar1size; i++)
            ar1[i] = input.nextInt();
        int ar2size = input.nextInt();
        int ar2[] = new int[ar2size];
        for (int i = 0; i < ar2size; i++)
            ar2[i] = input.nextInt();
        int newAr[] = new int[ar1size + ar2size];
        mergeBothMaxHeaps(ar1, ar2, ar1size, ar2size, newAr);
        for (int i = 0; i < newAr.length; i++)
            System.out.print(newAr[i] + " ");
    }

    private static void mergeBothMaxHeaps(int[] ar1, int[] ar2, int ar1size, int ar2size, int newAr[]) {
        for (int i = 0; i < ar1size; i++)
            newAr[i] = ar1[i];
        for (int i = 0; i < ar2size; i++)
            insert(newAr, ar1size, i, ar2);
    }

    private static void insert(int[] newAr, int ar1size, int i, int[] ar2) {
        newAr[ar1size + i] = ar2[i];
        int temp = ar1size + i;
        while (newAr[temp] > newAr[temp / 2]) {
            swap(newAr, temp, temp / 2);
            temp /= 2;
        }
    }

    private static void swap(int[] newAr, int i, int j) {
        int temp = newAr[i];
        newAr[i] = newAr[j];
        newAr[j] = temp;
    }

}
