package com.practice;

class Node {
    int data, height;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
        this.height = 1;
    }
}

public class AVLTree {
    Node root;

    //Main method
    public static void main(String[] args) {
        //Creation
        AVLTree tree = new AVLTree();

        //Insertions
        tree.root = tree.insertNode(tree.root, 10);
        tree.root = tree.insertNode(tree.root, 20);
        tree.root = tree.insertNode(tree.root, 30);
        tree.root = tree.insertNode(tree.root, 40);
        tree.root = tree.insertNode(tree.root, 50);
        tree.root = tree.insertNode(tree.root, 25);
        tree.root = tree.insertNode(tree.root, 38);
        tree.root = tree.insertNode(tree.root, 39);
        tree.preOrder(tree.root);
        System.out.println();

        //Deletion
        tree.root = tree.deleteNode(tree.root, 30);
        tree.preOrder(tree.root);
        System.out.println();
    }


    //to delete a node from AVL tree
    private Node deleteNode(Node root, int i) {
        if (root == null)
            return root;
        root = findNodeToBeDeleted(root, i);
        if (root == null)
            return root;
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balanceFactor = findDifferenceLeftRightNode(root);
        return doRequiredRotationAfterDeletion(balanceFactor, root);

    }

    //to find Node to be deleted
    private Node findNodeToBeDeleted(Node root, int i) {
        if (i < root.data)
            root.left = deleteNode(root.left, i);
        else if (i > root.data)
            root.right = deleteNode(root.right, i);
        else {
            if (root.left == null)
                root = root.right;
            else if (root.right == null)
                root = root.left;
            else {
                root.data = minValNodeFromSuccessor(root.right);
                root.right = deleteNode(root.right, root.data);
            }
        }
        return root;
    }

    //rotation after deletion
    private Node doRequiredRotationAfterDeletion(int balanceFactor, Node root) {
        //left left situation
        if (balanceFactor > 1 && findDifferenceLeftRightNode(root.left) >= 0)
            return rightRotation(root);

        //left right situation
        if (balanceFactor > 1 && findDifferenceLeftRightNode(root.left) < 0) {
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }

        //right right situation
        if (balanceFactor < -1 && findDifferenceLeftRightNode(root.right) <= 0)
            return leftRotation(root);

        //right left situation
        if (balanceFactor < -1 && findDifferenceLeftRightNode(root.right) > 0) {
            root.right = rightRotation(root.right);
            return leftRotation(root);
        }

        return root;
    }

    //to calculate Successor i.e leftMost of right node
    private int minValNodeFromSuccessor(Node node) {
        Node temp = node;
        while (temp.left != null)
            temp = temp.left;
        return temp.data;
    }

    //preorder traversal
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    //insertions of new node
    public Node insertNode(Node root, int i) {
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

    private Node doRequiredRotationAfterInsertion(int balanceFactor, Node root, int i) {
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
    private Node leftRotation(Node node) {
        Node temp = node;
        node = node.right;
        temp.right = node.left;
        node.left = temp;
        node.left.height = 1 + Math.max(height(node.left.left), height(node.left.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }

    //right rotation
    private Node rightRotation(Node node) {
        Node temp = node;
        node = node.left;
        temp.left = node.right;
        node.right = temp;
        node.right.height = 1 + Math.max(height(node.right.left), height(node.right.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }

    //find the selfBalancing factor
    private int findDifferenceLeftRightNode(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    //height of node
    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }
}
