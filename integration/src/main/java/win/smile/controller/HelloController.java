package win.smile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.smile.service.HelloService;

/**
 * @author smilewei on 2018/8/9.
 * @since 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public String index() {
        helloService.indexService();
        return "Greetings from Spring Boot!";
    }
}
