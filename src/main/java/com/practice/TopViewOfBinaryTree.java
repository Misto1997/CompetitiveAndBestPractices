package com.practice;

import java.util.*;

class BinaryTreeTopView {
    int val;
    int height;
    BinaryTreeTopView left;
    BinaryTreeTopView right;

    BinaryTreeTopView(int val) {
        this.val = val;
    }
}

public class TopViewOfBinaryTree {
    static BinaryTreeTopView root;

    public static void main(String[] args) {
        root = new BinaryTreeTopView(1);
        root.left = new BinaryTreeTopView(3);
        root.right = new BinaryTreeTopView(2);
        root.right.right = new BinaryTreeTopView(4);
        root.right.left = new BinaryTreeTopView(5);
        root.right.left.left = new BinaryTreeTopView(6);
        root.right.left.right = new BinaryTreeTopView(7);
        root.right.left.left.left = new BinaryTreeTopView(8);
        root.right.left.left.right = new BinaryTreeTopView(9);
        System.out.println(topView(root));
    }

    public static ArrayList<Integer> topView(BinaryTreeTopView root) {
        if (root == null)
            return new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<BinaryTreeTopView> q = new LinkedList<>();
        root.height = 0;
        q.add(root);
        while (!q.isEmpty()) {
            BinaryTreeTopView temp = q.remove();
            int hd = temp.height;
            if (map.get(hd) == null)
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
