package win.smile.partterns.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author weifw
 * @date 2019-05-18-21:19
 */
public class ClsInvocationHandler implements InvocationHandler {

    private Class cls;

    public ClsInvocationHandler(Class cls) {
        this.cls = cls;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("aaaaaaaaaaaaaa");
        method.invoke(proxy,args);
        System.out.println("bbbbbbbbbbbbbbb");
        return null;
    }
}
