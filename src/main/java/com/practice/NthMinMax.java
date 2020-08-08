package com.practice;
import java.util.Scanner;

public class NthMinMax {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int ar[] = new int[size];
        for (int i = 0; i < size; i++)
            ar[i] = sc.nextInt();
        int nthVal = sc.nextInt();
        System.out.println("Nth minimum Number=" + nthVal(ar, 0, ar.length - 1, nthVal));
    }

    private static int partition(int[] ar, int l,
                                 int r) {
        int x = ar[r], i = l;
        for (int j = l; j <= r - 1; j++) {
            if (ar[j] <= x) {
                int temp = ar[i];
                ar[i] = ar[j];
                ar[j] = temp;
                i++;
            }
        }
        int temp = ar[i];
        ar[i] = ar[r];
        ar[r] = temp;
        return i;
    }

    private static int nthVal(int[] ar, int l,
                              int r, int k) {
        if (k > 0 && k <= r - l + 1) {
            int pos = partition(ar, l, r);
            if (pos - l == k - 1)
                return ar[pos];
            if (pos - l > k - 1)
                return nthVal(ar, l, pos - 1, k);
            else
                return nthVal(ar, pos + 1, r, k - pos + l - 1);
        }
        return Integer.MAX_VALUE;
    }

}