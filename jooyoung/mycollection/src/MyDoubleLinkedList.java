import java.util.Collection;

public class MyDoubleLinkedList<E> {

    private class DNode {
        public E data;
        public DNode next;
        public DNode before;

        public DNode(E data) {
            this.data = data;
            this.next = null;
            this.before = null;
        }
        public DNode(E data, DNode next, DNode before) {
            this.data = data;
            this.next = next;
            this.before = before;
        }
        public String toString() {
            return "DNode(" + data.toString() + ")";
        }
    }

    public static void main(String[] args){
        MyDoubleLinkedList<String> ll = new MyDoubleLinkedList<String>();
        ll = new MyDoubleLinkedList<String>();
        ll.addTail("김수현");
        ll.addTail("전지현");

        //when 첫번째 노드 삭제
        ll.remove(0);
        System.out.println("ll = " + ll);
    }

    private int size;
    private DNode head;
    private DNode tail;

    public MyDoubleLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public MyDoubleLinkedList(DNode node) {
        this.head = node;
        this.tail = node;
        this.size = 1;
    }

    public boolean addTail(E element){
        if (element == null) throw new NullPointerException();
        DNode newNode = new DNode(element);
        if (size == 0){
            this.head = newNode;
            this.tail = newNode;
            size++;
            return true;
        }
        newNode.before = this.tail;
        this.tail = newNode;
        newNode.before.next = this.tail;
        size++;
        return true;
    }
    public boolean addHead(E element){
        if (element == null) throw new NullPointerException();
        DNode newNode = new DNode(element);
        if (size == 0){
            this.head = newNode;
            this.tail = newNode;
            size++;
            return true;
        }
        newNode.next = this.head;
        this.head = newNode;
        newNode.next.before = this.head;
        size++;
        return true;
    }
    
    public boolean add(int index, E element){
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (element == null) throw new NullPointerException();
        if (index == 0) return addHead(element);

        DNode node = getNode(index);
        DNode newNode = new DNode(element);

        node.before.next = newNode;
        newNode.next = node;
        newNode.before = node.before;
        node.before = newNode;
        size++;
        return true;
    }
    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
    }
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }
    public E get(int index) {
        DNode node = getNode(index);
        return node.data;
    }

    private DNode getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        DNode node = head;
        for (int i=0; i<index; i++) {
            node = node.next;
        }
        return node;
    }

    public int indexOf(Object target) {
        DNode nowNode = head;
        for(int i = 0; nowNode.next != null; nowNode = nowNode.next, i++){
            if (nowNode.data.equals(target)) return i;
        }
        return -1;
    }

    private boolean equals(Object target, Object element) {
        if (target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int lastIndexOf(Object target) {
        DNode node = head;
        int index = -1;
        for (int i=this.size - 1; i <= 0; i--) {
            if (equals(target, node.data)) {
                index = i;
            }
            node = node.before;
        }
        return index;
    }
    
    public boolean remove(Object o){
        DNode toRemove = getNode(indexOf(o));
        if (toRemove.equals(this.head)){
            this.head = this.head.next;
            size--;
            return true;
        }
        if (toRemove.equals(this.tail)){
            this.tail = this.tail.before;
            size--;
            return true;
        }
        toRemove.before.next = toRemove.next;
        toRemove.next.before = toRemove.before;
        size--;
        return true;
    }
    
    public E remove(int index){
        DNode toRemove = getNode(index);
        if (index == 0){
            this.head = this.head.next;
            size--;
            return this.head.before.data;
        }
        if (index == size - 1){
            this.tail = this.tail.before;
            size--;
            return this.tail.before.data;
        }

        toRemove.before.next = toRemove.next;
        toRemove.next.before = toRemove.before;
        size--;
        return toRemove.data;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;
        for (Object obj: collection) {
            flag &= remove(obj);
        }
        return flag;
    }

    public E set(int index, E element) {
        DNode node = getNode(index);
        E old = node.data;
        node.data = element;
        return old;
    }

    public int size() {
        return size;
    }

    public String toString(){
        String s = "";
        if (isEmpty()) return s;
        DNode nowNode = head;
        for(; nowNode.next != null; nowNode = nowNode.next){
            s += nowNode.toString();
        }
        return s;
    }
}
