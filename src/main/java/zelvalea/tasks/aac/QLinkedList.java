package zelvalea.tasks.aac;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class QLinkedList<E> {

    private Node<E> head, tail;
    private int size;

    public static class Node<E> {
        private final E item;
        private Node<E> prev, next;
        Node(E value) {
            this.item = value;
        }
        public E item() { return item; }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    // Добавляет новый узел со значением x после узла, на который указывает p
    public void insert_after(Node<E> p, E x) {
        Node<E> newNode = new Node<>(x);
        if (p == null) {
            newNode.next = head;
            if (head != null)
                head.prev = newNode;
            else
                tail = newNode;
            head = newNode;
        } else {
            newNode.next = p.next;
            newNode.prev = p;
            if (p.next != null)
                p.next.prev = newNode;
            else
                tail = newNode;
            p.next = newNode;
        }
        size++;
    }

    public void insert_before(Node<E> p, E x) {
        Node<E> newNode = new Node<>(x);
        if (p == null) {
            newNode.prev = tail;
            if (tail != null)
                tail.next = newNode;
            else
                head = newNode;
            tail = newNode;
        } else {
            newNode.prev = p.prev;
            newNode.next = p;
            if (p.prev != null)
                p.prev.next = newNode;
            else
                head = newNode;
            p.prev = newNode;
        }
        size++;
    }

    // Удаляет из списка узел, следующий за тем, на который указывает p
    public void erase_after(Node<E> p) {
        if (p != null && p.next != null) {
            if (p.next == tail)
                tail = p;
            p.next = p.next.next;
            if (p.next != null)
                p.next.prev = p;
            size--;
        }
    }

    public void erase(Node<E> p1, Node<E> p2) {
        if (p1 == null)
            head = p2.next;
        else
            p1.next = p2.next;
        if (p2 == tail)
            tail = p1;
        else
            p2.next.prev = p1;
        size -= countNodes(p1, p2);
    }

    private int countNodes(Node<E> p1, Node<E> p2) {
        int count = 0;
        Node<E> current = p1;
        while (current != p2) {
            count++;
            current = current.next;
        }
        return count + 1;
    }

    public void erase(Node<E> p) {
        Objects.requireNonNull(p);
        if (p == head) {
            head = head.next;
            if (head != null)
                head.prev = null;
            else
                tail = null;
        } else if (p == tail) {
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
            else
                head = null;
        } else {
            p.prev.next = p.next;
            p.next.prev = p.prev;
        }
        size--;
    }

    public void merge(QLinkedList<E> list2) {
        if (!list2.isEmpty()) {
            if (isEmpty()) {
                head = list2.head;
                tail = list2.tail;
                size = list2.size();
            } else {
                tail.next = list2.head;
                list2.head.prev = tail;
                tail = list2.tail;
                size += list2.size();
            }
        }
    }

    public void unique() {
        Node<E> current = head;
        while (current != null && current.next != null) {
            if (current.item.equals(current.next.item))
                erase_after(current);
            else
                current = current.next;
        }
    }

    public Node<E> find(E item) {
        for (Node<E> x = head; x != null; x = x.next)
            if (Objects.equals(x.item, item))
                return x;
        return null;
    }
    public void forEach(Consumer<? super E> action) {
        for (Node<E> x = head; x != null; x = x.next)
            action.accept(x.item);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(
                ", ", "[", "]");
        forEach(x -> joiner.add(x.toString()));
        return joiner.toString();
    }
}
