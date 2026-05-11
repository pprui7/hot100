package solutionTest;

import entity.TreeNode;
import org.junit.Test;
import solution.SolutionTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SolutionTreeTest {
    public SolutionTree solutionTree;

    public TreeNode buildTree(Integer[] array) {
        if (array == null || array.length == 0 || array[0] == null) {
            return null;
        }

        // 创建根节点
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < array.length) {
            TreeNode current = queue.poll();

            // 处理左子节点
            if (i < array.length && array[i] != null) {
                current.left = new TreeNode(array[i]);
                queue.offer(current.left);
            }
            i++;

            // 处理右子节点
            if (i < array.length && array[i] != null) {
                current.right = new TreeNode(array[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }
    public List<Integer> levelOrderIncludeNull(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                result.add(null);
            } else {
                result.add(node.val);
                // 注意：即使子节点为 null，也要入队
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        // 可选：去掉末尾连续的 null（根据需求）
        while (result.size() > 0 && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }

        return result;
    }

    @Test
    public void isValidBSTTest(){
        SolutionTree solutionTree = new SolutionTree();
        Integer[] array = {5,4,6,null,null,3,7};
        TreeNode treeNode = buildTree(array);
        System.out.println(solutionTree.isValidBST(treeNode));
    }
    @Test
    public void flattenTest(){
        SolutionTree solutionTree = new SolutionTree();
        Integer[] array = {1,2,null,3};
        TreeNode treeNode = buildTree(array);
        solutionTree.flatten(treeNode);
    }
    @Test
    public void buildTreeTest(){
        SolutionTree solutionTree = new SolutionTree();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode treeNode = solutionTree.buildTree(preorder, inorder);
        System.out.println(levelOrderIncludeNull(treeNode));
    }
    @Test
    public void pathSumTest(){
        SolutionTree solutionTree = new SolutionTree();
        Integer[] array = {1,0,1,1,2,0,-1,0,1,-1,0,-1,0,1,0};
        TreeNode treeNode = buildTree(array);
        System.out.println(solutionTree.pathSum(treeNode, 2));
    }

    @Test
    public void lowestCommonAncestorTest(){
        SolutionTree solutionTree = new SolutionTree();
        Integer[] array = {3,5,1,6,2,0,8,null,null,7,4};
        TreeNode treeNode = buildTree(array);
        System.out.println(solutionTree.lowestCommonAncestor(treeNode, treeNode.left, treeNode.left.right.right).val);
    }

}
