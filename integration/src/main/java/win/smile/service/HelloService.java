package win.smile.service;

import org.springframework.stereotype.Service;

/**
 * @author smilewei on 2018/8/29.
 * @since 1.0.0
 */
@Service
public class HelloService {


    public String indexService(){
        System.out.println("indexService......");
        return "indexServie...";
    }

}
