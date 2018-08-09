package win.smile.chapter6.lambda;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 * @author smilewei on 2018/7/12.
 * @since 1.0.0
 */
public class LambdaTest {


    public static void main(String[] args) {

        Path path = Paths.get("/usr/bin");

        Comparator<String> compare = (first,second)->first.length() - second.length();


    }

}
