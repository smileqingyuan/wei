package win.smile.size;

import com.carrotsearch.sizeof.RamUsageEstimator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weifw
 * @date 2018-12-13-14:54
 */
public class ObjectSizeTest {

    @Test
    public void testSize(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            list.add(5000000);
        }
        long sizeOf = RamUsageEstimator.sizeOf(list);
        System.out.println(sizeOf);
    }

    @Test
    public void testTime(){

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        System.out.println(list);

        int size = list.size();
        for (int i = 0; i < list.size(); i++) {
            if(i%2==0){
                list.remove(i);
            }
        }


    }

}
