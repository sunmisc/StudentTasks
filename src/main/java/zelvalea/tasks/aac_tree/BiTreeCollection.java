package zelvalea.tasks.aac_tree;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;

public class BiTreeCollection<E extends Comparable<? super E>> {
    private final Comparator<E> cpr;
    private Node<E> root;
    public BiTreeCollection(Comparator<E> cpr) {
        this.cpr = cpr;
    }

    public static void main(String[] args) {
        BiTreeCollection<Integer> tree = new BiTreeCollection<>(Integer::compareTo);

        tree.insert(321, true);
        tree.insert(2, true);
        tree.insert(5, true);
        tree.insert(23, true);
        tree.insert(24, true);
        tree.insert(23, true);
        tree.symmetricForEach(System.out::println);



    }

    public boolean contains(E key) {
        return getNode(key) != null;
    }

    public final Node<E> getNode(E key) {
        Objects.requireNonNull(key);
        Node<E> p = root;
        while (p != null) {
            int cmp = key.compareTo(p.value);
            if (cmp < 0)
                p = p.left;
            else if (cmp > 0)
                p = p.right;
            else
                return p;
        }
        return null;
    }

    public boolean deleteSubtree(Node<E> x) {
        Node<E> p = x.parent;

        if (p == null)
            root = null;
        else {
            if (x == p.right)
                p.right = null;
            else
                p.left = null;
        }

        return true;
    }

    public Node<E> getFirstValue(Node<E> p) {
        if (p != null) {
            while (p.left != null)
                p = p.left;
            return p;
        } else
            return null;
    }

    public Node<E> getLastValue(Node<E> p) {
        if (p != null) {
            while (p.right != null)
                p = p.right;
            return p;
        } else
            return null;
    }

    public void insert(E val, boolean balanced) {

        Node<E> r = root;

        if (r == null)
            root = new Node<>(val, null);
        else {
            for (Node<E> parent; ; ) {
                int cmp = cpr.compare(val, r.value);

                parent = r;
                boolean right = cmp > 0;
                r = right ? r.right : r.left;

                if (r == null) {
                    Node<E> newNode = new Node<>(val, parent);
                    if (right)
                        parent.right = newNode;
                    else
                        parent.left = newNode;

                    if (balanced)
                        fixAfterInsertion(newNode);
                    break;
                }
            }
        }
    }
    final E getLowerValue(E key) {
        Node<E> p = root;
        while (p != null) {
            int cmp = cpr.compare(key, p.value);
            if (cmp > 0) {
                if (p.right != null)
                    p = p.right;
                else
                    return p.value;
            } else {
                if (p.left != null) {
                    p = p.left;
                } else {
                    Node<E> parent = p.parent;
                    Node<E> ch = p;
                    while (parent != null && ch == parent.left) {
                        ch = parent;
                        parent = parent.parent;
                    }
                    return parent == null ? null : parent.value;
                }
            }
        }
        return null;
    }
    /*                 |10|
     *               /       \
     *             8          20
     *           /   \       /   \
     *          6      9          45
     *
     */
    public final Node<E> getNextNode(Node<E> r) {
        Node<E> p = r.right;

        if (p != null)
            return getFirstValue(p);
        else {
            Node<E> parent = r.parent;
            Node<E> ch = r;
            while (parent != null && ch == parent.right) {
                ch = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }
    public final Node<E> getPrevNode(Node<E> r) {
        Node<E> p = r.left;

        if (p != null)
            return getLastValue(p);
        else {
            Node<E> parent = r.parent;
            Node<E> ch = r;
            while (parent != null && ch == parent.left) {
                ch = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    private void fixAfterInsertion(Node<E> x) {
        x.color = RED;
        final Node<E> r = root;
        for (Node<E> p; x != r && (p = x.parent).color == RED; ) {
            Node<E> gp = p.parent, left;

            boolean right = p == (left = gp.left);

            Node<E> y = right ? gp.right : left;
            if (y != null && y.color == RED) {
                p.color = y.color = BLACK;
                gp.color = RED;
                x = gp;
            } else {
                if (right)
                    if (p.right == x)  rotateLeft(x = p);
                else if (p.left == x) rotateRight(x = p);

                (p = x.parent).color = BLACK;
                (gp = p.parent).color = RED;

                if (right)
                    rotateRight(gp);
                else
                    rotateLeft(gp);
            }
        }
        // assert r.color == BLACK;
        r.color = BLACK; // ensure root is black only
    }
    private void rotateLeft(Node<E> p) {
        if (p != null) {
            Node<E> pivot = p.right;
            p.right = pivot.left;
            if (pivot.left != null)
                pivot.left.parent = p;
            pivot.parent = p.parent;
            if (p.parent == null)
                root = pivot;
            else if (p.parent.left == p)
                p.parent.left = pivot;
            else
                p.parent.right = pivot;
            pivot.left = p;
            p.parent = pivot;
        }
    }

    private void rotateRight(Node<E> p) {
        if (p != null) {
            Node<E> pivot = p.left;
            p.left = pivot.right;
            if (pivot.right != null) pivot.right.parent = p;
            pivot.parent = p.parent;
            if (p.parent == null)
                root = pivot;
            else if (p.parent.right == p)
                p.parent.right = pivot;
            else
                p.parent.left = pivot;
            pivot.right = p;
            p.parent = pivot;
        }
    }
    static final boolean RED = false, BLACK = true;

    static final class Node<E> {
        final E value;
        Node<E> left, parent, right;

        boolean color = BLACK;

        Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    public void forEach(Consumer<E> consumer) {
        preOrderForeach0(root, consumer);
    }
    public void symmetricForEach(Consumer<E> consumer) {
        inOrderForeach0(root, consumer);
    }
    public void descendingForEach(Consumer<E> consumer) {
        postOrderForeach0(root, consumer);
    }
    private void preOrderForeach0(Node<E> node, Consumer<E> consumer) {
        if (node == null)
            return;
        consumer.accept(node.value);
        preOrderForeach0(node.left, consumer);
        preOrderForeach0(node.right, consumer);
    }
    private void inOrderForeach0(Node<E> node, Consumer<E> consumer) {
        if (node == null)
            return;
        inOrderForeach0(node.left, consumer);
        consumer.accept(node.value);
        inOrderForeach0(node.right, consumer);
    }
    private void postOrderForeach0(Node<E> node, Consumer<E> consumer) {
        if (node == null)
            return;
        postOrderForeach0(node.left, consumer);
        postOrderForeach0(node.right, consumer);
        consumer.accept(node.value);
    }
}
