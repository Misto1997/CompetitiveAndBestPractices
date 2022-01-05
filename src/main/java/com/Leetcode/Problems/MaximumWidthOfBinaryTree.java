package com.Leetcode.Problems;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTreeMaxWidth {
    int val;
    BinaryTreeMaxWidth left;
    BinaryTreeMaxWidth right;

    BinaryTreeMaxWidth(int val) {
        this.val = val;
    }
}

class BinaryTreeMaxWidthImpl {
    int index;
    BinaryTreeMaxWidth node;

    BinaryTreeMaxWidthImpl(int index, BinaryTreeMaxWidth node) {
        this.index = index;
        this.node = node;
    }
}


public class MaximumWidthOfBinaryTree {

    static BinaryTreeMaxWidth root;

    public static void main(String[] args) {
        root = new BinaryTreeMaxWidth(1);
        root.left = new BinaryTreeMaxWidth(3);
        root.right = new BinaryTreeMaxWidth(2);
        root.right.right = new BinaryTreeMaxWidth(4);
        root.right.left = new BinaryTreeMaxWidth(5);
        root.right.left.left = new BinaryTreeMaxWidth(6);
        root.right.left.right = new BinaryTreeMaxWidth(7);
        root.right.left.left.left = new BinaryTreeMaxWidth(8);
        root.right.left.left.right = new BinaryTreeMaxWidth(9);
        System.out.println(widthOfBinaryTree(root));
    }

    private static int widthOfBinaryTree(BinaryTreeMaxWidth root) {
        int max = 0;
        Queue<BinaryTreeMaxWidthImpl> queue = new LinkedList<>();
        queue.add(new BinaryTreeMaxWidthImpl(0, root));
        while (!queue.isEmpty()) {
            int size = queue.size();
            int left = queue.peek().index, right = queue.peek().index;
            for (int i = 0; i < size; i++) {
                BinaryTreeMaxWidthImpl temp = queue.poll();
                int index = temp.index;
                if (i == size - 1)
                    right = index;
                if (temp.node.left != null) {
                    queue.add(new BinaryTreeMaxWidthImpl(2 * index + 1, temp.node.left));
                }
                if (temp.node.right != null) {
                    queue.add(new BinaryTreeMaxWidthImpl(2 * index + 2, temp.node.right));
                }
            }
            max = Math.max(max, right - left + 1);
        }
        return max;

    }
}
