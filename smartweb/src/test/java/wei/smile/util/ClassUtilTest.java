package wei.smile.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author smilewei on 2018/8/25.
 * @since 1.0.0
 */
public class ClassUtilTest {

    @Test
    public void getClassLoader() throws Exception {
    }

    @Test
    public void loadClass() throws Exception {
    }

    @Test
    public void getClassSet() throws Exception {
        ClassUtil.getClassSet("wei.smile");
        Class<?> aClass = Class.forName("wei.smile.annotation.Action",false,Thread.currentThread().getContextClassLoader());
        System.out.println(aClass);
    }

}