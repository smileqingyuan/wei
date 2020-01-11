package win.smile.partterns.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * CGLIB动态生成的代理类会继承我们的业务类，并在代理类中对代理方法进行强化处理(前置处理、后置处理等)。CGLIB是高效的代码生成包，底层依靠ASM（开源的java字节码编辑类库）操作字节码实现的，性能比JDK强。
 * @author weifw
 * @date 2019-05-18-19:51
 */
public class CglibClient {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(Dog.class);
        //设置回调
        enhancer.setCallback(new DogCglibInterceptor());
        //生成代理类对象
        Dog dog = (Dog)enhancer.create();
        //在调用代理类中方法时会被我们实现的方法拦截器进行拦截
        dog.bark();


        enhancer.setSuperclass(Sparrow.class);
        enhancer.setCallbacks(new Callback[]{new DogCglibInterceptor(), new BirdCglibInterceptor()});
        Bird bird = (Bird) enhancer.create();
        bird.fly();



    }

}
