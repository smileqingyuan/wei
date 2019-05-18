package win.smile.house;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author weifw
 * @date 2019-03-15-22:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseCrawlerServiceTest {

    @Autowired
    private HouseCrawlerService houseCrawlerService;


    @Test
    public void get() {
        houseCrawlerService.get();
    }
}