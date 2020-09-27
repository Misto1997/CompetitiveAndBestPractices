package com.Leetcode.InterviewQuestionsEasy.Tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> listOfList = new ArrayList<>();
        if (root != null) {
            listOfList.add(Arrays.asList(root.val));
            queue.add(root);
        }
        while (true) {
            int size = queue.size();
            if (size == 0)
                break;
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.remove();
                size--;
                if (node.left != null) {
                    list.add(node.left.val);
                    queue.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right.val);
                    queue.add(node.right);
                }
            }
            if (list.size() != 0)
                listOfList.add(list);
        }
        return listOfList;
    }
}
