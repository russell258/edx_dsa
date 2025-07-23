// custom class for Node
public class Node<T> {
    T data; //stores the value
    Node<T> next; //reference to the next node

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public Node (T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", next=" + next + "]";
    }


}
