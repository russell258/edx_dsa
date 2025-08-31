package BST;

import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        root = addNode(root, data);
        size++;
    }

    private BSTNode<T> addNode(BSTNode<T> root, T data) {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        if (root == null) {
            root = new BSTNode<>(data);
            return root;
        }
        if (data.compareTo(root.getData()) < 0) {
            // go left
            root.setLeft(addNode(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) {
            // go right
            root.setRight(addNode(root.getRight(), data));
        } else{
            // this means data is equal to root data
            size--;
            return root; // do nothing for duplicates
        }
        return root;
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
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
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
                T originalData = root.getData();
                BSTNode<T> successor = findSuccessor(root.getRight());
                root.setData(successor.getData());
                root.setRight(removeNode(root.getRight(), successor.getData(), new RemovedData<T>()));
                removed.data = originalData;
            }
        }else{
            // Data not found in tree.
            throw new NoSuchElementException("Data not found in tree");
        }
        return root;
    }

    private static class RemovedData<T> {
        T data;
    }

    private BSTNode<T> findSuccessor(BSTNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
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