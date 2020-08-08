package com.practice;


import java.util.Scanner;

public class BinaryMaxpHeapCheck {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int ar[] = new int[size];
        for (int i = 0; i < size; i++)
            ar[i] = input.nextInt();
        if (checkMaxHeap(ar, size))
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static boolean checkMaxHeap(int ar[], int size) {
        int lastInternalNode = (size - 2) / 2;
        for (int i = 0; i <= lastInternalNode; i++) {
            if (ar[i] < ar[2 * i + 1])
                return false;
            if (2 * i + 2 < size && ar[i] < ar[2 * i + 2])
                return false;
        }
        return true;
    }
}
