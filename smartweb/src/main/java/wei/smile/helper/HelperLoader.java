package wei.smile.helper;

import wei.smile.util.ClassUtil;

/**
 * 加载相应的Helper
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public final class HelperLoader {

    /**
     * 为了加载更为集中，写了个加载方法，方便管理
     */
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                /*AOP的加载优先于IOC，因为首先需要通过AopHelper获取代理对象，然后才能通过IocHelper进行依赖注入*/
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }

    }

}
