import java.util.Collection;
import java.util.function.Predicate;

public class MyArrayListNoImpl<E>{

    private int size;
    private int capacity;
    private E[] array;

    public MyArrayListNoImpl(int size, E[] array, int capacity) {
        this.size = size;
        this.capacity = capacity;
    }

    public MyArrayListNoImpl() {
        this(0, null, 10);
        this.array = (E[]) new Object[10];
    }

    public MyArrayListNoImpl(int capacity){
        this(0, null, capacity);
        this.array = (E[]) new Object[capacity];
    }

    public boolean add(E e) {
        if (this.size >= this.capacity) grow();

        this.array[size] = e;
        this.size++;
        return true;
    }
    public boolean add(int index, E e){
        if (e == null) throw new NullPointerException();
        if (index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException();
        add(e);
        for(int i = this.size - 1; i >= index; i--){
            this.array[i] = this.array[i-1];
        }
        this.array[index] = e;
        return true;
    }
    public void grow(){
        E[] bigger = (E[]) new Object[capacity * 2];
        System.arraycopy(this.array, 0, bigger, 0, this.size);
        capacity *= 2;
        this.array = bigger;
    }

    public E get(int index){
        if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();
        return this.array[index];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        if (this.size == 0) return false;
        return true;
    }
    public boolean contains(E e) {
        if (e == null) throw new NullPointerException();
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(e)) return true;
        }
        return false;
    }

    public boolean remove(E e) {
        if (e == null) throw new NullPointerException();
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(e)){
                for(int j = i; j < this.size - 1; j++){
                    this.array[j] = this.array[j + 1];
                }
                this.size--;
                return true;
            }
        }
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        if (c == null) throw new NullPointerException();
        Object[] tmp = c.toArray();
        for(int i = 0; i < c.size(); i++){
            add((E)tmp[i]);
        }
        return true;
    }

    public boolean removeAll(Collection<? extends E> c) {
        boolean deletaFlag = false;
        for (Object o : c) {
            deletaFlag |= remove((E)c);
        }
        return false;
    }

    public boolean removeIf(Predicate<? super E> filter) {
        boolean deleteFlag = false;
        for (int i = 0; i < size; i++) {
            if(filter.test(this.array[i])) {
                remove(this.array[i]);
                deleteFlag = true;
            }
        }
        return deleteFlag;
    }

    public void clear() {
        this.size = 0;
    }

    public E set(int index, E element) {
        if(element == null) throw new NullPointerException();
        if(index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException();

        E old = this.array[index];
        this.array[index] = element;
        return old;
    }

    public int indexOf(E e){
        if (e == null) throw new NullPointerException();
        for(int i = 0; i < this.size; i++){
            if(this.array[i].equals(e)) return i;
        }
        return -1;
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < size; i++){
            s += this.array[i].toString() + " ";
        }
        return s;
    }
}
