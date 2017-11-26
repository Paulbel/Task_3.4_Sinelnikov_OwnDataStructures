package by.tc.sctructure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<E> implements List<E> {
    private int size = 0;
    private Entry first = null;
    private Entry last = null;

    private class Entry {
        private Entry prevEntry = null;
        private Entry nextEntry = null;
        private Object value;

        Entry(Object value) {
            this.value = value;
        }
    }

    private class ListIteratorImpl implements ListIterator {
        private Entry currentElement;
        private Entry addElement;

        @Override
        public boolean hasNext() {
            if (currentElement != null && currentElement.nextEntry != null) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            currentElement = currentElement.nextEntry;
            return currentElement.value;
        }

        @Override
        public boolean hasPrevious() {
            if (currentElement != null && currentElement != addElement) {
                return true;
            }
            return false;
        }

        @Override
        public Object previous() {
            Entry entry = currentElement;
            if (currentElement.prevEntry == null) {
                currentElement = addElement;
            } else {
                currentElement = currentElement.prevEntry;
            }
            return entry.value;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
            if (currentElement == addElement) {
                addElement.nextEntry = first.nextEntry;
                LinkedList.this.remove(first.value);
                return;
            }
            LinkedList.this.remove(currentElement.value);
        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }

        public ListIteratorImpl() {
            addElement = new Entry(null);
            addElement.nextEntry = first;
            currentElement = addElement;
        }
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return new ListIteratorImpl();
    }

    @Override
    public Object[] toArray() {
        Object [] array = new Object[size];
        Entry entry = first;
        int index = 0;
        while (entry!=null){
            array[index] = entry.value;
            index++;
            entry = entry.nextEntry;
        }
        return array;
    }

    @Override
    public boolean add(Object o) {
        if (size != 0) {
            last.nextEntry = new Entry(o);
            last.nextEntry.prevEntry = last;
            //last.prevEntry = last;
            last = last.nextEntry;
        } else {
            Entry entry = new Entry(o);
            this.last = entry;
            this.first = entry;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Entry currentElement = first;
        while (currentElement.nextEntry != null) {
            if (currentElement.value.equals(o)) {
                Entry prevEntry = currentElement.prevEntry;
                Entry nextEntry = currentElement.nextEntry;
                if (prevEntry != null) {
                    prevEntry.nextEntry = nextEntry;
                } else {
                    first = nextEntry;
                }
                if (nextEntry != null) {
                    nextEntry.prevEntry = prevEntry;
                } else {
                    last = prevEntry;
                }
                size--;
                return true;
            }
            currentElement = currentElement.nextEntry;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
