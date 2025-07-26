import java.util.ArrayList;
import java.util.List;

import SinglyLinkedList.SinglyLinkedList;
import SinglyLinkedList.SinglyLinkedListNode;
import ArrayBackedQueue.ArrayQueue;

public class App {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        // Test enqueue and size
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.size()); // Expected: 3
    
        // Test dequeue
        System.out.println(queue.dequeue()); // Expected: 10
        System.out.println(queue.dequeue()); // Expected: 20
        System.out.println(queue.size());    // Expected: 1
    
        // Test wrap-around behavior
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i + 100); // This should trigger resize at some point
        }
    
        System.out.println(queue.size()); // Expected: 11 (1 remaining + 10 added)
        System.out.println(queue.dequeue()); // Expected: 30 (last from before loop)
    
        // Print all remaining items
        while (queue.size() > 0) {
            System.out.print(queue.dequeue() + " "); // Should print 100 to 109
        }
    
        System.out.println();
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
