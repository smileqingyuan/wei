package wei.smile.aop.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author smilewei on 2018/8/28.
 * @since 1.0.0
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);


    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        begin();

        try {
            //判断该方法是否被切面切下
            if(intercept(cls,method,params)){
                before(cls,method,params);
                result = proxyChain.doProxyChain();
                after(cls,method,params,result);
            }else {
                result = proxyChain.doProxyChain();
            }
        }catch (Exception e){
            LOGGER.error("proxy failure",e);
            error(cls,method,params,e);
        }finally {
            end();
        }



        return result;
    }


    public void begin() {

    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {

    }


    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    public void after(Class<?> cls, Method method, Object[] params,Object result) throws Throwable {

    }

    public void error(Class<?> cls, Method method, Object[] params,Throwable e)  {

    }

    public void end() {

    }

}
