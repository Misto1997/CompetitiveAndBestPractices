package com.Leetcode.InterviewQuestionsMedium.TreeAndGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> listOfList = new ArrayList<>();
        queue.add(root);
        int flip = 0;
        while (true) {
            int size = queue.size();
            if (size == 0)
                break;
            if (flip == 0) flip = 1;
            else flip = 0;
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                size--;
                TreeNode node = queue.remove();
                if (flip == 0) {
                    list.add(0, node.val);
                } else {
                    list.add(node.val);
                }
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            if (list.size() != 0)
                listOfList.add(list);
        }
        return listOfList;
    }
}
