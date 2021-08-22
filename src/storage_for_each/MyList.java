package storage_for_each;

public interface MyList<T> {
    int size();
    boolean isEmpty();
    void add(T t);
    T remove(int index);
    T get(int index);
    int getCapacity();
}
