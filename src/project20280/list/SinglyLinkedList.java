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
        Node<E> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        E element = current.getElement();
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

    }
}
