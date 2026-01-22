import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import ArrayQueue.ArrayQueue.ArrayQueue;
import SinglyLinkedList.SinglyLinkedList;
import SinglyLinkedList.SinglyLinkedListNode;

public class App {
    public static void main(String[] args) {

String funnyStr = "south, long island";
funnyStr.replace("!"," ");
funnyStr.replace("the beach","");
funnyStr.replace(", long", "");
funnyStr.replace("wal","roc");
funnyStr.toUpperCase();
System.out.println(funnyStr);
    }
    
    public static void mystery(Node cur){
        if (cur==null){
            System.out.println("CS 1332 is cool!");
            return;
        }
        if (cur.next!=null && ((String) cur.data).length()>5){
            mystery(cur.next.next);
            System.out.println(((String) cur.data).length());
        }else if (((String) cur.data).length()%2 == 0){
            System.out.println(cur.data);
            mystery(cur.next);
        }
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
