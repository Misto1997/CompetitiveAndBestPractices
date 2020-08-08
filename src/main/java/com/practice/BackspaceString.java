package com.practice;

public class BackspaceString {
    public static void main(String[] args) {

String S="gtc#uz#";
String T="gtcm##uz#";
        int sizeS=S.length();
        int sizeT=T.length();
        int count1=0,count2=0;
        int s1HashVal=0, s2HashVal=0;
        for(int i=0;i<sizeS;i++){
            char s=S.charAt(i);
            if(s == '#'){
                count1=Math.max(count1-(int)S.charAt(s1HashVal),0);
                s1HashVal=Math.max(s1HashVal-1,0);
            }
            else{
                count1+=(int)s;
                s1HashVal=i;
            }
        }
        for(int i=0;i<sizeT;i++){
            char t=T.charAt(i);
            if(t == '#'){
                count2=Math.max(count2-(int)T.charAt(s2HashVal),0);
                s2HashVal=Math.max(s2HashVal-1,0);
            }
            else{
                count2+=(int)t;
                s2HashVal=i;
            }
        }
       /* if(count1 !=count2)
            return false;
        return true;*/
    }
}
