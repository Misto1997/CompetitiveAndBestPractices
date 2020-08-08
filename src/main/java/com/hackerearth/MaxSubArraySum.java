package com.hackerearth;

import java.util.Scanner;

public class MaxSubArraySum {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int ar[] = new int[size];
        for (int i = 0; i < size; i++)
            ar[i] = input.nextInt();
        System.out.println(maxSubArraySum(ar, size));
    }

    static int maxSubArraySum(int ar[], int size) {

        int result = 0, maxTillIthIndex = 0;

        for (int i = 0; i < size; i++) {
            maxTillIthIndex = maxTillIthIndex + ar[i];
            if (maxTillIthIndex < 0)
                maxTillIthIndex = 0;

            else if (result < maxTillIthIndex)
                result = maxTillIthIndex;
        }
        return result;
    }
}
