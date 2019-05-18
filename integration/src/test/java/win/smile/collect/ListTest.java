package win.smile.collect;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weifw
 * @date 2018-12-27-14:59
 */
public class ListTest {


    @Test
    public void testRemoveObject(){
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("11");
        list.add("11");
        list.add("33");
        list.remove("11");
        System.out.println(list);

    }


}
