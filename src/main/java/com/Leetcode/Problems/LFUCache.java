package com.Leetcode.Problems;

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int val;
    int count;
    Node next;
    Node prev;

    Node(int key, int val, int count) {
        this.key = key;
        this.val = val;
        this.count = count;
    }

}

public class LFUCache {
    int currCap;
    int cap;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    public LFUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.get(key) == null)
            return -1;
        Node node = map.get(key);
        adjustNode(node);
        return node.val;
    }

    private void adjustNode(Node node) {
        node.count++;
        if (tail != head && head != node) {
            Node temp = node.prev;
            while (temp != head && node.count >= temp.count) {
                temp = temp.prev;
            }
            if (tail == node) {
                tail = tail.prev;
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            if (temp == head && node.count >= head.count) {
                node.prev = null;
                node.next = head;
                head.prev = node;
                head = node;
            } else {
                if (temp.next != null) {
                    node.next = temp.next;
                    temp.next.prev = node;
                }
                node.prev = temp;
                temp.next = node;
                if (temp == tail)
                    tail = node;
            }
        }
    }

    public void put(int key, int value) {
        if (cap == 0)
            return;
        if (map.get(key) != null) {
            Node node = map.get(key);
            node.val = value;
            adjustNode(node);
        } else {
            if (currCap >= cap) {
                currCap--;
                map.remove(tail.key);
                if (tail != head) {
                    tail = tail.prev;
                    tail.next.prev = null;
                    tail.next = null;
                } else {
                    tail = null;
                    head = null;
                }
            }
            if (head == null) {
                head = new Node(key, value, 1);
                tail = head;
                map.put(key, head);
            } else {
                Node node = new Node(key, value, 0);
                map.put(key, node);
                tail.next = node;
                node.prev = tail;
                tail = node;
                adjustNode(node);
            }
            currCap++;
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        cache.put(5, 5);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));

    }

}
