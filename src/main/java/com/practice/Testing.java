package com.practice;

public class Testing {
    public static void main(String[] args) {
        int N=2;
        int [][] trust={{1,2}};
        System.out.println(findJudge(N,trust));
    }

    public static int findJudge(int N, int[][] trust) {
        int ar[]=new int[N+1];
        int size=trust.length;
        for(int i=0;i<size;i++){
            ar[trust[i][1]]+=1;
        }
        for(int i=1;i<=N;i++)
            if(ar[i]==N-1)
                return ar[i];
        return -1;
    }
}
