package wei.smile.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 编码与解码操作工具类
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public final class CodeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeUtil.class);

    /**
     * 将 URL编码
     * @return
     */
    public static String encodeURL(String source){
        String target = null;

        try {
            target = URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将 URL解码
     * @return
     */
    public static String decodeURL(String source){
        String target = null;

        try {
            target = URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("encode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }

}
