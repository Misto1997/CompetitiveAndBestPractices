package com.Leetcode.Problems;

class BTree {
    int val;
    BTree left;
    BTree right;

    BTree(int val) {
        this.val = val;
    }
}

public class FlattenBinaryTreeToLinkedList {
    static BTree root;
    static BTree prev = null;

    public static void main(String[] args) {
        root = new BTree(1);
        root.left = new BTree(2);
        root.left.left = new BTree(3);
        root.left.right = new BTree(4);
        root.right = new BTree(5);
        root.right.right = new BTree(6);
        flatten(root);
        BTree temp = root;
        while (temp != null) {
            System.out.println(temp.val + "->");
            temp = temp.right;
        }
    }

    private static void flatten(BTree root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

}
