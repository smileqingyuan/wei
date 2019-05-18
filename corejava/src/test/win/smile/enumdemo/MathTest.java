package win.smile.enumdemo;

import org.junit.Test;

/**
 * @author weifw
 * @date 2018-08-17-11:19
 */
public class MathTest {

    @Test
    public void testMath() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Math.random() * 5000);
        }
    }

}
