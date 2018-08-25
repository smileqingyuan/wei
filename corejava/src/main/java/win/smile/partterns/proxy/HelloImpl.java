package win.smile.partterns.proxy;

/**
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public class HelloImpl implements Hello {

    @Override
    public void say(String name) {
        System.out.println("Hello "+name);
    }
}
