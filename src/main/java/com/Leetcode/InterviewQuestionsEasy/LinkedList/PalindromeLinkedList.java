package com.Leetcode.InterviewQuestionsEasy.LinkedList;

import java.util.Scanner;

public class PalindromeLinkedList {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SinglyLinkedList list = new SinglyLinkedList();
        int listNodesCount = scanner.nextInt();
        for (int i = 0; i < listNodesCount; i++) {
            int data = scanner.nextInt();
            list.insertNode(data);
        }
        System.out.println(isPalindrome(list.head));
        scanner.close();
    }

    private static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;
        Node halfway = getHalfWay(head);
        halfway = reverseHalfwayList(halfway);
        return compareBothList(head, halfway);
    }

    private static boolean compareBothList(Node head, Node halfway) {
        while (halfway != null) {
            if (head.data != halfway.data)
                return false;
            head = head.next;
            halfway = halfway.next;
        }
        return true;
    }


    private static Node reverseHalfwayList(Node halfway) {
        Node prevNode = null;
        Node nextNode = null;
        Node currentNode = halfway;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = prevNode;
            prevNode = currentNode;
            currentNode = nextNode;
        }
        return prevNode;
    }

    private static Node getHalfWay(Node head) {
        Node fastNode = head.next;
        Node slowNode = head;
        while (fastNode.next != null) {
            slowNode = slowNode.next;
            if (fastNode.next.next == null)
                break;
            fastNode = fastNode.next.next;
        }
        return slowNode.next;
    }

    private static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
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


