package com.Leetcode.InterviewQuestionsEasy.Tree;

public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isAMirror(root, root);

    }

    private boolean isAMirror(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null)
            return true;
        if (tree1 != null && tree2 != null && tree1.val == tree2.val)
            return (isAMirror(tree1.left, tree2.right)
                    && isAMirror(tree1.right, tree2.left));
        return false;
    }
}
