package wei.smile.helper;

import wei.smile.annotation.Controller;
import wei.smile.annotation.Service;
import wei.smile.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手
 *
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public final class ClassHelper {

    /**
     * 定义类集合(用于存放所加载的类)
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下的所有类
     *
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }


    /**
     * 获取应用包名下所有的Bean类
     *
     * @return
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getClassSetByAnnotation(Controller.class));
        classSet.addAll(getClassSetByAnnotation(Service.class));
        return classSet;

    }

    /**
     * 获取 应用包名下某父类(或接口)的所有子类（或实现类）
     *
     * @param superClass
     * @return
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<>();
        CLASS_SET.forEach(cls -> {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        });
        return classSet;
    }

    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<>();
        CLASS_SET.forEach(cls -> {
           if(cls.isAnnotationPresent(annotationClass)){
               classSet.add(cls);
           }
        });

        return classSet;
    }

}
