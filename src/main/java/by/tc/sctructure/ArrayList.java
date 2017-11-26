package by.tc.sctructure;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private Object array[] = new Object[10];
    private int size = 0;

    public int size() {
        return size;
    }

    private void grow(int minCapacity) {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        array = Arrays.copyOf(array, newCapacity);
    }

    private class ListIteratorImpl implements ListIterator {
        int currentPosition = 0;

        @Override
        public boolean hasNext() {
            trim();
            if (currentPosition < size) {
                return true;
            }
            return false;
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
            if (currentPosition - 1 >= 0 && array[currentPosition - 1] != null) {
                return true;
            }
            return false;
        }

        @Override
        public E previous() {
            trim();
            if (currentPosition == 0) {
                currentPosition--;
            }
            currentPosition--;
            int pos = currentPosition;

            return (E) array[pos];
        }

        @Override
        public int nextIndex() {
            return currentPosition + 1;
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

        }

        @Override
        public void add(Object o) {

        }

    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity > array.length) {
            grow(newCapacity);
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public boolean contains(Object o) {
        for (int index = 0; index < size; index++) {
            if (array[index].equals(o)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Object[] toArray() {
        trim();
        return array;
    }

    public boolean add(Object o) {
        ensureCapacity(size + 1);
        array[size++] = o;
        return true;
    }

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

    public boolean addAll(Collection c) {
        Iterator iterator = c.iterator();
        ensureCapacity(size + c.size());
        while (iterator.hasNext()) {
            array[++size] = iterator.next();
        }
        return true;
    }

    public boolean addAll(int index, Collection c) {
        return false;
    }

    public void clear() {

    }

    public E get(int index) {
        trim();
        if (index > size - 1) {
            return null;
        } else {
            return (E) array[index];
        }
    }

    public Object set(int index, Object element) {
        trim();
        if (index > size - 1) {
            return null;
        } else {
            array[index] = element;
            return array[index];
        }
    }

    public void add(int index, Object element) {
        trim();
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1,
                size - index);
        array[index] = element;
        size++;
    }

    public E remove(int index) {
        Object e = array[index];
        System.arraycopy(array, index + 1, array, index,
                size - index - 1);
        size--;
        return (E) e;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }


    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    public Object[] toArray(Object[] a) {
        return new Object[0];
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
}
