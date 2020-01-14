package plus.one;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author weifw
 * @date 2020-01-12-9:23
 */
public class CollectionTest {

    @Test
    public void testHashMap(){

//        Map<Integer,Integer> hmap = new HashMap<>();
//        for (int binCount = 0; binCount <=10000; ++binCount) {
//            hmap.put(binCount%19, binCount);
//        }
        String a;
        String b = null;
        a=b;
        b=new String("ac");
        System.out.println(a);

        User user = new User();
        User userb = new User();
        user = userb;
        userb = new User();
        userb.setAge(1);
        userb.setName("name");
        System.out.println(user.getAge());

        User userc = new User();
        User userd = null;
        if((userc = userd) == null){
            userd = new User();
            userd.setName("aa");
            userd.setAge(11);
        }
        System.out.println(userc==null);

        int aa =10; int bb = 100;
        if(aa>0){
            System.out.println(aa);
        }else if(bb>10){
            System.out.println(bb);
        }


    }


}
