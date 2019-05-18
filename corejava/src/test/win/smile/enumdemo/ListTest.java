package win.smile.enumdemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weifw
 * @date 2018-11-13-18:04
 */
public class ListTest {

    @Test
    public void testList(){
        List<Integer> list = new ArrayList<>();
        list.add(0,null);
        System.out.println(list.size());
        System.out.println(list.get(0));

    }


}
