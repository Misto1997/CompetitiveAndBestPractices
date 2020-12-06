package com.Leetcode.InterviewQuestionsMedium.TreeAndGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        return list;
    }

    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        traverse(root.left, list);
        list.add(root.val);
        traverse(root.right, list);
    }
}
