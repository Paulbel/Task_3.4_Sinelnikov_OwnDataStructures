package by.tc.sctructure.controller;







import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        List <String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.nextIndex());
           System.out.println(iterator.next());
        }
        if (iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
        iterator.set("W");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        while (iterator.hasPrevious()){
            System.out.println(iterator.previousIndex());
            System.out.println(iterator.previous());
        }
    }
}