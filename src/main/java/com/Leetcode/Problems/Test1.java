package com.Leetcode.Problems;

class DoublyListNode {
    String url;
    DoublyListNode forward;
    DoublyListNode back;

    DoublyListNode(String url) {
        this.url = url;
    }

}

class A{
    A(){
        System.out.println("A");
    }
     void set(){
        System.out.println("A set");
    }
}

class B extends A{
    B(){
        System.out.println("b");
    }
    void set(){
        super.set();
        System.out.println("b set");
    }
    void preset(){
        System.out.println("B preset");
    }
}

public class Test1 {

    public static void main(String[] args) {
        B ob=new B();
        ob.set();
        ob.preset();
        try{}
        finally{

        }


    }
}
