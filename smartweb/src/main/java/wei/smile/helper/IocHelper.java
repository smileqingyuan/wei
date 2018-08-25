package wei.smile.helper;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import wei.smile.annotation.Inject;
import wei.smile.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 *
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public final class IocHelper {

    /**目前所有的对象都是单例的*/
    static {
        //获取所有的Bean类与Bean实例之间的映射关系(简称Bean Map)
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (!MapUtils.isEmpty(beanMap)) {
            beanMap.forEach((beanClass, beanInstance) -> {
                Field[] fields = beanClass.getFields();
                if (!ArrayUtils.isEmpty(fields)) {
                    for (Field beanField : fields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            });
        }
    }
}
