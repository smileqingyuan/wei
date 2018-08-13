package win.smile;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * @author smilewei on 2018/8/7.
 * @since 1.0.0
 */
public class IteratorStudy {

    public static void main(String[] args) {
        List list = new ArrayList<>(12);
        Collections.sort(list);
    }

    @Test
    public void testListIterator(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        ListIterator<Integer> iterator = list.listIterator();
        boolean b = iterator.hasPrevious();
        System.out.println(b);
//        Integer previous = iterator.previous();
//        System.out.println(previous);
        iterator.add(123);
//        Integer next = iterator.next();
//        System.out.println(next);
        Integer previous = iterator.previous();
        System.out.println(previous);
//        Integer previous1 = iterator.previous();
//        System.out.println(previous1);
    }

}
