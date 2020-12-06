package com.Leetcode.InterviewQuestionsMedium.TreeAndGraph;

public class PopulatingNextRightPointersInEachNode {
    public TreeNode connect(TreeNode root) {
        if (root == null)
            return null;
        builder(root);
        return root;
    }

    private void builder(TreeNode root) {
        if (root.left == null)
            return;
        root.left.next = root.right;
        if (root.next != null)
            root.right.next = root.next.left;
        builder(root.left);
        builder(root.right);
    }
}
