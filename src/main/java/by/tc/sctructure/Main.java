package by.tc.sctructure;


import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String [] args){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addNode(3,"ewe");
        binaryTree.addNode(4,"fsfd");
        binaryTree.addNode(2,"asda");
        binaryTree.addNode(1,"fsfd");
        binaryTree.addNode(7,"asda");
        binaryTree.addNode(9,"fsfd");
        binaryTree.addNode(10,"asda");
        binaryTree.inOrderTraverseTree(binaryTree.findNode(3));
        binaryTree.postOrderTraverseTree(binaryTree.findNode(3));
        binaryTree.preorderTraverseTree(binaryTree.findNode(3));
        List<String> a = new LinkedList<>();
        a.add("A");
        a.add("B");
        a.add("C");
        a.add("D");

        ListIterator<String> iterator =  a.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(a.toArray().length);


/*        List<String> list = new java.util.ArrayList<>();
        list.add("kk");
        list.add("priv");
        ListIterator iterator1 = list.listIterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
            System.out.println(iterator1.next());
        }
        while (iterator1.hasPrevious()){
            System.out.println(iterator1.previous());
        }*/
        //List <String> list = new LinkedList<>()
    }
}
