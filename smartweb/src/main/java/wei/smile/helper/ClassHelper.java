package wei.smile.helper;

import wei.smile.annotation.Controller;
import wei.smile.annotation.Service;
import wei.smile.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手
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
     * @return
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有的service类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<>();
        classSet.forEach(cls->{
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        });
        return classSet;
    }

    /**
     * 获取应用包名下所有的Controller类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<>();
        classSet.forEach(cls->{
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        });
        return classSet;
    }

    /**
     * 获取应用包名下所有的Bean类
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getServiceClassSet());
        classSet.addAll(getControllerClassSet());
        return classSet;

    }

}
