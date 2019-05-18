package win.smile.collection;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author weifw
 * @date 2018-11-26-10:56
 */
public class TreeSetClient {

    @Test
    public void testTreeSet(){
        Set<Integer> treeSet = new TreeSet();
        treeSet.add(112);
        treeSet.add(112);
        treeSet.add(11);
        treeSet.add(1112);
        treeSet.add(1122);
        treeSet.add(12);
        System.out.println(treeSet.size());
        treeSet.forEach(t->{
            System.out.println(t);
        });
        System.out.println("---------------------------");
        treeSet.forEach(t->{
            System.out.println(t);
        });

        String a = null;
        String b = "Adf";
        String c = "Adf";
        System.out.println(a+b+c);
        System.out.println(a);
    }

}
