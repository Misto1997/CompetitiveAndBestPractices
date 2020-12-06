package com.Leetcode.InterviewQuestionsMedium.TreeAndGraph;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        if (preorder.length == 0)
            return null;
        return builder(0, 0, preorder.length - 1, preorder, inorder, map);

    }

    private TreeNode builder(int preOrderFirstElement, int inOrderStart, int inOrderEnd, int[] preorder, int[] inorder, Map<Integer, Integer> map) {
        if (preOrderFirstElement > preorder.length - 1 || inOrderStart > inOrderEnd)
            return null;
        TreeNode root = new TreeNode(preorder[preOrderFirstElement]);
        int index = map.get(root.val);
        root.left = builder(preOrderFirstElement + 1, inOrderStart, index - 1, preorder, inorder, map);
        root.right = builder(preOrderFirstElement + index - inOrderStart + 1, index + 1, inOrderEnd, preorder, inorder, map);

        return root;
    }
}
