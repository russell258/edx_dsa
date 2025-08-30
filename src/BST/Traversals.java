package BST;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // Base case: if the root is null, return an empty list
        if (root == null) {
            return new ArrayList<>();
        }
        // CLR: Create a list to store the traversal result
        List<T> result = new ArrayList<>();
        // Visit the root node first
        result.add(root.getData());
        // Recursively traverse the left subtree
        result.addAll(preorder(root.getLeft()));
        // Recursively traverse the right subtree
        result.addAll(preorder(root.getRight()));
        return result;
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // Base case: if the root is null, return an empty list
        if (root == null) {
            return new ArrayList<>();
        }
        // LCR: Create a list to store the traversal result
        List<T> result = new ArrayList<>();
        // Recursively traverse the left subtree
        result.addAll(inorder(root.getLeft()));
        // Visit the root node
        result.add(root.getData());
        // Recursively traverse the right subtree
        result.addAll(inorder(root.getRight()));
        return result;
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (root == null) {
            return new ArrayList<>();
        }
        // LRC: Create a list to store the traversal result
        List<T> result = new ArrayList<>();
        // Recursively traverse the left subtree
        result.addAll(postorder(root.getLeft()));
        // Recursively traverse the right subtree
        result.addAll(postorder(root.getRight()));
        // Visit the root node
        result.add(root.getData());
        return result;
    }
}
