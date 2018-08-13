package win.smile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import win.smile.model.UserModel;

/**
 * @author smilewei on 2018/8/10.
 * @since 1.0.0
 */
@Controller
public class HelloFreemarkerController {


    @RequestMapping(value = "/api/freemarker/hello/{param}", method = RequestMethod.GET)
    public String findOneCity(Model model, @PathVariable("param") String param) {
        model.addAttribute("param", param);
        return "hello";
    }

    @RequestMapping(value = "/api/freemarker/user/{name}/{age}", method = RequestMethod.GET)
    public String findUser(Model model, @PathVariable("name") String name,@PathVariable("age")int age) {
        model.addAttribute("user", new UserModel(name,age));
        return "user";
    }



}
