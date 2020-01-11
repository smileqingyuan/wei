package plus.one.genericity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author weifw
 * @date 2020-01-04-11:42
 */
public class LinkedStack<T> {

    private static class Node<U>{

        U item;
        Node<U> next;
        Node(){
            item = null; next = null;
        }

        Node(U item,Node<U> next){
            this.item = item;
            this.next = next;
        }

        boolean end(){
            return item == null && next == null;
        }

    }

    private Node<T> top = new Node<T>();

    public void push(T item){
        top = new Node<>(item,top);
    }

    public T pop(){
        T result = top.item;
        if(!top.end()){
            top = top.next;
        }
        return result;
    }


    public static void main(String[] args) {
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();

        System.out.println(c1);
        System.out.println(c2);
        new HashMap<>();
    }



}
