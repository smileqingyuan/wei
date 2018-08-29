package wei.smile.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wei.smile.aop.annotation.Aspect;
import wei.smile.aop.proxy.AspectProxy;
import wei.smile.aop.proxy.Proxy;
import wei.smile.aop.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author smilewei on 2018/8/29.
 * @since 1.0.0
 */
public final class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);


    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            targetMap.forEach((targetCls, proxyList) -> {
                Object proxy = ProxyManager.createProxy(targetCls, proxyList);
                BeanHelper.setBean(targetCls, proxy);
            });
        } catch (Exception e) {
            LOGGER.error("aop failure", e);
        }
    }


    private static Set<Class<?>> createTargetClassSet(Aspect aspect) {

        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect.class)) {
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap() {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        proxyClassSet.forEach(proxyClass -> {
            if (proxyClass.isAnnotationPresent(Aspect.class)) {
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        });
        return proxyMap;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        proxyMap.forEach((proxyCls, targetClsSet) -> {

            targetClsSet.forEach(targetCls -> {
                try {
                    Proxy proxy = (Proxy) proxyCls.newInstance();
                    if (targetMap.containsKey(targetCls)) {
                        targetMap.get(targetCls).add(proxy);
                    } else {
                        List<Proxy> proxyList = new ArrayList<>();
                        proxyList.add(proxy);
                        targetMap.put(targetCls, proxyList);
                    }
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        });
        return targetMap;
    }


}
