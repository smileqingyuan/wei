package wei.smile.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON 工具类
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public class JSONUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);

    /**
     * 将POJO 转为JSON
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj){
        return JSON.toJSONString(obj);
    }

    /**
     * 将JSON 转为 POJO
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Class<T> type){
        return JSON.parseObject(json,type);
    }


}
