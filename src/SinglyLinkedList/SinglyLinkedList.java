package SinglyLinkedList;

import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;

    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null ) throw new IllegalArgumentException("data cannot be null");
        if (size==0){
            head = new SinglyLinkedListNode<T>(data);
            tail = head;
        }else{
            head = new SinglyLinkedListNode<T>(data,head);
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null ) throw new IllegalArgumentException("data cannot be null");
        if (size==0){
            head = new SinglyLinkedListNode<T>(data);
            tail = head;
        }else{
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data);
            tail.setNext(newNode); // This sets the current tail's next to the new node
            tail = newNode;        // Now tail points to the new node
        }
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        T data;
        if (size==0) return null;
        if (size==1){
            data = head.getData();
            head = null;
            tail = null;
        }else{
            data = head.getData();
            head = head.getNext();
        }
        size--;
        return data;
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        T data;
        if (size == 0) throw new NoSuchElementException("List is empty");
        if (size == 1){
            data = tail.getData();
            head = null;
            tail = null;
        }else{
            SinglyLinkedListNode<T> current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            data = tail.getData();
            tail = current; // Update tail to the second last node
            tail.setNext(null); // Set the new tail's next to null
        }

        size--;
        return data;
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}