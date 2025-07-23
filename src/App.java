import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(2);
        head.next.next.next = new Node<>(2);
        head.next.next.next.next = new Node<>(4);
        head.next.next.next.next.next = new Node<>(1);
        System.out.println(head.toString());
        head = rRemove(head);
        System.out.println(head.toString());
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




}
