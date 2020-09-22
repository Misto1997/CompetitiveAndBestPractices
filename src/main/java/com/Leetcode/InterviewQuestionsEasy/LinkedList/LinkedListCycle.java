package com.Leetcode.InterviewQuestionsEasy.LinkedList;


import java.util.Scanner;

public class LinkedListCycle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LinkedList.head = new LinkedList.Node(1);
        LinkedList.head.next = new LinkedList.Node(2);
        LinkedList.head.next.next = new LinkedList.Node(3);
        LinkedList.head.next.next.next = new LinkedList.Node(4);
        LinkedList.head.next.next.next.next = new LinkedList.Node(5);
        LinkedList.head.next.next.next.next.next = LinkedList.head.next;
        System.out.println(hasCycle(LinkedList.head));
        input.close();
    }

    private static boolean hasCycle(LinkedList.Node head) {
        if (head == null)
            return false;
        LinkedList.Node slow = head;
        LinkedList.Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}

