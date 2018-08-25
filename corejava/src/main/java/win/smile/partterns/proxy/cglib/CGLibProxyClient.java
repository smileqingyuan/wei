package win.smile.partterns.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public class CGLibProxyClient {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "Hello Tom!");
        enhancer.setCallback((MethodInterceptor) (obj, method, argss, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello Tom!";
            } else {
                return proxy.invokeSuper(obj, argss);
            }
        });

        PersonService proxy = (PersonService) enhancer.create();
        int lengthOfName = proxy.lengthOfName("Mary");
        System.out.println(lengthOfName);
        assertEquals(4, lengthOfName);
        String s = proxy.sayHello(null);
        System.out.println(s);
        assertEquals("Hello Tom!", s);

    }

    @Test
    public void givenPersonService_whenSayHello_thenReturnResult() {
        // given
        PersonService personService = new PersonService();

        // when
        String res = personService.sayHello("Tom");

        // then
        assertEquals(res, "Hello Tom");
    }

    @Test
    public void givenEnhancerProxy_whenExtendPersonService_thenInterceptMethod() throws Exception {
        // given
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "Hello Tom!");
        PersonService proxy = (PersonService) enhancer.create();

        // when
        String res = proxy.sayHello(null);

        // then
        assertEquals("Hello Tom!", res);
    }


    @Test
    public void givenEnhancer_whenExecuteMethodOnProxy_thenInterceptOnlyStringReturnTypeMethod() throws Exception {
        // given
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                return "Hello Tom!";
            } else {
                return proxy.invokeSuper(obj, args);
            }
        });

        // when
        PersonService proxy = (PersonService) enhancer.create();

        // then
        assertEquals("Hello Tom!", proxy.sayHello(null));
        int lengthOfName = proxy.lengthOfName("Mary");
        assertEquals(4, lengthOfName);
    }


}
