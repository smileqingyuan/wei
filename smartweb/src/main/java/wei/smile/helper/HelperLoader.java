package wei.smile.helper;

import wei.smile.annotation.Controller;
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
                IocHelper.class,
                ControllerHelper.class,
                AopHelper.class
        };

        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }

    }

}
