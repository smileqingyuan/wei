package win.smile.comparetor;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author smilewei on 2018/7/5.
 * @since 1.0.0
 */
public class CompareTest {


    public static void main(String[] args) {

        String[] friends = {"Peter", "Paul", "Mar", "Lucygirl", "Monkey"};
        Arrays.sort(friends,new LenthCompartor());
        System.out.println(Arrays.toString(friends));

    }

}
class LenthCompartor implements Comparator<String> {

    @Override
    public int compare(String first, String second) {
        return first.length() - second.length();
    }

}