package win.smile.partterns.proxy.cglib;

/**
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public class PersonService {
    public String sayHello(String name) {
        return "Hello " + name;
    }

    public Integer lengthOfName(String name) {
        return name.length();
    }
}
