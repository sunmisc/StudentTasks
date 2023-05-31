package zelvalea.tasks.aac;

import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class ArrayQueue<E> {
    private final E[] elements;
    private int head, tail;
    private int count;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.elements = (E[]) new Object[capacity];
    }


    public static void main(String[] args) {
        int n = 10;
        ArrayQueue<Integer> queue = new ArrayQueue<>(n);
        for (int i = 0; i < n; ++i)
            queue.push(i);
        System.out.println(queue);
        queue.push(12); // exp

    }
    public E peek() {
        return elements[head];
    }

    public void clear() { // O(n)
        int i = head, end = tail;
        E[] es = elements;
        for (int to = (i < end) ? end : es.length; ; i = 0, to = end) {
            for (; i < to; i++)
                es[i] = null;
            if (to == end)
                break;
        }
        tail = head;
        count = 0;
    }
    public void push(E e) {
        if (size() == elements.length)
            throw new IllegalStateException("Queue full");
        else {
            final E[] es = elements;
            es[tail] = e;

            if (++tail == es.length)
                tail = 0;
            count++;
        }
    }

    public E pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        final E[] es = this.elements;
        E e = es[head];
        es[head] = null;
        if (++head == es.length)
            head = 0;
        count--;
        return e;
    }
    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    public void forEach(Consumer<? super E> action) {
        if (size() > 0) {
            final E[] es = this.elements;
            for (int i = head, end = tail,
                 to = (i < end) ? end : es.length; ;
                 i = 0, to = end) {
                for (; i < to; i++)
                    action.accept(es[i]);
                if (to == end)
                    break;
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(
                ", ", "[", "]");
        forEach(e -> joiner.add(e.toString()));
        return joiner.toString();
    }
}
