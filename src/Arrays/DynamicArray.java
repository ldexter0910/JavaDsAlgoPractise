package Arrays;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class DynamicArray<T> implements Iterable<T> {
    private int capacity = 0;

    private int len = 0;

    private T[] arr;

    private void increaseArrCapacity(int capacity) {
        if(capacity<0) throw new IllegalArgumentException("Capacity of the array cannot be less than 0");
        while(this.capacity < capacity) {
            this.capacity = this.capacity == 0 ? capacity : 2*this.capacity;
        }
        T[] newArr = (T[])(new Object[this.capacity]);

        if(this.arr != null) {
            for (int i = 0; i < this.len; i++) {
                newArr[i] = this.arr[i];
            }
        }
        this.arr = newArr;
    }

    public DynamicArray() {
        this(8);
    }

    public DynamicArray(int capacity) {
        this.increaseArrCapacity(capacity);
    }

    public int size() {
        return this.len;
    }

    public boolean isEmpty() {
        return this.len == 0;
    }

    public T get(int index) {
        if(index<0 || index>=len) throw new IndexOutOfBoundsException("Index out of bound");
        return this.arr[index];
    }

    public void add(T item) {
        this.add(this.len, item);
    }

    public void add(int index, T item) {
        if(index<0) throw new IllegalArgumentException("Index cannot be less than 0");
        // logic to check get the length of the dynamic array
        int newLen = index < this.len ? this.len+1 : index+1;
        // logic to check if we need to increase the capacity of the dynamic array and do the needful
        if(newLen > this.capacity) {
            this.increaseArrCapacity(newLen);
        }
        // right shift if required
        for(int i=this.len-1;i>=index;i--) {
            this.arr[i+1] = this.arr[i];
        }
        this.arr[index] = item;
        this.len = newLen;
    }

    public void set(int index, T item) {
        if(index<0 || index>=len) throw new IndexOutOfBoundsException("Index out of bounds");

        this.arr[index] = item;
    }

    public void clear() {
        for(int i=0;i<this.len;i++) {
            this.arr[i] = null;
        }
    }

    public T remove(int index) {
        if(index<0 && index>=len) throw new IndexOutOfBoundsException("Index cannot be out of Bounds");

        T result = this.arr[index];
        for(int i=index;i<this.len-1;i++) {
            this.arr[i] = this.arr[i+1];
        }
        this.len--;
        return result;
    }

    public T remove(T item) {
        int index = this.indexOf(item);
        if(index != -1) {
            return this.remove(index);
        }
        return null;
    }

    public int indexOf(T item) {
        for(int i=0;i<this.len;i++) {
            if(item == this.arr[i])
                return i;
        }
        return -1;
    }

    public boolean contains(T item) {
        return this.indexOf(item) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return this.index < len;
            }
            @Override
            public T next() {
                return arr[this.index++];
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DynamicArray)) return false;
        DynamicArray<?> that = (DynamicArray<?>) o;
        return len == that.len && Arrays.equals(arr, that.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(len);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(2*this.len+1);
        res.append("[");
        for(int i=0;i<this.len;i++) {
            res.append(this.arr[i].toString());
            if(i != this.len-1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
}
