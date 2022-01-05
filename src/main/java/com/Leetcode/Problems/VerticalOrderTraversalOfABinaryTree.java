package com.Leetcode.Problems;

import java.util.LinkedList;
import java.util.*;

class BinaryTreeVerticalOrder {
    int val;
    BinaryTreeVerticalOrder left;
    BinaryTreeVerticalOrder right;

    BinaryTreeVerticalOrder(int val) {
        this.val = val;
    }
}

class BTreeVerHeight {
    int height;
    BinaryTreeVerticalOrder node;

    BTreeVerHeight(int height, BinaryTreeVerticalOrder node) {
        this.height = height;
        this.node = node;
    }

}

public class VerticalOrderTraversalOfABinaryTree {
    static BinaryTreeVerticalOrder root;

    public static void main(String[] args) {
        root = new BinaryTreeVerticalOrder(1);
        root.left = new BinaryTreeVerticalOrder(3);
        root.right = new BinaryTreeVerticalOrder(2);
        root.right.right = new BinaryTreeVerticalOrder(4);
        root.right.left = new BinaryTreeVerticalOrder(5);
        root.right.left.left = new BinaryTreeVerticalOrder(6);
        root.right.left.right = new BinaryTreeVerticalOrder(7);
        root.right.left.left.left = new BinaryTreeVerticalOrder(8);
        root.right.left.left.right = new BinaryTreeVerticalOrder(9);
        System.out.println(verticalTraversal(root));
    }

    private static List<List<Integer>> verticalTraversal(BinaryTreeVerticalOrder root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<BTreeVerHeight> queue = new LinkedList<>();
        queue.add(new BTreeVerHeight(0, root));
        int size = 1;
        while (!queue.isEmpty()) {
            Map<Integer, List<Integer>> subMap = new TreeMap<>();
            for (int i = 0; i < size; i++) {
                BTreeVerHeight temp = queue.poll();
                int height = temp.height;
                if (!subMap.containsKey(height)) {
                    subMap.put(height, new ArrayList<>());
                }
                subMap.get(height).add(temp.node.val);
                if (temp.node.left != null)
                    queue.add(new BTreeVerHeight(height - 1, temp.node.left));
                if (temp.node.right != null)
                    queue.add(new BTreeVerHeight(height + 1, temp.node.right));
            }
            subMap.forEach((key, val) -> Collections.sort(val));
            for (int key : subMap.keySet()) {
                if (!map.containsKey(key))
                    map.put(key, new ArrayList<>());
                map.get(key).addAll(subMap.get(key));
            }
            size = queue.size();
        }

        return new ArrayList<>(map.values());
    }
}
