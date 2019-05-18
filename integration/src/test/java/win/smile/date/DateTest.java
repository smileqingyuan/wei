package win.smile.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author weifw
 * @date 2018-12-25-16:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {

    @Test
    public void testLocalDateTime(){
        System.out.println(LocalDateTime.now());
    }

}
