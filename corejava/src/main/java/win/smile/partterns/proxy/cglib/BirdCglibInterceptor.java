package win.smile.partterns.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author weifw
 * @date 2019-05-18-19:49
 */
public class BirdCglibInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("BirdCglibInterceptor before:" + method.getName());
//        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("BirdCglibInterceptor after:" + method.getName());
        return null;

    }
}
