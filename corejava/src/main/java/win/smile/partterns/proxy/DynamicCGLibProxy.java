package win.smile.partterns.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public class DynamicCGLibProxy implements MethodInterceptor {

    private static DynamicCGLibProxy instance = new DynamicCGLibProxy();

    private DynamicCGLibProxy() {
    }

    public static DynamicCGLibProxy getInstance(){
        return instance;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(obj,args);
        after();
        return result;
    }


    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    private void before() {
        System.out.println("Before.........");
    }

    private void after() {
        System.out.println("After.........");
    }



}
