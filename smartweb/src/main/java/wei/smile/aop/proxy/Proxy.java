package wei.smile.aop.proxy;

/**
 * 代理接口
 * @author smilewei on 2018/8/28.
 * @since 1.0.0
 */
public interface Proxy {


    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
