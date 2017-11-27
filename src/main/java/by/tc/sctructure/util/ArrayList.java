package by.tc.sctructure.util;

import java.io.Serializable;
import java.util.*;

public class ArrayList<E> implements List<E>, Serializable {
    private Object array[];
    private int size;


    public ArrayList(){
        this.size = 0;
        array = new Object[16];
    }


    @Override
    public int size() {
        return size;
    }

    private class ListIteratorImpl implements ListIterator {
        private int currentPosition = 0;

        @Override
        public boolean hasNext() {
            trim();
            return currentPosition < size;
        }

        @Override
        public E next() {
            trim();
            int position = currentPosition;
            currentPosition++;
            return (E) array[position];
        }

        @Override
        public boolean hasPrevious() {
            trim();
            if (currentPosition - 1 >= 0) {
                return true;
            }
            return false;
        }

        @Override
        public E previous() {
            trim();
            currentPosition--;
            int pos = currentPosition;
            return (E) array[pos];
        }

        @Override
        public int nextIndex() {
            return currentPosition;
        }

        @Override
        public int previousIndex() {
            return currentPosition - 1;
        }

        @Override
        public void remove() {
            ArrayList.this.remove(currentPosition);
        }

        @Override
        public void set(Object o) {
            array[currentPosition] = o;
        }

        @Override
        public void add(Object o) {
            ArrayList.this.add(currentPosition, o);
        }

        ListIteratorImpl() {
        }

        ListIteratorImpl(int index) {
            currentPosition = index;
        }
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int index = 0; index < size; index++) {
            if (array[index].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIteratorImpl();
    }

    @Override
    public Object[] toArray() {
        trim();
        return array;
    }

    @Override
    public boolean add(Object o) {
        ensureCapacity(size + 1);
        array[size++] = o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (array[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(array[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        Iterator iterator = c.iterator();
        ensureCapacity(size + c.size());
        while (iterator.hasNext()) {
            array[++size] = iterator.next();
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        Object[] a = c.toArray();
        int arrayLength = a.length;
        ensureCapacity(size + arrayLength);
        int numMoved = size - index;
        if (a.length > 0)
            System.arraycopy(array, index, array, index + arrayLength,
                    numMoved);

        System.arraycopy(a, 0, array, index, arrayLength);
        size += arrayLength;
        return arrayLength != 0;
    }

    @Override
    public void clear() {
        array = new Object[0];
    }

    @Override
    public E get(int index) {
        trim();
        if (index > size - 1) {
            return null;
        } else {
            return (E) array[index];
        }
    }

    @Override
    public Object set(int index, Object element) {
        trim();
        if (index > size - 1) {
            return null;
        } else {
            array[index] = element;
            return array[index];
        }
    }

    @Override
    public void add(int index, Object element) {
        trim();
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1,
                size - index);
        array[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        Object e = array[index];
        System.arraycopy(array, index + 1, array, index,
                size - index - 1);
        size--;
        return (E) e;
    }

    @Override
    public int indexOf(Object o) {
        for (int index = 0; index < array.length; index++) {
            if (array[index].equals(o)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        for (int index = array.length; index >= 0; index--) {
            if (array[index].equals(o)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIteratorImpl(index);
    }


    public List subList(int fromIndex, int toIndex) {

        List<E> list = new ArrayList<>();
        for (int index = fromIndex; index < toIndex; index++) {
            list.add((E) array[index]);
        }
        return list;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            remove(iterator.next());
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        trim();
        return array;
    }


    private void trim() {
        if (size != 0 && size < array.length) {
            array = Arrays.copyOf(array, size);
        } else if (size == 0) {
            array = new Object[0];
        }
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index,
                    numMoved);
        }
        array[--size] = null;
    }

    private void grow(int minCapacity) {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        array = Arrays.copyOf(array, newCapacity);
    }


    private void ensureCapacity(int newCapacity) {
        if (newCapacity > array.length) {
            grow(newCapacity);
        }
    }
}
