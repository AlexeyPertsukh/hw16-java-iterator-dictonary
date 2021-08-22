package storage_for_each;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Storage<T> implements MyList<T>, Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private  Object[] data;
    private  int size;  //счетчик количества объектов в хранилище

    public Storage()
    {
        this(DEFAULT_CAPACITY);
    }

    public Storage(int capacity)
    {
        data = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void add(T t) {
        if(size >= data.length) {
            int newLength;
            if(data.length == 0) {
                newLength = DEFAULT_CAPACITY;
            }
            else {
                newLength = data.length + (data.length >> 1);      // увеличиваем размер массива на 50%
            }
            Object[] tmp = new Object[newLength];
            System.arraycopy(data, 0, tmp, 0, data.length);
            data = tmp;
        }
            data[size] = t;
            size++;
    }

    public T remove(int index)
    {
        if(index < 0 || index >= size) {
            return null;
        }

        T oldValue = elementData(index);
        if (data.length - 1 - index >= 0) {
            System.arraycopy(data, index + 1, data, index, data.length - 1 - index);
        }
        data[data.length - 1] = null;
        size--;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T elementData(int index) {
        return (T) data[index];
    }

    public T get(int index) {
        return elementData(index);
    }

    public int getCapacity(){
        return data.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new StorageIterator<T>(data, size);
    }


    //делаем наш внутренний итератор
    private static class StorageIterator<T> implements Iterator<T> {
        private final Object[] array;
        private int pos;
        private final int size;

        public StorageIterator(Object[] data, int size) {
            this.array = data;
            this.size = size;
        }

        public boolean hasNext() {
            return (pos < size);
        }

        @SuppressWarnings("unchecked")
        public T next() throws NoSuchElementException {
            if (hasNext()) {
                return (T) array[pos++];
            }
            else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
