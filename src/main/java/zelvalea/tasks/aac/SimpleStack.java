package zelvalea.tasks.aac;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class SimpleStack<E> {
    private E[] elements;
    private int top; // not negative

    @SuppressWarnings("unchecked")
    public SimpleStack(int initialCapacity) {
        this.elements = (E[]) new Object[Math.max(2, initialCapacity)];
    }

    public SimpleStack() {
        this(10);
    }
    public static void main(String[] args) {
        final int n = 10;
        SimpleStack<Integer> stack = new SimpleStack<>(n);

        for (int i = 0; i < n*2; ++i)
            stack.push(i);

        System.out.println(stack);

        for (int i = 0; i < n; ++i)
            stack.pop();
        System.out.println(stack);
    }
    public E peek() {
        int h = top - 1;
        if (h < 0)
            throw new EmptyStackException();
        return elements[h];
    }

    public void forEach(Consumer<? super E> action) {
        for (int i = 0, h = top; i < h; ++i)
            action.accept(elements[i]);
    }
    public boolean isEmpty() {
        return top == 0;
    }
    public int size() {
        return top;
    }

    public void clear() { // O(n)
        for (int i = 0, h = top; i < h; ++i)
            elements[i] = null; // help gc
        top = 0;
    }
    public void push(E e) {
        E[] es = elements;
        int h = top++, n = elements.length;
        if (h == n)
            es = elements = Arrays.copyOf(es,
                    n + (n >> 1)); // 50%
        es[h] = e;
    }

    public E pop() {
        int h = top - 1;
        if (h < 0) throw new NoSuchElementException();
        E e = elements[h];
        elements[h] = null;
        top = h;
        return e;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(
                ", ", "[", "]");
        forEach(e -> joiner.add(e.toString()));
        return joiner.toString();
    }
}
