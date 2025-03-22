package com.umiitkose;


public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        root.left.left = new TreeNode(4);
        System.out.println(new Main().maxDepth(root));
    }

    /**
     * Definition for a binary tree node.
     */

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int leftLevels = maxDepth(root.left);
        int rightLevels = maxDepth(root.right);
        return 1 + Math.max(leftLevels, rightLevels);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
