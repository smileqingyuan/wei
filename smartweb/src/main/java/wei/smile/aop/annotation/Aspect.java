package wei.smile.aop.annotation;

import java.lang.annotation.*;

/**
 * @author smilewei on 2018/8/28.
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     * @return
     */
    Class<? extends Annotation> value();

}
