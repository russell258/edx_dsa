package OOP;

public class LinkedList<E> {


    private Node<E> head;

    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }else{
            Node<E> newNode = new Node<>(data, null);
            if (index == 0) {
                newNode.setNext(head);
                head = newNode;
            } else {
                Node<E> current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.getNext();
                }
                newNode.setNext(current.getNext());
                current.setNext(newNode);
            }
            size++;
        }
    }

    public E get(int index) {
        if (index<0 || index >=size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }else{
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    public boolean contains(Object data) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if ((current.getData() == null && data == null) || (current.getData() != null && current.getData().equals(data))) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int indexOf(Object data) {
        Node<E> current = head;
        // data null dk if should return -1 or throw exception or return the index
        for (int i = 0; i < size; i++) {
            if ((current.getData() == null && data == null) || (current.getData() != null && current.getData().equals(data))) {
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } else {
            E removedData;
            if (index == 0) {
                removedData = head.getData();
                head = head.getNext();
            } else {
                Node<E> current = head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.getNext();
                }
                removedData = current.getNext().getData();
                current.setNext(current.getNext().getNext());
            }
            size--;
            return removedData;
        }
    }

    public boolean remove(Object data) {
        Node<E> current = head;
        Node<E> previous = null;
        for (int i = 0; i < size; i++) {
            if ((current.getData() == null && data == null) || (current.getData() != null && current.getData().equals(data))) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    public E set(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.setData(data);
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = head;
        while (current != null) {
            if (current.getData() == null) {
                sb.append("null");
            } else {
            sb.append(current.getData());
            }
            current = current.getNext();
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        return true;
    }

    public void add(E data) {
        add(size, data);
    }

    public int size() {
        return size;
    }

        private class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        private E getData() {
            return data;
        }

        private E setData(E data) {
            E oldData = this.data;
            this.data = data;
            return oldData;
        }

        private Node<E> getNext() {
            return next;
        }

        private void setNext(Node<E> next) {
            this.next = next;
        }
    }

}
