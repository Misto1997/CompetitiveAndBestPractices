package com.Leetcode.Problems;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Tree {
    TreeNode root;
    TreeNode newRoot;
    TreeNode temp;

    public void insert(int val) {
        root = insertNodes(root, val);
    }

    public TreeNode insertNodes(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        } else if (val > root.val)
            root.right = insertNodes(root.right, val);
        else
            root.left = insertNodes(root.left, val);
        return root;
    }

    public void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public void inOrderForRightSet(TreeNode root) {
        if (root == null)
            return;
        inOrderForRightSet(root.left);
        if (newRoot == null) {
            newRoot = root;
        } else {
            temp.right = root;
        }
        temp = root;
        root.left = null;
        inOrderForRightSet(root.right);
    }
}
