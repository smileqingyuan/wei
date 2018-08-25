package win.smile.partterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * JDK 方式的动态代理 ： 只能对实现了接口的类进行，没有实现接口的类不能使用JDK动态代理。
 * cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public class DynamicJDKProxy implements InvocationHandler {

    private Object target;

    public DynamicJDKProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("Before.........");
    }

    private void after() {
        System.out.println("After.........");
    }

    /**
     * 获得代理对象
     * @param <T>
     * @return
     */
    public <T> T getProxy(){
        return (T) newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

}
