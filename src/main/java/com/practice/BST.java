package com.practice;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BST {
    TreeNode root;

    BST() {
        root = null;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        //inseration
        bst.insertUtil(5);
        bst.insertUtil(2);
        bst.insertUtil(7);
        bst.insertUtil(3);
        bst.insertUtil(1);
        bst.insertUtil(6);

        //dfs
        bst.dfsUtil();
        System.out.println();

        //bfs
        bst.bfsUtil();
        System.out.println();

        //inorder successor
        System.out.println(bst.inorderSuccessorUtil(5));
        System.out.println(bst.inorderSuccessorUtil(6));
        System.out.println(bst.inorderSuccessorUtil(1));
        System.out.println(bst.inorderSuccessorUtil(3));
        System.out.println(bst.inorderSuccessorUtil(7));
        System.out.println(bst.inorderSuccessorUtil(11));

        //inorder predecessor
        System.out.println(bst.inorderPredecessorUtil(5));
        System.out.println(bst.inorderPredecessorUtil(6));
        System.out.println(bst.inorderPredecessorUtil(1));
        System.out.println(bst.inorderPredecessorUtil(3));
        System.out.println(bst.inorderPredecessorUtil(7));
        System.out.println(bst.inorderPredecessorUtil(11));
    }

    // Inorder Predecessor
    private int inorderPredecessorUtil(int val) {
        TreeNode node = inorderPredecessor(val, root, null);
        if (node == null)
            return -1;
        else
            return node.val;
    }

    private TreeNode inorderPredecessor(int val, TreeNode root, TreeNode parent) {
        if (root != null && root.val == val) {
            if (root.left == null) {
                return parent;
            } else {
                return minValForPredecessor(root.left);
            }
        } else if (root != null && root.val < val) {
            parent = root;
            return inorderPredecessor(val, root.right, parent);
        } else if (root != null && root.val > val) {
            return inorderPredecessor(val, root.left, parent);
        }
        return null;
    }

    private TreeNode minValForPredecessor(TreeNode root) {
        TreeNode temp = root;
        while (temp.right != null)
            temp = temp.right;
        return temp;
    }


    // Inorder Successor
    private int inorderSuccessorUtil(int val) {
        TreeNode node = inorderSuccessor(val, root, null);
        if (node == null)
            return -1;
        else
            return node.val;
    }

    private TreeNode inorderSuccessor(int val, TreeNode root, TreeNode parent) {
        if (root != null && root.val == val) {
            if (root.right == null) {
                return parent;
            } else {
                return minValForSuccessor(root.right);
            }
        } else if (root != null && root.val < val) {
            return inorderSuccessor(val, root.right, parent);
        } else if (root != null && root.val > val) {
            parent = root;
            return inorderSuccessor(val, root.left, parent);
        }
        return null;
    }

    private TreeNode minValForSuccessor(TreeNode root) {
        TreeNode temp = root;
        while (temp.left != null)
            temp = temp.left;
        return temp;
    }


    //bfs
    private void bfsUtil() {
        bfsInorder(root);
    }

    private void bfsInorder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.isEmpty() == false) {
            TreeNode temp = queue.poll();
            System.out.print(temp.val + " ");
            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
    }


    //dfs inorder
    private void dfsUtil() {
        dfs(root);
    }

    private void dfs(TreeNode root) {
        if (root != null) {
            dfs(root.left);
            System.out.print(root.val + " ");
            dfs(root.right);
        }
    }

    // insertion in bst
    private void insertUtil(int val) {
        root = insertNode(val, root);
    }

    private TreeNode insertNode(int val, TreeNode root) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        if (root.val < val)
            root.right = insertNode(val, root.right);
        else
            root.left = insertNode(val, root.left);
        return root;
    }
}
