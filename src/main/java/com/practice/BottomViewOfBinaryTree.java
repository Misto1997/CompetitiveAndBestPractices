package com.practice;

import java.util.*;

class BinaryTree {
    int val;
    int height;
    BinaryTree left;
    BinaryTree right;

    BinaryTree(int val) {
        this.val = val;
    }
}

public class BottomViewOfBinaryTree {
    static BinaryTree root;

    public static void main(String[] args) {
        root = new BinaryTree(1);
        root.left = new BinaryTree(3);
        root.right = new BinaryTree(2);
        System.out.println(bottomView(root));
    }

    public static ArrayList<Integer> bottomView(BinaryTree root) {
        if (root == null)
            return new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<BinaryTree> q = new LinkedList<>();
        root.height = 0;
        q.add(root);
        while (!q.isEmpty()) {
            BinaryTree temp = q.remove();
            int hd = temp.height;
            map.put(hd, temp.val);
            if (temp.left != null) {
                temp.left.height = hd - 1;
                q.add(temp.left);
            }
            if (temp.right != null) {
                temp.right.height = hd + 1;
                q.add(temp.right);
            }
        }

        return new ArrayList<>(map.values());
    }

}
