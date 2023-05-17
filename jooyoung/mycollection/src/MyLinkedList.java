
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author downey
 * @param <E>
 *
 */
public class MyLinkedList<E> implements List<E> {

    /**
     * Node is identical to ListNode from the example, but parameterized with T
     *
     * @author downey
     *
     */
    private class Node {
        public E cargo;
        public Node next;

        public Node(E cargo) {
            this.cargo = cargo;
            this.next = null;
        }
        public Node(E cargo, Node next) {
            this.cargo = cargo;
            this.next = next;
        }
        public String toString() {
            return "Node(" + cargo.toString() + ")";
        }
    }

    private int size;            // keeps track of the number of elements
    private Node head;           // reference to the first node

    /**
     *
     */
    public MyLinkedList() {
        head = null;
        size = 0;
    }
    public MyLinkedList(Node node){
        this.head = node;
        this.size = 1;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // run a few simple tests
        List<Integer> mll = new MyLinkedList<Integer>();
        mll.add(1);
        mll.add(2);
        mll.add(3);
        mll.add(2, 10);
        System.out.println("mll = " + mll);
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());

        mll.remove(2);
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
    }

    @Override
    public boolean add(E element) {
        if (element == null) throw new NullPointerException();
        if (this.head == null){
            this.head = new Node(element);
            size++;
            return true;
        }
        Node last = head;
        for(; last.next != null;){
            last = last.next;
        }

        Node newNode = new Node(element);
        head.next = newNode;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        // no need to check bounds; getNode does it.
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (element == null) throw new NullPointerException();
        if (index == 0){
            Node newNode = new Node(element);
            newNode.next = head;
            head = newNode;
        }else {
            Node beforeNode = null;
            Node nowNode = head;
            for (int i = 0; i < this.size; i++) {
                if (i == index) {
                    Node newNode = new Node(element);
                    newNode.next = nowNode;
                    beforeNode.next = newNode;
                    break;
                }
                beforeNode = nowNode;
                nowNode = nowNode.next;
            }
        }
        this.size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean flag = true;
        for (E element: collection) {
            flag &= add(element);
        }
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object obj: collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public E get(int index) {
        Node node = getNode(index);
        return node.cargo;
    }

    /** Returns the node at the given index.
     * @param index
     * @return
     */
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int i=0; i<index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public int indexOf(Object target) {
        Node nowNode = head;
        for(int i = 0; nowNode.next != null; nowNode = nowNode.next, i++){
            if (nowNode.cargo.equals(target)) return i;
        }
        return -1;
    }

    /** Checks whether an element of the array is the target.
     *
     * Handles the special case that the target is null.
     *
     * @param target
     * @param object
     */
    private boolean equals(Object target, Object element) {
        if (target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        @SuppressWarnings("unchecked")
        E[] array = (E[]) toArray();
        return Arrays.asList(array).iterator();
    }

    @Override
    public int lastIndexOf(Object target) {
        Node node = head;
        int index = -1;
        for (int i=0; i<size; i++) {
            if (equals(target, node.cargo)) {
                index = i;
            }
            node = node.next;
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public boolean remove(Object obj) {
        if (head.cargo.equals(obj)) {
            this.head = null;
            size--;
            return true;
        }
        Node beforeNode = null;
        Node nowNode = head;
        if (head.cargo.equals(obj)) {
            for (; nowNode.next != null; nowNode = nowNode.next) {
                if (nowNode.cargo.equals(obj)) {
                    beforeNode.next = nowNode.next;
                    size--;
                    return true;
                }
                beforeNode = nowNode;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        E element = get(index);
        if (index == 0) {
            head = head.next;
        } else {
            Node node = getNode(index-1);
            node.next = node.next.next;
        }
        size--;
        return element;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;
        for (Object obj: collection) {
            flag &= remove(obj);
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int index, E element) {
        Node node = getNode(index);
        E old = node.cargo;
        node.cargo = element;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        // TODO: classify this and improve it.
        int i = 0;
        MyLinkedList<E> list = new MyLinkedList<E>();
        for (Node node=head; node != null; node = node.next) {
            if (i >= fromIndex && i <= toIndex) {
                list.add(node.cargo);
            }
            i++;
        }
        return list;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (Node node=head; node != null; node = node.next) {
            // System.out.println(node);
            array[i] = node.cargo;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(){
        String s = "";
        if (isEmpty()) return s;
        Node nowNode = head;
        for(; nowNode.next != null; nowNode = nowNode.next){
            s += nowNode.toString();
        }
        return s;
    }
}