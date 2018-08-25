package win.smile.partterns.proxy;

/**
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public class ProxyClient {

    public static void main(String[] args) {
//
//        DynamicJDKProxy dynamicJDKProxy = new DynamicJDKProxy(new HelloImpl());
//        Hello helloProxy = dynamicJDKProxy.getProxy();
//        helloProxy.say("Jack...");


        Hello proxy = DynamicCGLibProxy.getInstance().getProxy(HelloImpl.class);
        proxy.say("CGLIB....");


    }

}
