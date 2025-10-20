package BST;

import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BSTb<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This should be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return true if the parameter is contained within the tree, false otherwise.
     */
    public boolean contains(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (root == null) {
            return false;
        }
        if (data.compareTo(root.getData()) == 0) {
            return true;
        } else if (data.compareTo(root.getData()) < 0) {
            // go left
            return containsNode(root.getLeft(), data);
        } else {
            // go right
            return containsNode(root.getRight(), data);
        }
    }

    private boolean containsNode(BSTNode<T> root, T data) {
        if (root == null) {
            return false;
        }
        if (data.compareTo(root.getData()) == 0) {
            return true;
        } else if (data.compareTo(root.getData()) < 0) {
            // go left
            return containsNode(root.getLeft(), data);
        } else {
            // go right
            return containsNode(root.getRight(), data);
        }
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove. You may assume that data is never null.
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        RemovedData<T> removed = new RemovedData<>();
        root = removeNode(root,data,removed);
        if (removed.data == null) throw new NoSuchElementException("Data not found in tree");
        size--;
        return removed.data;
    }

    private static class RemovedData<T> {
        T data;
    }

    private BSTNode<T> removeNode(BSTNode<T> root, T data, RemovedData<T> removed) {
        if (root == null) throw new NoSuchElementException("Data not found in tree");
        if (data.compareTo(root.getData()) < 0) {
            // go left
            root.setLeft(removeNode(root.getLeft(), data, removed));
        } else if (data.compareTo(root.getData()) > 0) {
            // go right
            root.setRight(removeNode(root.getRight(), data, removed));
        } else if (data.compareTo(root.getData()) == 0) {
            // found the node to remove
            removed.data = root.getData();
            if (root.getLeft() == null && root.getRight() == null) {
                // case 1: no children
                return null;
            } else if (root.getLeft() == null && root.getRight() != null) {
                // case 2: one child (right)
                return root.getRight();
            } else if (root.getRight() == null  && root.getLeft() != null) {
                // case 2: one child (left)
                return root.getLeft();
            } else {
                // case 3: two children
                T successorData = findSuccessor(root.getRight());
                root.setData(successorData);
                root.setRight(removeNode(root.getRight(), successorData, new RemovedData<>()));
            }
        }else{
            // Data not found in tree.
            throw new NoSuchElementException("Data not found in tree");
        }
        return root;
    }

    private T findSuccessor(BSTNode<T> node) {
        BSTNode<T> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getData();
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}