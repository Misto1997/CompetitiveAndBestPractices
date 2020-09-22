package com.Leetcode.InterviewQuestionsEasy.LinkedList;

import java.util.Scanner;

public class MergeTwoSortedLists {

    static Node mergeLists(Node a, Node b) {

        if (a == null)
            return b;
        else if (b == null)
            return a;

        if (a.data <= b.data) {
            a.next = mergeLists(a.next, b);
            return a;
        } else {
            b.next = mergeLists(a, b.next);
            return b;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SinglyLinkedList list1 = new SinglyLinkedList();
        int list1NodesCount = scanner.nextInt();
        for (int i = 0; i < list1NodesCount; i++) {
            int data = scanner.nextInt();
            list1.insertNode(data);
        }

        SinglyLinkedList list2 = new SinglyLinkedList();

        int list2NodesCount = scanner.nextInt();

        for (int i = 0; i < list2NodesCount; i++) {
            int data = scanner.nextInt();
            list2.insertNode(data);
        }

        Node mergedList = mergeLists(list1.head, list2.head);

        while (mergedList != null) {
            System.out.print(mergedList.data + "->");
            mergedList = mergedList.next;
        }
        System.out.println("null");
        scanner.close();
    }

    static class Node {
        public int data;
        public Node next;

        public Node(int Data) {
            this.data = Data;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public Node head;
        public Node tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            Node node = new Node(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

}

