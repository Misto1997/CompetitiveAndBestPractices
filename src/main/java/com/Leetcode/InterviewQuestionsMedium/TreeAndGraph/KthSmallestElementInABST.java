package com.Leetcode.InterviewQuestionsMedium.TreeAndGraph;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list, k);
        return list.get(k - 1);

    }

    private void inOrder(TreeNode root, List<Integer> list, int k) {
        if (root == null || list.size() == k)
            return;
        inOrder(root.left, list, k);
        list.add(root.val);
        inOrder(root.right, list, k);
    }

}
