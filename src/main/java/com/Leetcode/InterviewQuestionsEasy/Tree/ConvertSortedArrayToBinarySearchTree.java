package com.Leetcode.InterviewQuestionsEasy.Tree;


import java.util.Scanner;

class Node {
    int data, height;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
        this.height = 1;
    }

    Node() {
    }
}

public class ConvertSortedArrayToBinarySearchTree {
    //Node root;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ar[] = new int[n];
        for (int i = 0; i < n; i++)
            ar[i] = sc.nextInt();
        sortedArrayToBST(ar);
        /*ConvertSortedArrayToBinarySearchTree tree = new ConvertSortedArrayToBinarySearchTree();
        for (int i : ar)
            tree.root = tree.insertNode(tree.root, i);
        preOrder(tree.root);*/
    }

    private static void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void sortedArrayToBST(int[] nums) {
        Node root = new Node();
        for (int i : nums)
            root = insertNode(root, i);
        preOrder(root);
    }

    private static Node insertNode(Node root, int i) {
        if (root == null)
            return new Node(i);
        else if (i < root.data)
            root.left = insertNode(root.left, i);
        else if (i > root.data)
            root.right = insertNode(root.right, i);

        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balanceFactor = findDifferenceLeftRightNode(root);
        return doRequiredRotationAfterInsertion(balanceFactor, root, i);

    }

    private static Node doRequiredRotationAfterInsertion(int balanceFactor, Node root, int i) {
        //left left situation
        if (balanceFactor > 1 && i < root.left.data) {
            return rightRotation(root);
        }
        //left right situation
        else if (balanceFactor > 1 && i > root.left.data) {
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }
        //right right rotation
        else if (balanceFactor < -1 && i > root.right.data) {
            return leftRotation(root);
        }
        //right left rotation
        else if (balanceFactor < -1 && i < root.right.data) {
            root.right = rightRotation(root.right);
            return leftRotation(root);
        }
        return root;
    }

    //left rotation
    private static Node leftRotation(Node node) {
        Node temp = node;
        node = node.right;
        temp.right = node.left;
        node.left = temp;
        node.left.height = 1 + Math.max(height(node.left.left), height(node.left.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }

    //right rotation
    private static Node rightRotation(Node node) {
        Node temp = node;
        node = node.left;
        temp.left = node.right;
        node.right = temp;
        node.right.height = 1 + Math.max(height(node.right.left), height(node.right.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }

    private static int findDifferenceLeftRightNode(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private static int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }
}
