package com.Leetcode.InterviewQuestionsEasy.Tree;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 1;
        return Math.max(getMaxDepth(root.left, depth), getMaxDepth(root.right, depth));
    }

    private int getMaxDepth(TreeNode root, int depth) {
        if (root == null)
            return depth;
        return Math.max(getMaxDepth(root.left, depth + 1), getMaxDepth(root.right, depth + 1));
    }
}
