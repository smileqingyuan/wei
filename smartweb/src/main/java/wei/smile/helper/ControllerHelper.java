package wei.smile.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import wei.smile.annotation.Action;
import wei.smile.annotation.Controller;
import wei.smile.bean.Handler;
import wei.smile.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Controller操作类
 *
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public final class ControllerHelper {

    /**
     * 用于存放请求处理器的映射关系(简称 Action Map)
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        //获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getClassSetByAnnotation(Controller.class);
        if (CollectionUtils.isEmpty(controllerClassSet)) {

            controllerClassSet.forEach(controllerClass -> {

                Method[] methods = controllerClass.getMethods();
                if (!ArrayUtils.isEmpty(methods)) {
                    for (Method method : methods) {
                        if(method.isAnnotationPresent(Action.class)){
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证URL映射规则
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(!ArrayUtils.isEmpty(array)&&array.length==2){
                                    //获取请求方法与请求路径
                                    String requesMethod = array[0];
                                    String requesPath = array[1];
                                    Request request = new Request(requesMethod,requesPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    //初始化Aciton Map
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    /**
     * 获取Handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod,String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }


}
