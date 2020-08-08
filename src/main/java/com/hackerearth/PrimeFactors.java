package com.hackerearth;

import java.util.Scanner;

//Prime Factorization using Sieve O(log n) for multiple queries
public class PrimeFactors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int max = 100001;
        int ar[] = new int[max];
        sievePreCompute(ar, max);
        printPrimeFactorsOfX(x, ar);
    }

    private static void sievePreCompute(int[] ar, int max) {
        int sqrt = (int) Math.sqrt(max);
        for (int i = 0; i < max; i++)
            ar[i] = i;
        for (int i = 4; i < max; i += 2)
            ar[i] = 2;
        for (int i = 3; i <= sqrt; i++) {
            if (ar[i] == i) {
                for (int j = i * i; j < max; j += i) {
                    if (ar[j] == j)
                        ar[j] = i;
                }
            }
        }
    }

    private static void printPrimeFactorsOfX(int x, int ar[]) {
        while (x != 1) {
            System.out.println(ar[x]);
            x /= ar[x];
        }
    }

}
