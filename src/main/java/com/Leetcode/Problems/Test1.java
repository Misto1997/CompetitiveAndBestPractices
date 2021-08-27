package com.Leetcode.Problems;

class DoublyListNode {
    String url;
    DoublyListNode forward;
    DoublyListNode back;

    DoublyListNode(String url) {
        this.url = url;
    }

}

public class Test1 {

    public static void main(String[] args) {
        DoublyListNode root = new DoublyListNode("a");
        DoublyListNode node = new DoublyListNode("b");
        root.forward = node;
        node.back = root;
        root = node;
        System.out.println(root.url);
        System.out.println(root.back.url);

    }
}
