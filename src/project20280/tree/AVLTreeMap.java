package project20280.tree;

import project20280.interfaces.Entry;
import project20280.interfaces.Position;

import java.util.Comparator;

/**
 * An implementation of a sorted map using an AVL tree.
 */
public class AVLTreeMap<K, V> extends TreeMap<K, V> {

    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public AVLTreeMap() {
        super();
    }

    /**
     * Constructs an empty map using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the map
     */
    public AVLTreeMap(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Returns the height of the given tree position.
     */
    protected int height(Position<Entry<K, V>> p) {
        return (p == null ? -1 : ((BalanceableBinaryTree.BSTNode<Entry<K, V>>) p).getAux());
    }

    /**
     * Recomputes the height of the given position based on its children's heights.
     */
    protected void recomputeHeight(Position<Entry<K, V>> p) {
        int newHeight = 1 + Math.max(height(left(p)), height(right(p)));
        ((BalanceableBinaryTree.BSTNode<Entry<K, V>>) p).setAux(newHeight);
    }

    /**
     * Returns whether a position has balance factor between -1 and 1 inclusive.
     */
    protected boolean isBalanced(Position<Entry<K, V>> p) {
        int balanceFactor = height(left(p)) - height(right(p));
        return balanceFactor >= -1 && balanceFactor <= 1;
    }

    /**
     * Returns a child of p with height no smaller than that of the other child.
     */
    protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
        int hl = height(left(p));
        int hr = height(right(p));
        if (hl > hr)
            return left(p);
        if (hl < hr)
            return right(p);

        if (p == root())
            return left(p);
        if (p == left(parent(p)))
            return left(p);
        else
            return right(p);
    }

    /**
     * Utility used to rebalance after an insert or removal operation.
     */
    protected void rebalance(Position<Entry<K, V>> p) {
        while (p != null) {
            if (!isBalanced(p)) {
                Position<Entry<K, V>> x = tallerChild(p);
                Position<Entry<K, V>> y = tallerChild(x);
                p = restructure(y);
                recomputeHeight(left(p));
                recomputeHeight(right(p));
            }
            recomputeHeight(p);
            p = parent(p);
        }
    }

    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        rebalance(p);
    }

    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        rebalance(p);
    }

    /**
     * Ensure that current tree structure is valid AVL (for debug use only).
     */
    private boolean sanityCheck() {
        for (Position<Entry<K, V>> p : tree.positions()) {
            if (isInternal(p)) {
                if (p.getElement() == null)
                    System.out.println("VIOLATION: Internal node has null entry");
                else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
                    System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
                    return false;
                }
            }
        }
        return true;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<Entry<K, V>> btp = new BinaryTreePrinter<>(this.tree);
        return btp.print();
    }

    public static void main(String[] args) {
        AVLTreeMap<Integer, Integer> avl = new AVLTreeMap<>();

        Integer[] arr = new Integer[] { 5, 3, 10, 2, 4, 7, 11, 1, 6, 9, 12, 8 };

        for (Integer i : arr) {
            if (i != null)
                avl.put(i, i);
            System.out.println("root " + avl.root());
        }
        System.out.println(avl.toBinaryTreeString());

        avl.remove(5);
        System.out.println(avl.toBinaryTreeString());
    }
}