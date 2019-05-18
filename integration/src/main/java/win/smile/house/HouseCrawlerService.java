package win.smile.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weifw
 * @date 2019-03-15-22:05
 */
@Service
public class HouseCrawlerService {


    @Autowired
    private HouseAugentMapper houseAugentMapper;


    public void  get(){
        System.out.println(houseAugentMapper.get());
    }

}
