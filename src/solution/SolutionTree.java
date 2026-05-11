package solution;

import entity.TreeNode;

import java.util.*;

public class SolutionTree {

    // 遍历  中左右  左中右  左右中
    private void preTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            list.add(-1);
            return;
        }

        list.add(root.val);
        preTraversal(root.left, list);
        preTraversal(root.right, list);
    }
    private void postTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            list.add(-1);
            return;
        }
        list.add(root.val);
        postTraversal(root.right, list);
        postTraversal(root.left, list);
    }
    private void inTraversal(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inTraversal(root.left, list);
        list.add(root.val);
        inTraversal(root.right, list);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inTraversal(root, list);
        return list;
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int n = queue.size();
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            while (n > 0) {
                TreeNode pollTreeNode = queue.poll();
                list.add(pollTreeNode.val);
                if (pollTreeNode.left != null) {
                    queue.offer(pollTreeNode.left);
                }
                if (pollTreeNode.right != null) {
                    queue.offer(pollTreeNode.right);
                }
                n--;
            }
            result.add(list);
            n = queue.size();
        }
        return result;
    }


    // 后序遍历的变形 //深度 = 节点数
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterTree(root);
        return max;
    }
    public int diameterTree(TreeNode root) {
        if (root == null)
            return 0;
        int left = diameterTree(root.left);
        int right = diameterTree(root.right);
        max = Math.max(left + right, max);
        return Math.max(left, right) + 1;
    }

    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return maxSum;
    }
    private int maxSum(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(maxSum(root.left), 0);
        int right = Math.max(maxSum(root.right), 0);
        maxSum = Math.max(maxSum, root.val + left + right);
        return root.val + Math.max(left, right);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return false;
        return symmetric(root.left, root.right);
    }
    private boolean symmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        return left.val == right.val
                && symmetric(left.left, right.right)
                && symmetric(left.right, right.left);
    }



    // 二叉搜索树与中序遍历
    public TreeNode sortedArrayToBST(int[] nums) {
        return arrayToBST(nums, 0, nums.length);
    }
    private TreeNode arrayToBST(int[] nums, int start, int end) {
        if (start == end)
            return null;
        if (start == end - 1) {
            return new TreeNode(nums[start]);
        }
        if (start == end - 2) {
            TreeNode treeNode = new TreeNode(nums[start]);
            treeNode.right = new TreeNode(nums[start + 1]);
            return treeNode;
        }
        TreeNode treeNode = new TreeNode();
        int mid = (end - start) / 2 + start;
        treeNode.val = nums[mid];
        treeNode.left = arrayToBST(nums, start, mid);
        treeNode.right = arrayToBST(nums, mid + 1, end);
        return treeNode;
    }

    public boolean isValidBST(TreeNode root) {
        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean validBST(TreeNode root, long min, long max) {
        if (root == null)
            return true;
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return validBST(root.left, min, root.val) && validBST(root.right, root.val, max);
    }

    public int kthSmallest(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null || !list.isEmpty()) {
            while (curr != null) {
                list.add(curr);
                curr = curr.left;
            }
            TreeNode tmp = list.remove(list.size() - 1);
            k--;
            if (k == 0)
                return tmp.val;
            curr = tmp.right;
        }
        return -1;
    }
    public int kSmallest(TreeNode root, int[] k) {
        if (root == null) {
            return -1;
        }
        int left = kSmallest(root.left, k);
        if(left != -1){
            return left;
        }
        k[0]--;
        if (k[0] == 0) {
            return root.val;
        }
        return kSmallest(root.right, k);
    }


    // 中左右遍历
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideViewOrder(root, list, 1);
        return list;
    }
    private void rightSideViewOrder(TreeNode root, List<Integer> list, int height) {
        if (root == null)
            return;
        if (height - 1 == list.size())
            list.add(root.val);
        rightSideViewOrder(root.right, list, height + 1);
        rightSideViewOrder(root.left, list, height + 1);
    }

    // ？？？moris算法
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode tmp = curr.left;
                while (tmp.right != null)
                    tmp = tmp.right;
                tmp.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    // 前 + 中建树
    private Map<Integer, Integer> inorderSet;
    private int preIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderSet = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderSet.put(inorder[i], i);
        return buildTreeOrder(preorder, 0, inorder.length - 1);
    }
    private TreeNode buildTreeOrder(int[] preorder, int inStartIndex, int inEndIndex) {
        if (inStartIndex > inEndIndex)
            return null;
        int mid = inorderSet.get(preorder[preIndex]);
        TreeNode node = new TreeNode(preorder[preIndex]);
        preIndex++;
        node.left = buildTreeOrder(preorder, inStartIndex, mid - 1);
        node.right = buildTreeOrder(preorder, mid + 1, inEndIndex);
        return node;
    }

    // 前缀和  currentSum - targetSum
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return pathTargetSum(root, 0L, targetSum, map);
    }
    public int pathTargetSum(TreeNode root, Long currentSum, int targetSum, Map<Long, Integer> map) {
        if (root == null)
            return 0;
        currentSum += root.val;
        int count = map.getOrDefault(currentSum - targetSum, 0);
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        count += pathTargetSum(root.left, currentSum, targetSum, map);
        count += pathTargetSum(root.right, currentSum, targetSum, map);
        map.put(currentSum, map.get(currentSum) - 1);
        return count;
    }

    // 最近祖先 ？？？返回值为Node
    TreeNode treeNode;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestAncestor(root, p, q);
        return treeNode;
    }
    public boolean lowestAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return false;
        boolean flag = root == p || root == q;
        boolean flag1 = lowestAncestor(root.left, p, q);
        boolean flag2 = lowestAncestor(root.right, p, q);
        if (flag && (flag1 || flag2) && treeNode == null) {
            treeNode = root;
            return true;
        }
        if (flag1 && flag2) {
            treeNode = root;
            return true;
        }
        return flag1 || flag2 || flag;
    }





}
