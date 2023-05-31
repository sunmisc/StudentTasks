package zelvalea.tasks.acc_lab3;import java.util.Objects;import java.util.Optional;import java.util.StringJoiner;import java.util.function.Consumer;public class QDequeLinked<E> {    private int size;    private Node<E> head, tail;    // O(1)    // x    // y<-x    public void push_front(E e) {        Objects.requireNonNull(e);        Node<E> h = head,                newNode = new Node<>(e, null, h);        head = newNode;        if (h == null) {            tail = newNode;        } else {            h.prev = newNode;        }        size++;    }    public void push_back(E e) {        Objects.requireNonNull(e);        Node<E> t = tail,                newNode = new Node<>(e, t,null);        tail = newNode;        if (t == null) {            head = newNode;        } else {            t.next = newNode;        }        size++;    }    public Optional<E> pop_front() {        final Node<E> h = head, next = h.next;        final E element = h.item;        h.item = null; h.next = null;        head = next;        if (next == null) {            tail = null;        } else {            next.prev = null;        }        size--;        return Optional.ofNullable(element);    }    public Optional<E> pop_back() {        final Node<E> t = tail, prev = t.prev;        final E element = t.item;        t.item = null; t.prev = null;        tail = prev;        if (prev == null) {            head = null;        } else {            prev.next = null;        }        size--;        return Optional.ofNullable(element);    }    public Optional<E> front() {        Node<E> h = head;        return h == null                ? Optional.empty()                : Optional.ofNullable(h.item);    }    public Optional<E> back() {        Node<E> t = tail;        return t == null                ? Optional.empty()                : Optional.ofNullable(t.item);    }    public int size() { return size; }    public boolean isEmpty() {        return size < 1;    }    public void clear() {        head = tail = null;        size = 0;    }    public void exit() {        System.out.println("bye");        System.exit(1);    }    // O(n)    public void forEach(Consumer<E> consumer) {        for (Node<E> h = head; h != null; h = h.next) {            consumer.accept(h.item);        }    }    public void print() {        StringJoiner joiner = new StringJoiner(", ", "[", "]");        forEach(x -> joiner.add(x.toString()));        System.out.println(joiner);    }    public void print0() {        forEach(System.out::println);    }    // O(n/2)    public Optional<E> find(int index) {        int sz = size;        Objects.checkIndex(index, sz);        Node<E> x; int i;        if (index < (sz >> 1)) {            for (i = 0, x = head; i < index; i++)                x = x.next;        } else {            for (i = sz - 1, x = tail; i > index; i--)                x = x.prev;        }        return x == null ? Optional.empty()                : Optional.ofNullable(x.item);    }    public int test() {        Node<E> p = head; boolean odd = true;        int S = 0;        while (p != null && odd) {            int i = (int) p.item;            odd = i % 2 != 0;            if (odd) {                S = S + i;            }            odd = !odd;            p = p.next;        }        return S;    }    private static class Node<E> {        E item;        Node<E> prev, next;        Node(E element, Node<E> prev, Node<E> next) {            this.item = element;            this.next = next;            this.prev = prev;        }    }}