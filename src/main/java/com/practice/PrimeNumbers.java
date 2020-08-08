package com.practice;

import java.util.Scanner;

//Sieve of Eratosthenes
public class PrimeNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        printPrimeNumbersUptoN(x);
    }

    private static void printPrimeNumbersUptoN(int x) {
        boolean ar[] = new boolean[x + 1];
        for (int i = 0; i < x; i++)
            ar[i] = true;
        int sqrt = (int) Math.sqrt(x);
        for (int i = 2; i <= sqrt; i++) {
            if (ar[i] == true) {
                for (int j = i * i; j <= x; j += i)
                    ar[j] = false;
            }
        }
        for (int i = 2; i <= x; i++)
            if (ar[i] == true)
                System.out.println(i);
    }

}
