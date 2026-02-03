package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element; // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next; // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
            next = n;
        }
    } // ----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null; // head node of the list (or null if empty)

    /**
     * Number of nodes in the list
     */
    private int size = 0; // number of nodes in the list

    public SinglyLinkedList() {
    } // constructs an initially empty list

    // @Override
    public int size() {
        // TODO
        return size;
        // return 0;
    }

    // @Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Index: " + position + ", Size: " + size);
        }
        Node<E> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public void add(int position, E e) {
        // TODO
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Index: " + position + ", Size: " + size);
        }
        if (position == 0) {
            addFirst(e);
        } else if (position == size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<E>(e, current.next);
            current.next = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(E e) {
        // TODO
        head = new Node<E>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        Node<E> newNode = new Node<E>(e, null);
        Node<E> last = head;
        if (last == null) {
            head = newNode;
        } else {
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newNode);
        }
        size++;
    }

    @Override
    public E remove(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Index: " + position + ", Size: " + size);
        }
        if (position == 0) {
            return removeFirst();
        } else if (position == size - 1) {
            return removeLast();
        } else {
            Node<E> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            E element = current.next.getElement();
            current.next = current.next.next;
            size--;
            return element;
        }
    }

    @Override
    public E removeFirst() {
        // TODO
        if (isEmpty()) {
            return null;
        }
        E element = head.getElement();
        head = head.getNext();
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        // TODO
        if (isEmpty()) {
            return null;
        }
        if (head.getNext() == null) {
            E element = head.getElement();
            head = null;
            size--;
            return element;
        }
        Node<E> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        E element = current.getNext().getElement();
        current.setNext(null);
        size--;
        return element;
    }

    // @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    // Q8: sortedMerge
    @SuppressWarnings("unchecked")
    public void sortedMerge(SinglyLinkedList<E> otherList) {
        Node<E> dummy = new Node<>(null, null);
        Node<E> tail = dummy;
        Node<E> a = this.head;
        Node<E> b = otherList.head;

        while (a != null && b != null) {
            Comparable<? super E> aElem = (Comparable<? super E>) a.getElement();
            if (aElem.compareTo(b.getElement()) <= 0) {
                tail.setNext(a);
                a = a.getNext();
            } else {
                tail.setNext(b);
                b = b.getNext();
            }
            tail = tail.getNext();
        }

        if (a != null) {
            tail.setNext(a);
        } else {
            tail.setNext(b);
        }

        this.head = dummy.getNext();
    }

    // Q9: reverse
    public void reverse() {
        Node<E> prev = null;
        Node<E> current = head;
        Node<E> next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    // Q10 clone
    @Override
    public SinglyLinkedList<E> clone() {
        SinglyLinkedList<E> clonedList = new SinglyLinkedList<>();
        if (head == null) {
            return clonedList;
        }

        Node<E> current = head;
        clonedList.head = new Node<>(current.getElement(), null);
        Node<E> clonedCurrent = clonedList.head;
        current = current.getNext();

        while (current != null) {
            Node<E> newNode = new Node<>(current.getElement(), null);
            clonedCurrent.setNext(newNode);
            clonedCurrent = newNode;
            current = current.getNext();
        }

        clonedList.size = this.size;
        return clonedList;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        // LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        // ll.removeLast();
        // ll.removeFirst();
        // System.out.println("I accept your apology");
        // ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

        ll.reverse();
        System.out.println(ll);

        // l1 = {2, 6, 20, 24};
        // l2 = {1, 3, 5, 8, 12, 19, 25};
        // test sortedMerge
        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<Integer>();
        l1.addLast(2);
        l1.addLast(6);
        l1.addLast(20);
        l1.addLast(24);
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<Integer>();
        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(5);
        l2.addLast(8);
        l2.addLast(12);
        l2.addLast(19);
        l2.addLast(25);
        System.out.println("l1: " + l1);
        System.out.println("l2: " + l2);
        l1.sortedMerge(l2);
        System.out.println("After merge, l1: " + l1);

    }
}
