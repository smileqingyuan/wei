package win.smile.collection;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @author weifw
 * @date 2018-08-27-12:50
 */
public class ArrayListClient {

   List list =  new ArrayList();

    public static void main(String[] args) {
        List<String> list =  new ArrayList(10);
        System.out.println(list.size());
        list.add(0,"bbb");
        list.add(1,"aaa");
        list.set(9, "12");
        list.set(100,"21" );
        System.out.println(list.size());
        int[] a = new int[9];
        System.out.println(a.length);
        int aa = Integer.MAX_VALUE+1;
        System.out.println(aa);

    }

}
