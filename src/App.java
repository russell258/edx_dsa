import java.util.ArrayList;
import java.util.List;

import SinglyLinkedList.SinglyLinkedList;
import SinglyLinkedList.SinglyLinkedListNode;

public class App {
    public static void main(String[] args) {
        SinglyLinkedListNode<Integer> head = new SinglyLinkedListNode(1);
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        // System.out.println(list.size());
        list.addToFront(4);
        list.addToFront(12);
        // System.out.println(list.getHead().getData());
        // System.out.println(list.getTail().getData());
        list.removeFromFront();
        System.out.println("TAIL: " + list.getTail().getData());
        System.out.println("HEAD: " + list.getHead().getData());
    }   

    public static void recursiveMethod(int n) {
        if (n <= 0) {
            return;
        } else {
            System.out.println(n);
            recursiveMethod(n - 2);
            System.out.println(n - 1);
        }
    }

    // Recursive method to remove duplicates for Node / LinkedList
    public static <T> Node<T> rRemove(Node<T> curr){
        if (curr == null) {
            return null;
        }

        curr.next = rRemove(curr.next);
        if (curr.next!=null && curr.next.data.equals(curr.data)) {
            return curr.next;
        }
        return curr;
    }

    // adding duplicate node adjacent to each node in the LinkedList
    public static <T> Node<T> aAdd(Node<T> curr) {
    if (curr == null) {
        return null;
    } else {
        Node<T> temp = new Node<>(curr.data);
        temp.next = curr;
        curr.next = aAdd(curr.next);
        return temp;
    }
}


}
