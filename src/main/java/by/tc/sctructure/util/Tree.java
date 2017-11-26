package by.tc.sctructure.util;

import java.util.Iterator;
import java.util.List;

public interface Tree<E> extends Iterable<E> {
    void setRoot(E root);

    List<E> getChildList(E node);

    List<E> toLevelOrderList();

    List<E> toLeftRightList();

    List<E> toRightLeftList();


    List<E> toList();

    Iterator<E> rightLeftIterator();

    Iterator<E> leftRightIterator();

    Iterator<E> levelOrderIterator();

    boolean contains(E node);

    boolean add(E node);

    boolean remove(E node);

    void clear();
}