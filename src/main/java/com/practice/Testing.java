package com.practice;


import java.util.ArrayList;
import java.util.List;

class A extends Thread {


}

public class Testing extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            try{
                Thread.sleep(10);
            }catch(Exception e){
                System.out.println("ee");
            }
            System.out.println(i+" ");
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println(3/2.0);
    }

    public static int numDecodings(String s) {

        String [] ar=s.split("");
        if(ar[0]=="0")
            return 0;
        int[] dp=new int[ar.length];
        for(int i=0;i<ar.length;i++){
            int num=0;
            if(ar[i].equals("0")){
                if(ar[i-1].equals("O"))
                    return 0;
                else{
                    int val=Integer.parseInt(ar[i-1]+ar[i]);
                    if(val<21){
                        if(i>1)
                            num=dp[i-2];
                        else
                            num=dp[i-1];
                    }else
                        return 0;

                }
            }else{
                num=1;
                if(i>0){
                    int val=Integer.parseInt(ar[i-1]+ar[i]);
                    if(val==0 || val>26 || ar[i-1].equals("0"))
                        num=dp[i-1];
                    else
                        num+=dp[i-1];
                }

            }

            dp[i]=num;
        }
        return dp[ar.length-1];
    }



}

