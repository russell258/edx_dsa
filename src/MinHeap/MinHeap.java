package MinHeap;

import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size + 1 == backingArray.length) {
            T[] newArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 1; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }
        backingArray[size + 1] = data;
        size++;
        int index = size;
        while (index > 1) {
            int parentIndex = index / 2; //parent = n/2
            if (backingArray[index].compareTo(backingArray[parentIndex]) < 0) {
                //smaller than parent, swap
                T temp = backingArray[index];
                backingArray[index] = backingArray[parentIndex];
                backingArray[parentIndex] = temp;
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        int index = 1;
        while (index <= size / 2) { //while index has at least one child
            int leftChildIndex = index * 2;
            int rightChildIndex = index * 2 + 1;
            int smallerChildIndex;
            if (rightChildIndex <= size) { //has right child
                if (backingArray[leftChildIndex].compareTo(backingArray[rightChildIndex]) < 0) {
                    smallerChildIndex = leftChildIndex;
                } else {
                    smallerChildIndex = rightChildIndex;
                }
            } else { //only has left child
                smallerChildIndex = leftChildIndex;
            }
            if (backingArray[index].compareTo(backingArray[smallerChildIndex]) > 0) {
                //parent is greater than smaller child, swap
                T temp = backingArray[index];
                backingArray[index] = backingArray[smallerChildIndex];
                backingArray[smallerChildIndex] = temp;
                index = smallerChildIndex;
            } else {
                break;
            }
        }
        return min;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
