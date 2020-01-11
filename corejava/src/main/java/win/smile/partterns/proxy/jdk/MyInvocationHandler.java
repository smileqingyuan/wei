package win.smile.partterns.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author weifw
 * @date 2019-05-18-17:21
 */
public class MyInvocationHandler<T> implements InvocationHandler {


//    private T target;
//
//
//    public MyInvocationHandler(T target) {
//        this.target = target;
//    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println(args);
        beforeMethod();

//        method.invoke(target, args);

        afterMethod();

        return "bbb";
    }


    public void beforeMethod() {
        System.out.println("饭前处理");
    }


    public void afterMethod() {
        System.out.println("饭后处理");
    }


}
