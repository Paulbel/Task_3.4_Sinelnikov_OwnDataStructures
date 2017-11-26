package by.tc.sctructure.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class BinaryTree<E extends Comparable<E>> implements Tree, Serializable {

    private Node<E> root;

    private static class Node<E extends Comparable<E>> {
        Node<E> left;
        Node<E> right;
        E value;

        Node(E value) {
            left = null;
            right = null;
            this.value = value;
        }
    }

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(E value) {
        root = new Node<>(value);
    }


    public void setRoot(Object root) {
        this.root = new Node<>((E)root);
    }


    public List<E> getChildList(E element) {
        Node<E> node = search(root, element);
/*        if (node == null) {
            throw new NoSuchElement(element.toString());
        }*/
        List<E> nodesList = new ArrayList<>();
        if (node.left != null) {
            nodesList.add(node.left.value);
        }
        if (node.right != null) {
            nodesList.add(node.right.value);
        }
        return nodesList;
    }



    @Override
    public List getChildList(Object node) {
        return null;
    }

    @Override
    public List<E> toLevelOrderList() {
        List<E> levelOrderList = getLevelOrder();
        return levelOrderList;
    }


    private List<E> getLevelOrder() {
        List<E> list = new ArrayList<>();
        int rootHeight = height(root);

        for (int level = 1; level <= rootHeight; level++) {
            addNodesFromLevel(list, root, level);
        }
        return list;
    }

    private void addNodesFromLevel(List<E> list, Node<E> root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            list.add(root.value);
        } else if (level > 1) {
            addNodesFromLevel(list, root.left, level - 1);
            addNodesFromLevel(list, root.right, level - 1);
        }
    }

    private int height(Node<E> root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (leftHeight > rightHeight) {
            return (leftHeight + 1);
        } else return (rightHeight + 1);
    }


    @Override
    public List<E> toLeftRightList() {
        List<E> list = new ArrayList<>();
        leftRightInorder(list, root);
        return list;
    }

    private void leftRightInorder(List<E> list, Node<E> node) {
        if (node == null) {
            return;
        }

        leftRightInorder(list, node.left);
        list.add(node.value);
        leftRightInorder(list, node.right);
    }

    @Override
    public List<E> toRightLeftList() {
        List<E> list = new ArrayList<>();
        rightLeftInorder(list, root);
        return list;
    }

    private void rightLeftInorder(List<E> list, Node<E> node) {
        if (node == null) {
            return;
        }

        rightLeftInorder(list, node.right);
        list.add(node.value);
        rightLeftInorder(list, node.left);

    }

    @Override
    public List<E> toList() {
        return getLevelOrder();
    }

    @Override
    public Iterator<E> iterator() {
        return levelOrderIterator();
    }

    @Override
    public Iterator<E> rightLeftIterator() {
        List<E> rightLeftList = toRightLeftList();
        return rightLeftList.iterator();
    }

    @Override
    public Iterator<E> leftRightIterator() {
        List<E> leftRightList = toLevelOrderList();
        return leftRightList.iterator();
    }

    @Override
    public Iterator<E> levelOrderIterator() {
        List<E> levelOrderList = getLevelOrder();
        return levelOrderList.iterator();
    }

    @Override
    public boolean contains(Object node) {
        return false;
    }

    @Override
    public boolean add(Object node) {
        return false;
    }

    @Override
    public boolean remove(Object node) {
        return false;
    }


    public boolean contains(E node) {
        Node<E> searched = search(root, node);
        return searched != null;
    }

    private Node<E> search(Node<E> root, E key) {
        if (root == null || root.value.equals(key)) {
            return root;
        }

        if (root.value.compareTo(key) > 0) {
            return search(root.left, key);
        }

        return search(root.right, key);
    }

    public boolean add(E node) {
        insert(root, node);
        return true;
    }

    private void insert(Node<E> node, E value) {
        if (isLess(value, node.value)) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                node.right = new Node<>(value);
            }

        } else {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new Node<>(value);
            }
        }
    }

    public boolean remove(E node) {
        return removeElement(node);
    }

    private boolean removeElement(E key) {
        removeRecursive(root, key);
        return true;
    }

    private Node<E> removeRecursive(Node<E> root, E value) {
        if (root == null) {
            return null;
        }

        if (isLess(value, root.value)) {
            root.left = removeRecursive(root.left, value);
        } else if (isGreater(value, root.value)) {
            root.right = removeRecursive(root.right, value);
        } else {
            if (!hasLeftChild(root)) {
                return root.right;
            } else if (!hasRightChild(root)) {
                return root.left;
            }

            root.value = minValue(root.right);

            root.right = removeRecursive(root.right, root.value);
        }

        return root;
    }

    private E minValue(Node<E> root) {
        E minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    @Override
    public void clear() {
        removeAll(root);
        root = null;
    }

    private Node<E> removeAll(Node<E> root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left = removeAll(root.left);
        }
        if (root.right != null) {
            root.right = removeAll(root.right);
        }
        root.value = null;

        return root;
    }

    private boolean hasLeftChild(Node<E> node) {
        return node.left != null;
    }

    private boolean hasRightChild(Node<E> node) {
        return node.right != null;
    }

    private boolean isGreater(E first, E second) {
        return first.compareTo(second) > 0;
    }

    private boolean isLess(E first, E second) {
        return first.compareTo(second) < 0;
    }

}