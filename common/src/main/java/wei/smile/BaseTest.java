package wei.smile;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BaseTest {

    @Test
    public void testDouble(){

        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal price = new BigDecimal(12365);
        BigDecimal mo = new BigDecimal(10000);
        BigDecimal divide = price.divide(mo,2,BigDecimal.ROUND_DOWN);
        System.out.println(divide);
    }

}
