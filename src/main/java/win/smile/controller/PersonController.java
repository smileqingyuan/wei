package win.smile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import win.smile.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weifengwei
 * @date 2018-05-06
 */
@Controller
public class PersonController {

    @RequestMapping("/person")
    public String index(Model model) {
//        Person single = new Person("aa", 11);
//
//        List<Person> personList = new ArrayList<Person>();
//        Person p1 = new Person("bb", 22);
//        Person p2 = new Person("cc", 33);
//        Person p3 = new Person("dd", 44);
//        personList.add(p1);
//        personList.add(p2);
//        personList.add(p3);
//        model.addAttribute("singlePerson",single);
//        model.addAttribute("people",personList);

        return "index";
    }

}
